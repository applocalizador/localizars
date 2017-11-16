/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author waltero
 */
public class Consulta {

    private Statement query;

    public Consulta(Connection conexion) throws SQLException {
        query = conexion.createStatement();
    }

    public ResultSet ejecutar(String sql) throws SQLException {
        return query.executeQuery(sql);
    }

    public ResultSet ejecutar(StringBuilder sql) throws SQLException {
        return query.executeQuery(sql.toString());
    }

    public int actualizar(String sql) throws SQLException {
        return query.executeUpdate(sql);
    }

    public int actualizar(StringBuilder sql) throws SQLException {
        return query.executeUpdate(sql.toString());
    }

    public void desconectar() {
        if (query != null) {
            try {
                query.close();
            } catch (SQLException e) {
            }
            query = null;
        }
    }
}
