/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades.modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author juliano
 */
public class UtilCorreo {

    private static final String patronCorreo = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * valida un correo electronico.
     *
     * @param correo
     * @return
     */
    public static boolean validarCorreo(String correo) {
        if (correo == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(patronCorreo);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();

    }
}
