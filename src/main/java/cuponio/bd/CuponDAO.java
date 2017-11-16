/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuponio.bd;

import conexion.Consulta;
import configuracion.modelo.Usuario;
import cuponio.modelo.Categoria;
import cuponio.modelo.CentroComercial;
import cuponio.modelo.Cupon;
import cuponio.modelo.Tienda;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utilidades.modelo.UtilLog;

/**
 *
 * @author Julian
 */
public class CuponDAO {

    private final Connection conexion;

    public CuponDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public List<Cupon> cargarCuponesSalserin() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        List<Cupon> listaCupones = new ArrayList<>();
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT cod_centro_comercial, cod_tienda, cod_categoria, cod_cupon, nombre,"
                    + " descripcion, inicio, fin, imagen"
                    + " FROM cupon"
                    + " WHERE cod_centro_comercial='1' and cod_tienda='2' and cod_categoria=1 and cod_cupon=10"
            //                    + " WHERE fin::date>=current_date"
            );
            rs = consulta.ejecutar(sql);
            while (rs.next()) {
                Cupon c = new Cupon();
                c.setCodigo(rs.getInt("cod_cupon"));
                c.setNombre(rs.getString("nombre"));
                c.setImagen(rs.getBytes("imagen"));
                listaCupones.add(c);
            }
            return listaCupones;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public List<Cupon> cargarCupones() throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        List<Cupon> listaCupones = new ArrayList<>();
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "WITH cupones_tienda AS ("
                    + " SELECT cod_centro_comercial,cod_tienda,COUNT(1) as cantidad"
                    + " FROM cupon"
                    + " GROUP BY 1,2"
                    + " )"
                    + " SELECT cod_centro_comercial, cod_tienda, cod_categoria, cod_cupon, nombre,"
                    + " descripcion, inicio, fin, imagen, compartido, ct.cantidad"
                    + " FROM cupon c"
                    + " JOIN cupones_tienda ct USING (cod_centro_comercial,cod_tienda)"
                    + " WHERE current_date between inicio::DATE and fin::DATE"
                    + " ORDER BY (ct.cantidad*0.2 + compartido*0.8) desc"
            );
            rs = consulta.ejecutar(sql);
            while (rs.next()) {
                Cupon c = new Cupon();
                c.setTienda(new Tienda(new CentroComercial(rs.getString("cod_centro_comercial"), null), rs.getString("cod_tienda"), null));
                c.setCodigo(rs.getInt("cod_cupon"));
                c.setNombre(rs.getString("nombre"));
                c.setImagen(rs.getBytes("imagen"));
                c.setCategoria(new Categoria(rs.getInt("cod_categoria")));
                listaCupones.add(c);
            }
            return listaCupones;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Cupon cargarCupon(Cupon cupon, Usuario usuario) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT C.cod_centro_comercial, C.cod_tienda, C.cod_categoria, C.cod_cupon, C.nombre,"
                    + " C.descripcion, C.inicio, C.fin, C.imagen, T.nombre AS nombre_tienda, CU.cod_verificacion"
                    + " FROM cupon C"
                    + " JOIN tienda T USING (cod_tienda, cod_centro_comercial)"
                    + " LEFT JOIN cupon_usuario CU ON (CU.cod_centro_comercial=C.cod_centro_comercial AND CU.cod_cupon=C.cod_cupon "
                    + "  AND CU.cod_categoria=C.cod_categoria AND CU.cod_tienda=C.cod_tienda AND CU.cod_usuario=" + usuario.getCodigo() + ")"
                    + " WHERE C.cod_centro_comercial='" + cupon.getTienda().getCentroComercial().getCodigo() + "' "
                    + " AND C.cod_tienda='" + cupon.getTienda().getCodigo() + "'"
                    + " AND C.cod_categoria=" + cupon.getCategoria().getCodigo() + " AND C.cod_cupon=" + cupon.getCodigo()
            );

            rs = consulta.ejecutar(sql);
            if (rs.next()) {
                cupon.setTienda(new Tienda(new CentroComercial(rs.getString("cod_centro_comercial"), null), rs.getString("cod_tienda"), rs.getString("nombre_tienda")));
                cupon.setCodigo(rs.getInt("cod_cupon"));
                cupon.setNombre(rs.getString("nombre"));
                cupon.setImagen(rs.getBytes("imagen"));
                cupon.setDescripcion(rs.getString("descripcion"));
                cupon.setCategoria(new Categoria(rs.getInt("cod_categoria")));
                cupon.setCodVerificacion(rs.getString("cod_verificacion"));
            }
            return cupon;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public void asociarCuponUsuario(Usuario usuario, Cupon cupon) throws SQLException {
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "INSERT INTO cupon_usuario("
                    + " cod_centro_comercial, cod_cupon, cod_categoria, cod_tienda, cod_usuario, cod_verificacion)"
                    + " VALUES ('" + cupon.getTienda().getCentroComercial().getCodigo() + "', " + cupon.getCodigo()
                    + ", " + cupon.getCategoria().getCodigo() + ", '" + cupon.getTienda().getCodigo() + "', " + usuario.getCodigo()
                    + ",'" + cupon.getCodVerificacion() + "')"
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public List<Cupon> cargarCuponesUsuario(Usuario usuario) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        List<Cupon> listaCuponesUsuario = new ArrayList<>();
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT "
                    + " C.cod_centro_comercial, C.cod_tienda, C.cod_categoria, C.cod_cupon, C.nombre,"
                    + " C.descripcion, C.inicio, C.fin, C.imagen"
                    + " FROM"
                    + " public.cupon_usuario CU"
                    + " JOIN public.cupon C ON (CU.cod_centro_comercial = C.cod_centro_comercial AND"
                    + " CU.cod_tienda = C.cod_tienda AND CU.cod_cupon = C.cod_cupon AND CU.cod_categoria = C.cod_categoria)"
                    + " WHERE CU.cod_usuario=" + usuario.getCodigo()
                    + " ORDER BY C.cod_cupon DESC"
            );
            rs = consulta.ejecutar(sql);
            while (rs.next()) {
                Cupon cupon = new Cupon();
                cupon.setTienda(new Tienda(new CentroComercial(rs.getString("cod_centro_comercial"), null), rs.getString("cod_tienda"), null));
                cupon.setCodigo(rs.getInt("cod_cupon"));
                cupon.setNombre(rs.getString("nombre"));
                cupon.setImagen(rs.getBytes("imagen"));
                cupon.setDescripcion(rs.getString("descripcion"));
                cupon.setCategoria(new Categoria(rs.getInt("cod_categoria")));
                listaCuponesUsuario.add(cupon);
            }
            return listaCuponesUsuario;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public boolean existeCuponUsuario(Usuario usuario, Cupon cupon) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT COUNT(1)>0 AS existe"
                    + " FROM cupon_usuario"
                    + " WHERE cod_centro_comercial='" + cupon.getTienda().getCentroComercial().getCodigo() + "' AND cod_cupon=" + cupon.getCodigo()
                    + " AND cod_categoria=" + cupon.getCategoria().getCodigo() + " AND cod_tienda='" + cupon.getTienda().getCodigo() + "' AND cod_usuario=" + usuario.getCodigo()
            );
            rs = consulta.ejecutar(sql);
            rs.next();
            return rs.getBoolean("existe");
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public void registrarCompartido(Cupon cupon) throws SQLException {
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "UPDATE cupon"
                    + " SET compartido=compartido+1"
                    + " WHERE"
                    + " cod_centro_comercial='" + cupon.getTienda().getCentroComercial().getCodigo() + "' AND cod_tienda='" + cupon.getTienda().getCodigo()
                    + "' AND cod_categoria=" + cupon.getCategoria().getCodigo() + " AND cod_cupon=" + cupon.getCodigo()
            );
            consulta.actualizar(sql);
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

}
