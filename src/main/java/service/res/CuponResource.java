/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.res;

import com.google.gson.Gson;
import configuracion.controlador.GestorUsuario;
import configuracion.modelo.Usuario;
import cuponio.controlador.GestorCupon;
import cuponio.modelo.Categoria;
import cuponio.modelo.CentroComercial;
import cuponio.modelo.Cupon;
import cuponio.modelo.Tienda;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.LinkedHashMap;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import sun.misc.BASE64Encoder;
import utilidades.modelo.UtilLog;
import utilidades.modelo.UtilRest;
import utilidades.modelo.UtilidadesGeneral;

/**
 * REST Web Service
 *
 * @author Julian
 */
@Path("cupon")
public class CuponResource {

    private static final int PRETTY_PRINT_INDENT_FACTOR = 4;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public CuponResource() {
    }

    /**
     * Retrieves representation of an instance of service.res.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        String xml = //"<?xml version='1.0' encoding='UTF-8' ?>"
                "<desarrollador>"
                + "<items>"
                + "<id>" + "1" + "</id>"
                + "<apels>" + "OSORIO" + "</apels>"
                + "<nombs>" + "JULIAN" + "</nombs>"
                + "<descripcion>" + "prueba correcta servicio rest" + "</descripcion>"
                + "</items>"
                + "</desarrollador>";

        JSONObject soapDatainJsonObject = XML.toJSONObject(xml);
        return soapDatainJsonObject.toString(PRETTY_PRINT_INDENT_FACTOR);
    }

    /**
     * Devuelve una lista de cupones vigentes.
     *
     * @return
     * @throws java.io.IOException
     */
    @GET
    @Path("/get/cupones")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCuponesSalserin() throws IOException {
        try {
            JSONArray cupones = new JSONArray();
            GestorCupon gestorCupon = new GestorCupon();
            Gson gson = new Gson();
            List<Cupon> listaCupones = gestorCupon.cargarCuponesSalserin();
            for (Cupon c : listaCupones) {
                c.setImagenBase64(new BASE64Encoder().encode(c.getImagen()));
                c.setImagen(null);
                cupones.put(gson.toJson(c));
            }
            return cupones.toString();
        } catch (Exception ex) {
            if (!UtilLog.causaControlada(ex)) {
                UtilLog.generarLog(Cupon.class, ex);
            }
        }
        return null;
    }

    /**
     * Devuelve una lista de cupones vigentes.
     *
     * @return
     * @throws java.io.IOException
     */
    @GET
    @Path("/get/listaCupones")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCupones() throws IOException {
        try {
            JSONArray cupones = new JSONArray();
            GestorCupon gestorCupon = new GestorCupon();
            Gson gson = new Gson();
            List<Cupon> listaCupones = gestorCupon.cargarCupones();
            for (Cupon c : listaCupones) {
                c.setImagenBase64(new BASE64Encoder().encode(c.getImagen()));
                c.setImagen(null);
                cupones.put(gson.toJson(c));
            }
            return cupones.toString();
        } catch (Exception ex) {
            if (!UtilLog.causaControlada(ex)) {
                UtilLog.generarLog(Cupon.class, ex);
            }
        }
        return null;
    }

    /**
     * Devuelve una lista de cupones asignados a un usuario.
     *
     * @param objUsuario
     * @return
     * @throws java.io.IOException
     */
    @POST
    @Path("/get/listaCuponesUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCuponesUsuario(Object objUsuario) throws IOException {
        try {
            JSONArray cuponesUsuario = new JSONArray();
            Usuario usuario = new Usuario();
            usuario = (Usuario) UtilidadesGeneral.obtenerObjetoMapa(objUsuario, usuario);

            GestorCupon gestorCupon = new GestorCupon();
            GestorUsuario gestorUsuario = new GestorUsuario();
            usuario = gestorUsuario.cargarUsuarioCorreo(usuario);
            List<Cupon> listaCuponesUsuario = gestorCupon.cargarCuponesUsuario(usuario);
            for (Cupon c : listaCuponesUsuario) {
                c.setImagenBase64(UtilRest.encode(c.getImagen()));
                c.setImagen(null);
                cuponesUsuario.put(UtilRest.toJson(c));
            }
            return cuponesUsuario.toString();
        } catch (Exception ex) {
            if (!UtilLog.causaControlada(ex)) {
                UtilLog.generarLog(Cupon.class, ex);
            }
        }
        return null;
    }

