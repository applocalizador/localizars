/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades.modelo;

import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Julian
 */
public class UtilRest {

    /**
     * Convierte un objeto en Json.
     *
     * @param object
     * @return
     *
     */
    public static String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static String encode(byte[] imagen) {
        return new BASE64Encoder().encode(imagen);
    }

}
