/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publico.bd;

import conexion.Consulta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import publico.modelo.LocalizacionesDispositivo;

/**
 *
 * @author juliano
 */
public class LocalizacionesDispositivoDAO {

    private Connection conexion;

    public LocalizacionesDispositivoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarLocalizacionesDispositivo(LocalizacionesDispositivo ld) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {

            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "INSERT INTO public.localizaciones_dispositivo("
                    + " correo, cod_dispositivo, cod_localizacion, fecha, latitude, longitud)"
                    + " VALUES ('" + ld.getLocalizacionesDispositivoPK().getCorreo() + "', " + ld.getLocalizacionesDispositivoPK().getCodDispositivo() + ", DEFAULT, NOW(),"
                    + " '" + ld.getLatitude() + "', '" + ld.getLongitud() + "');"
            );
            consulta.actualizar(sql);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

}