    /**
     * Retrieves representation of an instance of service.res.GenericResource
     *
     * @param arrayObjects
     * @return an instance of java.lang.String
     */
    @POST
    @Path("/get/cupon")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCupon(Object arrayObjects) {
        try {
            Object objTienda, objCentroComercial, objCupon, objCategoria, objUsuario;
            GestorCupon gestorCupon = new GestorCupon();
            GestorUsuario gestorUsuario = new GestorUsuario();

            objTienda = (Object) ((LinkedHashMap) arrayObjects).get("tienda");
            objCentroComercial = (Object) ((LinkedHashMap) arrayObjects).get("centroComercial");
            objCupon = (Object) ((LinkedHashMap) arrayObjects).get("cupon");
            objCategoria = (Object) ((LinkedHashMap) arrayObjects).get("categoria");
            objUsuario = (Object) ((LinkedHashMap) arrayObjects).get("usuario");

            Tienda tienda = new Tienda();
            CentroComercial centroComercial = new CentroComercial();
            Cupon cupon = new Cupon();
            Categoria categoria = new Categoria();
            Usuario usuario = new Usuario();

            tienda = (Tienda) UtilidadesGeneral.obtenerObjetoMapa(objTienda, tienda);
            centroComercial = (CentroComercial) UtilidadesGeneral.obtenerObjetoMapa(objCentroComercial, centroComercial);
            cupon = (Cupon) UtilidadesGeneral.obtenerObjetoMapa(objCupon, cupon);
            categoria = (Categoria) UtilidadesGeneral.obtenerObjetoMapa(objCategoria, categoria);
            usuario = (Usuario) UtilidadesGeneral.obtenerObjetoMapa(objUsuario, usuario);
            if (usuario.getCorreo() != null) {
                usuario = gestorUsuario.cargarUsuarioCorreo(usuario);
            } else {
                usuario.setCodigo("0");
            }

            tienda.setCentroComercial(centroComercial);
            cupon.setTienda(tienda);
            cupon.setCategoria(categoria);

            cupon = gestorCupon.cargarCupon(cupon, usuario);

            cupon.setImagenBase64(new BASE64Encoder().encode(cupon.getImagen()));
            cupon.setImagen(null);

            return UtilRest.toJson(cupon);
        } catch (Exception ex) {
            Logger.getLogger(CuponResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Crea la relación entre un cupon y un usuario.
     *
     * @param arrayObjects
     * @throws java.lang.Exception
     *
     */
    @POST
    @Path("/post/cuponUsuario")
    @Consumes(MediaType.APPLICATION_JSON)
    public void postCuponUsuario(Object arrayObjects) throws Exception {
        try {
            Object objTienda, objCentroComercial, objCupon, objCategoria, objUsuario;
            GestorCupon gestorCupon = new GestorCupon();
            GestorUsuario gestorUsuario = new GestorUsuario();

            objTienda = (Object) ((LinkedHashMap) arrayObjects).get("tienda");
            objCentroComercial = (Object) ((LinkedHashMap) arrayObjects).get("centroComercial");
            objCupon = (Object) ((LinkedHashMap) arrayObjects).get("cupon");
            objCategoria = (Object) ((LinkedHashMap) arrayObjects).get("categoria");
            objUsuario = (Object) ((LinkedHashMap) arrayObjects).get("usuario");

            Tienda tienda = new Tienda();
            CentroComercial centroComercial = new CentroComercial();
            Cupon cupon = new Cupon();
            Categoria categortia = new Categoria();
            Usuario usuario = new Usuario();

            tienda = (Tienda) UtilidadesGeneral.obtenerObjetoMapa(objTienda, tienda);
            centroComercial = (CentroComercial) UtilidadesGeneral.obtenerObjetoMapa(objCentroComercial, centroComercial);
            cupon = (Cupon) UtilidadesGeneral.obtenerObjetoMapa(objCupon, cupon);
            categortia = (Categoria) UtilidadesGeneral.obtenerObjetoMapa(objCategoria, categortia);
            tienda.setCentroComercial(centroComercial);
            cupon.setTienda(tienda);
            cupon.setCategoria(categortia);

            usuario = (Usuario) UtilidadesGeneral.obtenerObjetoMapa(objUsuario, usuario);
            usuario = gestorUsuario.cargarUsuarioCorreo(usuario);

            if (gestorCupon.existeCuponUsuario(usuario, cupon)) {
                throw new Exception("El cupon ya esta en tu lista de regalos", UtilLog.TW_VALIDACION);
            }

            cupon.setCodVerificacion(gestorCupon.generarCodigoVerifiacion(Cupon.LONGITUD_CADENA_VERIFICACION));
            gestorCupon.asociarCuponUsuario(usuario, cupon);
            gestorCupon.registrarCompartido(cupon);

        } catch (Exception ex) {
            if (!UtilLog.causaControlada(ex)) {
                UtilLog.generarLog(Usuario.class, ex);
            }
            throw ex;
        }
    }
}
