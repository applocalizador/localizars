/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuponio.controlador;

import configuracion.modelo.Usuario;
import cuponio.bd.CuponDAO;
import cuponio.modelo.Cupon;
import general.controlador.Gestor;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Julian
 */
public class GestorCupon extends Gestor {

    public GestorCupon() throws Exception {
        super();
    }

    public List<Cupon> cargarCuponesSalserin() throws Exception {
        try {
            this.abrirConexion();
            CuponDAO cuponDAO = new CuponDAO(conexion);
            return cuponDAO.cargarCuponesSalserin();
        } finally {
            this.cerrarConexion();
        }
    }

    public List<Cupon> cargarCupones() throws Exception {
        try {
            this.abrirConexion();
            CuponDAO cuponDAO = new CuponDAO(conexion);
            return cuponDAO.cargarCupones();
        } finally {
            this.cerrarConexion();
        }
    }

    public Cupon cargarCupon(Cupon cupon, Usuario usuario) throws Exception {
        try {
            this.abrirConexion();
            CuponDAO cuponDAO = new CuponDAO(conexion);
            return cuponDAO.cargarCupon(cupon, usuario);
        } finally {
            this.cerrarConexion();
        }
    }

    public void asociarCuponUsuario(Usuario usuario, Cupon cupon) throws Exception {
        try {
            this.abrirConexion();
            CuponDAO cuponDAO = new CuponDAO(conexion);
            cuponDAO.asociarCuponUsuario(usuario, cupon);
        } finally {
            this.cerrarConexion();
        }
    }

    public List<Cupon> cargarCuponesUsuario(Usuario usuario) throws Exception {
        try {
            this.abrirConexion();
            CuponDAO cuponDAO = new CuponDAO(conexion);
            return cuponDAO.cargarCuponesUsuario(usuario);
        } finally {
            this.cerrarConexion();
        }
    }

    public boolean existeCuponUsuario(Usuario usuario, Cupon cupon) throws Exception {
        try {
            this.abrirConexion();
            CuponDAO cuponDAO = new CuponDAO(conexion);
            return cuponDAO.existeCuponUsuario(usuario, cupon);
        } finally {
            this.cerrarConexion();
        }
    }

    public void registrarCompartido(Cupon cupon) throws Exception {
        try {
            this.abrirConexion();
            CuponDAO cuponDAO = new CuponDAO(conexion);
            cuponDAO.registrarCompartido(cupon);
        } finally {
            this.cerrarConexion();
        }
    }

    public String generarCodigoVerifiacion(int longitud) {
        String cadenaAleatoria = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while (i < longitud) {
            char c = (char) r.nextInt(255);
            if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
                cadenaAleatoria += c;
                i++;
            }
        }
        return cadenaAleatoria;
    }
}
