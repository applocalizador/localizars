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
            p.setProperty("urlbd", "jdbc:postgresql://jws-app-postgresql.localizars.svc:5432/localizador");
//            p.setProperty("urlbd", "jdbc:postgresql://localhost:5432/localizador");
            p.setProperty("controlador", "org.postgresql.Driver");
            p.setProperty("usuario", "userSCW");
            p.setProperty("clave", "AoC5WDCAFiCXqGeT");
            
        } catch (Exception e) {
            throw e;
        }
        return p;
    }
}
