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
import java.util.ArrayList;
import java.util.Collection;
import publico.modelo.Grupos;
import publico.modelo.GruposPK;
import publico.modelo.LocalizacionesDispositivo;
import publico.modelo.LocalizacionesDispositivoPK;


/**
 *
 * @author Walter Osorio
 */
public class GrupoDAO {
    
    private Connection conexion;

    public GrupoDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    
    
     public Collection<Grupos> cargarGrupos(String correo) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            Collection<Grupos> listaGrupos = new ArrayList<>();
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT correo, cod_grupo, nombre " +
                    "  FROM grupos  WHERE correo='" +correo+"'");
            
        
            rs = consulta.ejecutar(sql);
            while (rs.next()) {
                Grupos grupo = new Grupos(new GruposPK(rs.getString("correo"), rs.getInt("cod_grupo")));
                grupo.setNombre(rs.getString("nombre"));
                listaGrupos.add(grupo);
            }
            return listaGrupos;
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
