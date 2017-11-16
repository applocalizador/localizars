/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general.bd;

import general.modelo.Establecimiento;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import conexion.Consulta;


/**
 *
 * @author Julian
 */
public class EstablecimientoDAO {

    private Connection conexion;

    public EstablecimientoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public Establecimiento cargarEstablecimiento(int codigo) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            Establecimiento establecimiento = new Establecimiento();
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT codigo_establecimiento, codigo_municipio, nombre, nit, direccion, "
                    + " telefono, correo, tipo_establecimiento"
                    + " FROM establecimiento"
                    + " WHERE codigo_establecimiento=" + codigo
            );
            rs = consulta.ejecutar(sql);
            while (rs.next()) {
                establecimiento.setCodigo(rs.getInt("codigo_establecimiento"));
                establecimiento.setNombre(rs.getString("nombre"));
                establecimiento.setTipoEstablecimiento(rs.getString("tipo_establecimiento"));
            }
            return establecimiento;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public String cargarPrefijo() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT codigo_establecimiento, cod_prefijo, fecha_inicial, fecha_final"
                    + " FROM prefijos"
                    + " WHERE current_date between fecha_inicial AND fecha_final"
            );
            rs = consulta.ejecutar(sql);
            if (rs.next()) {
                return rs.getString("cod_prefijo");
            }
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public List<?> cargarListaEstablecimientos() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        List<Establecimiento> listaEstablecimientos = new ArrayList<Establecimiento>();
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT codigo_establecimiento, codigo_municipio, nombre, nit, direccion, "
                    + " telefono, correo"
                    + " FROM establecimiento"
            );
            rs = consulta.ejecutar(sql);
            while (rs.next()) {
                listaEstablecimientos.add(new Establecimiento(rs.getInt("codigo_establecimiento"), rs.getString("nombre")));
            }
            return listaEstablecimientos;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public List<?> cargarListaEstablecimientosUsuario(String documentoUsuario) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        List<Establecimiento> listaEstablecimientos = new ArrayList<Establecimiento>();
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT E.codigo_establecimiento, E.nombre"
                    + " FROM rel_usuarios_establecimiento"
                    + " JOIN establecimiento E USING (codigo_establecimiento)"
                    + " WHERE documento_usuario='" + documentoUsuario + "'"
            );
            rs = consulta.ejecutar(sql);
            while (rs.next()) {
                listaEstablecimientos.add(new Establecimiento(rs.getInt("codigo_establecimiento"), rs.getString("nombre")));
            }
            return listaEstablecimientos;
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
