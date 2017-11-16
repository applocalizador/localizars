/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general.controlador;

import general.bd.EstablecimientoDAO;
import general.modelo.Establecimiento;
import java.util.List;

/**
 *
 * @author Julian
 */
public class GestorEstablecimiento extends Gestor {

    public GestorEstablecimiento() throws Exception {
        super();
    }

    public Establecimiento cargarEstablecimiento(int codigo) throws Exception {
        try {
            this.abrirConexion();
            Establecimiento establecimiento;
            EstablecimientoDAO establecimientoDAO = new EstablecimientoDAO(conexion);
            establecimiento = establecimientoDAO.cargarEstablecimiento(codigo);
            establecimiento.setPrefijo(establecimientoDAO.cargarPrefijo());
            return establecimiento;
        } finally {
            this.cerrarConexion();
        }
    }

    public List<?> cargarListaEstablecimientos() throws Exception {
        try {
            this.abrirConexion();
            EstablecimientoDAO establecimientoDAO = new EstablecimientoDAO(conexion);
            return establecimientoDAO.cargarListaEstablecimientos();
        } finally {
            this.cerrarConexion();
        }
    }

    public List<?> cargarListaEstablecimientosUsuario(String documentoUsuario) throws Exception {
        try {
            this.abrirConexion();
            EstablecimientoDAO establecimientoDAO = new EstablecimientoDAO(conexion);
            return establecimientoDAO.cargarListaEstablecimientosUsuario(documentoUsuario);
        } finally {
            this.cerrarConexion();
        }
    }

}
