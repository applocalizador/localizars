package configuracion.bd;

import conexion.Consulta;
import configuracion.modelo.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import utilidades.modelo.UtilFecha;
import utilidades.modelo.UtilLog;

public class UsuarioDAO {

    private Connection conexion;

    public UsuarioDAO() {
    }

    public UsuarioDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public Usuario cargarUsuario(String documento) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            Usuario usuario = new Usuario();
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT cod_usuario, correo, nombre, apellido, fecha_nacimiento"
                    + " FROM usuario WHERE cod_usuario='" + documento + "'"
            );
            rs = consulta.ejecutar(sql);
            if (rs.next()) {
                usuario.setCodigo(documento);
                usuario.setCorreo(rs.getString("correo"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setSuccess(Boolean.TRUE);
            } else {
                usuario.setCodigo(null);
            }
            return usuario;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public boolean existeUsuario(Usuario usuario) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        boolean existe = false;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT COUNT(1)>0 AS existe"
                    + " FROM usuarios"
                    + " WHERE correo='" + usuario.getCorreo().toLowerCase().trim() + "' OR TRIM(REGEXP_REPLACE(correo, '@.*', '', 'g'))::varchar='" + usuario.getCorreo().toLowerCase().trim() + "'"
            );
            rs = consulta.ejecutar(sql);
            rs.next();
            existe = rs.getBoolean("existe");
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
        return existe;
    }

    public Usuario insertarUsuario(Usuario usuario) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "INSERT INTO usuario("
                    + " nombre, email, clave)"
                    + " VALUES ('" + usuario.getNombre() + " " + usuario.getApellido() + "', '" + usuario.getCorreo() + "', null) RETURNING cod_usuario"
            );
            rs = consulta.ejecutar(sql);
            rs.next();
            usuario.setCodigo(rs.getString("cod_usuario"));
            return usuario;
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Usuario cargarUsuario(Usuario usuario) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT correo, cod_documento, documento_usuario, nombre, apellido, clave, activo, fecha_registro"
                    + " FROM usuarios"
                    + " WHERE (correo='" + usuario.getCorreo().toLowerCase().trim() + "' OR TRIM(REGEXP_REPLACE(correo, '@.*', '', 'g'))::varchar='" + usuario.getCorreo().toLowerCase().trim() + "')"
                    + " AND clave=md5('" + usuario.getClave().trim() + "')"
            );
            rs = consulta.ejecutar(sql);
            if (rs.next()) {
                usuario.setCodigo(rs.getString("documento_usuario"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setClave(rs.getString("clave"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setSuccess(Boolean.TRUE);
            }
            return usuario;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Usuario actualizarUsuario(Usuario usuario) throws SQLException {
        Consulta consulta = null;
        ResultSet rs = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "UPDATE usuario"
                    + " SET  nombre='" + usuario.getNombre() + " " + usuario.getApellido() + "'"
                    + " WHERE email='" + usuario.getCorreo() + "' RETURNING cod_usuario"
            );
            rs = consulta.ejecutar(sql);
            if (rs.next()) {
                usuario.setCodigo(rs.getString("cod_usuario"));
            }
            return usuario;
        } finally {
            if (consulta != null) {
                consulta.desconectar();
            }
        }
    }

    public Usuario cargarUsuarioCorreo(Usuario usuario) throws SQLException {
        ResultSet rs = null;
        Consulta consulta = null;
        try {
            consulta = new Consulta(this.conexion);
            StringBuilder sql = new StringBuilder(
                    "SELECT cod_usuario, nombre, email, clave"
                    + " FROM usuario"
                    + " WHERE email='" + usuario.getCorreo() + "'"
            );
            rs = consulta.ejecutar(sql);
            if (rs.next()) {
                usuario.setCodigo(rs.getString("cod_usuario"));
            } else {
                throw new SQLException("Ingresa para obtener nuevos cupones", UtilLog.TW_VALIDACION);
            }
            return usuario;
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
