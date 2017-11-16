/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades.modelo;

import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juliano
 */
public class UtilLog {

    public static Throwable TW_VALIDACION = new Throwable("validacion");
    public static Throwable TW_RESTRICCION = new Throwable("restriccion");

    public static void generarLog(final Class c, final Exception ex) {
        String mensaje = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL).format(GregorianCalendar.getInstance().getTime());
        Logger.getLogger(c.getName()).log(Level.SEVERE, mensaje, ex);
    }

    public static boolean causaControlada(final Exception ex) {
        if (ex == null || ex.getCause() == null) {
            return false;
        }
        return ex.getCause().equals(TW_VALIDACION) || ex.getCause().equals(TW_RESTRICCION);
    }
}
