/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades.modelo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julian
 */
public class UtilidadesGeneral {

    public static String BUNDLE_PATH = "utilidades/modelo/bundle";
    public static String PROPIEDADES_PATH = "utilidades/modelo/propiedades";
//    public static final String UNIT_NAME = "simaAdminPU";
//    public static final String CAF_MULTIEMPRESA = "CAF MULTIEMPRESA";
    public static final String DEFAULT_LANGUAGE = "es";
    public static final String DEFAULT_COUNTRY = "co";
    public static Locale LOCALE = new Locale(DEFAULT_LANGUAGE, DEFAULT_COUNTRY);

    public static void generarLog(Exception ex) {
        String mensaje = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL).format(GregorianCalendar.getInstance(LOCALE).getTime());
        Logger.getLogger("analizador").log(Level.SEVERE, mensaje, ex);
    }

    public static String obtenerMD5(String clave) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(clave.getBytes("UTF-8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
            }
            return result;
        } catch (Exception ex) {
            return null;
        }
    }

    private static String validarMensaje(String mensaje) {
        String msg = null;
        try {
            msg = ResourceBundle.getBundle(BUNDLE_PATH, LOCALE).getString(mensaje);
        } catch (Exception e) {
            msg = mensaje;
        }
        return msg;
    }

    public static Map<? extends String, ? extends Object> mapearObjeto(final Object object) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Map<String, Object> map = new HashMap<String, Object>();
        String funClass = object.getClass().getName();
        Class c = Class.forName(funClass);
        Class[] paramTypes = new Class[0];

        for (Field f : object.getClass().getDeclaredFields()) {
            String methodName = "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
            Method m = c.getDeclaredMethod(methodName, paramTypes);
            Object valor = m.invoke(object);
            map.put(f.getName().toLowerCase(), valor);
        }
        return map;
    }

    public static Object obtenerObjetoMapa(final Object objOrigen, final Object objDestino) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException {
        String funClass = objDestino.getClass().getName();
        Class c = Class.forName(funClass);
        Class[] paramTypes = new Class[1];

        for (Field f : objDestino.getClass().getDeclaredFields()) {
            if (((LinkedHashMap) objOrigen).get(f.getName()) != null) {
//                paramTypes[0] = ((LinkedHashMap) objOrigen).get(f.getName()).getClass();
                paramTypes[0] = f.getType();
                String methodName = "set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);

                Method m = c.getDeclaredMethod(methodName, paramTypes);

                if (paramTypes[0].getName().equalsIgnoreCase("java.util.Date")) {
                    String fecha = (String) ((LinkedHashMap) objOrigen).get(f.getName());
                    m.invoke(objDestino, UtilFecha.fechaDeCadena(fecha, UtilFecha.PATRON_FECHA_YYYYMMDD));
                } else {
                    m.invoke(objDestino, ((LinkedHashMap) objOrigen).get(f.getName()));
                }

            }
        }
        return objDestino;
    }
}
