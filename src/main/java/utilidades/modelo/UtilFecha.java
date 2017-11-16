/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author juliano
 */
public class UtilFecha {

    public static String PATRON_FECHA_YYYYMMDD = "yyyy-MM-dd";
    public static String PATRON_FECHA_YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
    public static String PATRON_FECHA_YYYYMMDD_HH_MM = "yyyy-MM-dd_HH_mm";
    public static String CARACTER_COMILLA = "'";

    /**
     * Calcula la diferencia en días entre dos fechas.
     *
     * @param fechaInicial
     * @param fechaFinal
     * @return long
     *
     */
    public static long diferenciaFechaDias(final Date fechaInicial, final Date fechaFinal) {
        long milisegundosPorDia = 24 * 60 * 60 * 1000;
        long diferencia = (fechaInicial.getTime() - fechaFinal.getTime()) / milisegundosPorDia;
        return diferencia;
    }

    /**
     * Retorna el formato de una fecha ingresada.
     *
     * @param fecha
     * @param valorDefecto
     * @param patronFecha .. "yyyy-MM-dd"
     * @return
     *
     */
    public static String formatoFecha(Date fecha, String valorDefecto, String patronFecha) {
        if (fecha == null) {
            return null;
        }
        SimpleDateFormat formato = new SimpleDateFormat(patronFecha);
        return formato.format(fecha);
    }

    /**
     * Retorna el formato de una fecha ingresada, antecedido y precedido por un
     * caracter especial, usado normalmente para realizar comandos INSERT en
     * bases de datos.
     *
     * @param fecha
     * @param valorDefecto
     * @param patronFecha .. "yyyy-MM-dd"
     * @param caracter .. "'"
     * @return
     *
     */
    public static String formatoFecha(Date fecha, String valorDefecto, String patronFecha, String caracter) {
        if (fecha == null) {
            return null;
        }
        SimpleDateFormat formato = new SimpleDateFormat(patronFecha);
        return caracter + formato.format(fecha) + caracter;
    }

    /**
     * Retorna la fecha de una cadena enviada.
     *
     * @param fecha
     * @param patronFecha .. "yyyy-MM-dd"
     * @return
     * @throws java.text.ParseException
     *
     */
    public static Date fechaDeCadena(String fecha, String patronFecha) throws ParseException {
        if (fecha == null) {
            return null;
        }
        SimpleDateFormat formato = new SimpleDateFormat(patronFecha);
        return formato.parse(fecha);
    }

}
