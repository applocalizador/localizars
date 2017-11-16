package conexion;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import utilidades.modelo.UtilLog;

/**
 *
 * @author waltero
 */
public class BaseDatos {

    private Properties propiedades;

    public BaseDatos(Properties propiedades) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        String controlador = propiedades.getProperty("controlador");
        Class.forName(controlador).newInstance();
        this.propiedades = propiedades;
    }

    public Connection getConexion() throws SQLException, UnknownHostException {
//        InetAddress ip;
//        try {
//            ip = InetAddress.getLocalHost();
//            System.out.println("Current IP address : " + ip.getHostAddress());
//        } catch (Exception e) {
//        }
//        System.out.println(propiedades.getProperty("urlbd"));
        return java.sql.DriverManager.getConnection(propiedades.getProperty("urlbd"), propiedades.getProperty("usuario"), propiedades.getProperty("clave"));
    }

    public void ejecutar(Connection conexion, String sql) throws SQLException {
        Statement consulta = conexion.createStatement();
        try {
            consulta.executeUpdate(sql);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (consulta != null) {
                consulta.close();
            }
        }
    }
}
