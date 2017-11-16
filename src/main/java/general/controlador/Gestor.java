/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package general.controlador;


import conexion.BaseDatos;
import general.modelo.Propiedades;
import java.sql.Connection;

/**
 *
 * @author carlosp
 */
public abstract class Gestor {

    private BaseDatos bd;
    public Connection conexion;

    public Gestor() throws Exception {
        bd = new BaseDatos(Propiedades.getInstancia().getPropiedades());
    }

    public void abrirConexion() throws Exception {
        conexion = bd.getConexion();
    }

    public void cerrarConexion() throws Exception {
        if (conexion != null) {
            conexion.close();
        }
    }

    public void inicioTransaccion() throws Exception {
        bd.ejecutar(conexion, " BEGIN ");
    }

    public void finTransaccion() throws Exception {
        bd.ejecutar(conexion, " COMMIT ");
    }

    public void devolverTransaccion() throws Exception {
        bd.ejecutar(conexion, " ROLLBACK ");
    }

    /**
     * @return the bd
     */
    public BaseDatos getBd() {
        return bd;
    }

    /**
     * @param bd the bd to set
     */
    public void setBd(BaseDatos bd) {
        this.bd = bd;
    }
}
