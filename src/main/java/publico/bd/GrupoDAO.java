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
import publico.modelo.GruposDispositivos;
import publico.modelo.GruposPK;

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
                    "SELECT correo, cod_grupo, nombre "
                    + "  FROM grupos  WHERE correo='" + correo + "'");

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

    public Grupos insertarGrupos(Grupos g) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "INSERT INTO public.grupos("
                    + " correo, cod_grupo, nombre, fecha_creacion)"
                    + " VALUES ('" + g.getGruposPK().getCorreo() + "', DEFAULT, '" + g.getNombre() + "', NOW()) RETURNING cod_grupo;"
            );
            rs = consulta.ejecutar(sql);
            rs.next();
            g.getGruposPK().setCodGrupo(rs.getInt("cod_grupo"));
            return g;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public void insertarGruposDispositivos(GruposDispositivos gd) throws SQLException {

        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "INSERT INTO public.grupos_dispositivos("
                    + " correo_administrador, cod_grupo, correo, cod_dispositivo, fecha, "
                    + " fecha_aprobacion, aprobado)"
                    + " VALUES ('" + gd.getGruposDispositivosPK().getCorreoAdministrador() + "', " + gd.getGruposDispositivosPK().getCodGrupo()
                    + " , '" + gd.getGruposDispositivosPK().getCorreo() + "', " + gd.getGruposDispositivosPK().getCodDispositivo() + ", NOW(), "
                    + " NOW(), " + gd.getAprobado() + ")"
            );
            consulta.actualizar(sql);

        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }
    
      public String guardarGrupo(String nombreGrupo, String correo) throws SQLException {
        Consulta consulta = null;
        ResultSet rs = null;
        String respuesta="";
        try {
            consulta = new Consulta(this.conexion);
            
            StringBuilder sql = new StringBuilder(
                    "SELECT correo, cod_grupo, nombre " +
                    "  FROM grupos  WHERE upper(nombre) ='"+nombreGrupo.trim().toUpperCase()+"' and  correo='" +correo.trim()+"'");
            
        
            rs = consulta.ejecutar(sql);
            if (rs.next()) {
                
                respuesta="EXISTE";
            }else{
            StringBuilder sql2 = new StringBuilder( "INSERT INTO grupos("
                    + "correo,  nombre, fecha_creacion)"
                    + " VALUES ('" + correo + "', '" + nombreGrupo+"',current_timestamp)");
                
             consulta.actualizar(sql2);
              respuesta="OK";
            
            }
            
            
            
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
        
       return respuesta;
    }

}
