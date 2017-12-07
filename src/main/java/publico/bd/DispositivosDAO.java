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
import publico.modelo.Dispositivos;
import publico.modelo.DispositivosPK;
import publico.modelo.LocalizacionesDispositivo;
import publico.modelo.LocalizacionesDispositivoPK;
import publico.modelo.Usuarios;

/**
 *
 * @author juliano
 */
public class DispositivosDAO {

    private Connection conexion;

    public DispositivosDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public Collection<? extends Dispositivos> cargarDispositivosUsuario(String correo) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            Collection<Dispositivos> dispositivoses = new ArrayList<>();
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "WITH tmp_localizaciones AS ("
                    + " SELECT correo, cod_dispositivo, MAX(cod_localizacion) AS cod_localizacion, MAX(fecha) AS fecha"
                    + " FROM public.localizaciones_dispositivo"
                    + " WHERE correo='" + correo + "' AND fecha>=current_Date-90"
                    + " GROUP BY correo,cod_dispositivo"
                    + " )"
                    + " SELECT D.correo, D.cod_dispositivo, D.identificador, D.fecha, LD.cod_localizacion, LD.fecha, LD.latitude, LD.longitud"
                    + " FROM public.dispositivos D"
                    + " LEFT JOIN tmp_localizaciones TL ON (TL.correo=D.correo AND TL.cod_dispositivo=D.cod_dispositivo)"
                    + " LEFT JOIN public.localizaciones_dispositivo LD ON (LD.correo=TL.correo AND LD.cod_dispositivo=TL.cod_dispositivo AND LD.cod_localizacion=TL.cod_localizacion)"
                    + " WHERE D.correo='" + correo + "'"
            );
            rs = consulta.ejecutar(sql);
            while (rs.next()) {
                Dispositivos dispositivos = new Dispositivos(new DispositivosPK(rs.getString("correo"), rs.getInt("cod_dispositivo")), rs.getString("identificador"), rs.getDate("fecha"),
                        new Usuarios(correo), new LocalizacionesDispositivo(new LocalizacionesDispositivoPK(rs.getString("correo"),
                                rs.getInt("cod_dispositivo"), rs.getInt("cod_localizacion")), rs.getDate("fecha"), rs.getDouble("latitude"), rs.getDouble("longitud")));
                dispositivoses.add(dispositivos);
            }
            return dispositivoses;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Collection<LocalizacionesDispositivo> cargarLocalizacionesDispositivo(String correo, int codDispositivo) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            Collection<LocalizacionesDispositivo> localizacionesDispositivos = new ArrayList<>();
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT correo, cod_dispositivo, cod_localizacion, fecha, latitude, longitud"
                    + " FROM public.localizaciones_dispositivo"
                    + " WHERE correo='" + correo + "' AND cod_dispositivo=" + codDispositivo
            );
            rs = consulta.ejecutar(sql);
            while (rs.next()) {
                LocalizacionesDispositivo ld = new LocalizacionesDispositivo(new LocalizacionesDispositivoPK(rs.getString("correo"),
                        rs.getInt("cod_dispositivo"), rs.getInt("cod_localizacion")), rs.getDate("fecha"), rs.getDouble("latitude"), rs.getDouble("longitud"));
                localizacionesDispositivos.add(ld);
            }
            return localizacionesDispositivos;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Collection<? extends Dispositivos> cargarDispositivosGrupoUsuario(String correo, int codGrupo) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            Collection<Dispositivos> dispositivoses = new ArrayList<>();
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "WITH tmp_localizaciones AS ("
                    + " SELECT correo, cod_dispositivo, MAX(cod_localizacion) AS cod_localizacion, MAX(fecha) AS fecha"
                    + " FROM public.localizaciones_dispositivo"
                    + " WHERE correo IN (SELECT DISTINCT correo FROM public.grupos_dispositivos WHERE correo_administrador='" + correo + "' AND cod_grupo=" + codGrupo + " AND aprobado) AND fecha>=current_Date-90"
                    + " GROUP BY correo,cod_dispositivo"
                    + " )"
                    + " SELECT D.correo, D.cod_dispositivo, D.identificador, D.fecha, LD.cod_localizacion, LD.fecha, LD.latitude, LD.longitud"
                    + " FROM public.dispositivos D"
                    + " JOIN grupos_dispositivos GD on (GD.correo=D.correo AND GD.cod_dispositivo=D.cod_dispositivo)"
                    + " LEFT JOIN tmp_localizaciones TL ON (TL.correo=D.correo AND TL.cod_dispositivo=D.cod_dispositivo)"
                    + " LEFT JOIN public.localizaciones_dispositivo LD ON (LD.correo=TL.correo AND LD.cod_dispositivo=TL.cod_dispositivo AND LD.cod_localizacion=TL.cod_localizacion)"
                    + " WHERE GD.cod_grupo=" + codGrupo + " AND GD.correo_administrador='" + correo + "'"
            );
            rs = consulta.ejecutar(sql);
            while (rs.next()) {
                Dispositivos dispositivos = new Dispositivos(new DispositivosPK(rs.getString("correo"), rs.getInt("cod_dispositivo")), rs.getString("identificador"), rs.getDate("fecha"),
                        new Usuarios(correo), new LocalizacionesDispositivo(new LocalizacionesDispositivoPK(rs.getString("correo"),
                                rs.getInt("cod_dispositivo"), rs.getInt("cod_localizacion")), rs.getDate("fecha"), rs.getDouble("latitude"), rs.getDouble("longitud")));
                dispositivoses.add(dispositivos);
            }
            return dispositivoses;
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
