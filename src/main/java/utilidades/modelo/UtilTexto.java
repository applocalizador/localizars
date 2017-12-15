/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades.modelo;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author juliano
 */
public class UtilTexto {

    public static String CARACTER_COMILLA = "'";

    /*
     * Toma una cadena y la transforma reemplazando todos los caracteres especiales como tildes, ñ, etc.
     */
    public static String normalizar(String cadena) {
        String cadenaNormalizada = Normalizer.normalize(cadena, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        cadenaNormalizada = pattern.matcher(cadenaNormalizada).replaceAll("");
        pattern = Pattern.compile("[\"]");
        cadenaNormalizada = pattern.matcher(cadenaNormalizada).replaceAll("");
        cadenaNormalizada = cadenaNormalizada.replace("&", "-");
        return cadenaNormalizada;
    }

    /*
     * Elimina el caracter de la cadena recorriendo desde la izquierda
     * Ej:  eliminarCaracterIzquierda(0,000538) -> 000538 = 538 
     */
    public static String eliminarCaracterIzquierda(String caracter, String cadena) {
        StringBuilder cadenaAuxiliar = new StringBuilder(cadena);
        while (cadenaAuxiliar.indexOf(caracter) == 0) {
            cadenaAuxiliar.deleteCharAt(0);
        }
        return cadenaAuxiliar.toString();
    }

    /*
     * Elimina el caracter de la cadena recorriendo desde la derecha
     * Ej:  eliminarCaracterDerecha(0,441000) -> 441000 = 441.
     */
    public static String eliminarCaracterDerecha(String caracter, String cadena) {
        StringBuilder cadenaAuxiliar = new StringBuilder(cadena);
        while (cadenaAuxiliar.lastIndexOf(caracter) >= 0
                && cadenaAuxiliar.substring(cadenaAuxiliar.lastIndexOf(caracter)).equalsIgnoreCase(caracter)) {
            cadenaAuxiliar.deleteCharAt(cadenaAuxiliar.lastIndexOf(caracter));
        }
        return cadenaAuxiliar.toString();
    }

    /*
     * Toma la cadena y le agregar el caracter a la izquierda hasta completar numeroCaracteres
     */
    public static String lpad(String cadena, String caracter, int numeroCaracteres) {
        if (cadena == null) {
            cadena = "";
        }
        StringBuilder sb = new StringBuilder(numeroCaracteres);
        for (int i = 0; i < (numeroCaracteres - cadena.length()); i++) {
            sb = sb.append(caracter);
        }
        return sb.toString() + cadena;
    }

    /*
     * Toma la cadena y le agrega el caracter a la derecha hasta completar numeroCaracteres
     */
    public static String rpad(String cadena, String caracter, int numeroCaracteres) {
        if (cadena == null) {
            cadena = "";
        }
        StringBuilder sb = new StringBuilder(numeroCaracteres);
        sb.append(cadena);
        for (int i = 0; i < (numeroCaracteres - cadena.length()); i++) {
            sb = sb.append(caracter);
        }
        return sb.toString();
    }

    /*
     * Toma un vector de cadenas y la convierte en una cadena separada por separador
     */
    public static String arrayToString(String[] lista, String separador) {
        String cadena = "";
        for (int i = 0; i < lista.length; i++) {
            cadena += lista[i] + separador;
        }
        if (cadena.length() > 0) {
            cadena = cadena.substring(0, cadena.length() - 1);
        }
        return cadena;
    }

    public static List<String> transformarLista(final List<?> objects) {
        List<String> lista = new ArrayList<String>();
        for (Object ob : objects) {
            lista.add(ob.toString());
        }
        return lista;
    }

    public static String cadenaDefecto(final String cadena, final String caracter) {
        if (cadena != null) {
            return (caracter + cadena + caracter).trim();
        }
        return null;
    }

    /**
     *
     * @param cadena
     * @param defecto
     * @param caracter
     * @return
     */
    public static String cadenaDefecto(final String cadena, final String defecto, final String caracter) {
        if (cadena != null && cadena.length() > 0) {
            return (caracter + cadena + caracter).trim();
        }
        if (defecto != null && defecto.length() > 0) {
            return defecto.trim();
        }
        return null;
    }

    /**
     * Evalua una cadena si es valida la retorna en mayuscula de lo contrario
     * retorna null, se le puede adicionar un caracter para realizar
     * insercciones en bases de datos.
     *
     * @param cadena
     * @param caracter
     * @return String
     *
     */
    public static String upper(final String cadena, final String caracter) {
        if (cadena != null && cadena.length() > 0) {
            return (caracter + cadena.toUpperCase().trim() + caracter).trim();
        }
        return null;
    }

    /**
     * Evalua una expresión regular o patron definido.
     *
     * @param cadena
     * @param patron
     * @return boolean
     *
     */
    public static boolean patternMatcher(final String cadena, final String patron) {
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(cadena);
        return matcher.matches();
    }

}
