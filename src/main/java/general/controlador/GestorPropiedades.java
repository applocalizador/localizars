/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package general.controlador;

import java.util.Properties;

/**
 *
 * @author carlosf
 */
public class GestorPropiedades {

    public Properties cargarPropiedades() throws Exception {
        Properties p = new Properties();
        try {
//            p.setProperty("urlbd", "jdbc:postgresql://127.0.0.1:5432/localizador");
//            p.setProperty("urlbd", "jdbc:postgresql://54dc96645973ca7c56000153-oxes.rhcloud.com:51801/cuponrs");
            p.setProperty("controlador", "org.postgresql.Driver");
            p.setProperty("usuario", "adminvqltilx");
            p.setProperty("clave", "2cbRV4EurrpR");
            
        } catch (Exception e) {
            throw e;
        }
        return p;
    }
}
