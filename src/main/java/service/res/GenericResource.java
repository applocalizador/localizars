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
import cuponio.modelo.CentroComercial;
import cuponio.modelo.Cupon;
import cuponio.modelo.Tienda;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import publico.modelo.Usuarios;
import sun.misc.BASE64Encoder;
import utilidades.modelo.UtilLog;
import utilidades.modelo.UtilRest;
import utilidades.modelo.UtilidadesGeneral;

/**
 * REST Web Service
 *
 * @author Julian
 */
@Path("generic")
public class GenericResource {

    private static final int PRETTY_PRINT_INDENT_FACTOR = 4;
    private Gson gson = new Gson();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of service.res.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
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
     * Retrieves representation of an instance of service.res.GenericResource
     *
     * @param documento
     * @return an instance of java.lang.String
     * @throws java.io.IOException
     */
    @GET
    @Path("/get/usuarioMap/{documento}")
    @Produces("application/json")
    public String getUsuarioMap(@PathParam("documento") String documento) throws IOException {
        JSONObject obj = new JSONObject();
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            GestorUsuario gestorUsuario = new GestorUsuario();
            Usuario usuario = gestorUsuario.cargarUsuario(documento);

            if (usuario.getCodigo() != null) {

                List<Object> list = new ArrayList<Object>();
                list.add("msg 1");
                list.add("msg 2");
                list.add("msg 3");
                map.put("messages", list);
                map.putAll(UtilidadesGeneral.mapearObjeto(usuario));

            } else {
                map.put("error", "Usuario no existe");
            }

        } catch (Exception ex) {
            map.put("error", "Error al consultar el usuario.");
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapper.writeValueAsString(map);
    }

    @GET
    @Path("/get/usuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuario() throws IOException {
        return new Usuario();
    }

    @GET
    @Path("/get/tienda")
    @Produces(MediaType.APPLICATION_JSON)
    public Tienda getTienda() throws IOException {
        return new Tienda();
    }

    @GET
    @Path("/get/centroComercial")
    @Produces(MediaType.APPLICATION_JSON)
    public CentroComercial getCentroComercial() throws IOException {
        return new CentroComercial();
    }

    @GET
    @Path("/get/cupon")
    @Produces(MediaType.APPLICATION_JSON)
    public Cupon getCupon() throws IOException {
        return new Cupon();
    }

    /**
     * Devuelve un usuario especifico.
     *
     * @param documento
     * @return
     * @throws java.io.IOException
     */
    @GET
    @Path("/get/usuario/{documento}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuario(@PathParam("documento") String documento) throws IOException {
        try {
            GestorUsuario gestorUsuario = new GestorUsuario();
            Usuario usuario = gestorUsuario.cargarUsuario(documento);
            return usuario;
        } catch (Exception ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Valida los datos de un usuario.
     *
     * @param objUsuario
     * @return
     * @throws java.lang.Exception
     */
    @POST
    @Path("/post/validarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuarios getUsuarioAutorizado(Object objUsuario) throws Exception {
        Usuarios usuario = new Usuarios();
        try {
            usuario = (Usuarios) UtilidadesGeneral.obtenerObjetoMapa(objUsuario, usuario);
            GestorUsuario gestorUsuario = new GestorUsuario();
            gestorUsuario.validarAtributosIngreso(usuario);
            usuario = gestorUsuario.validarUsuario(usuario);
            return usuario;
        } catch (Exception ex) {
            if (!UtilLog.causaControlada(ex)) {
                UtilLog.generarLog(Usuario.class, ex);
            }
            throw ex;
        }
    }

    /**
     * Crea un nuevo usuario.
     *
     * @param objUsuario
     * @throws java.lang.Exception
     *
     */
    @POST
    @Path("/post/usuario")
    @Consumes(MediaType.APPLICATION_JSON)
    public String postUsuario(Object objUsuario) throws Exception {
        Usuario usuario = new Usuario();
        GestorCupon gestorCupon = new GestorCupon();
        JSONArray cuponesUsuario = new JSONArray();
        List<Cupon> listaCuponesUsuario;
        try {
            usuario = (Usuario) UtilidadesGeneral.obtenerObjetoMapa(objUsuario, usuario);
            GestorUsuario gestorUsuario = new GestorUsuario();
//            gestorUsuario.validarAtributos(usuario, Usuario.VERIFICAR_CLAVE);
            usuario = gestorUsuario.upperAtributos(usuario);
            gestorUsuario.almacenarUsuario(usuario);
            listaCuponesUsuario = gestorCupon.cargarCuponesUsuario(usuario);
            for (Cupon c : listaCuponesUsuario) {
                c.setImagenBase64(new BASE64Encoder().encode(c.getImagen()));
                c.setImagen(null);
                cuponesUsuario.put(UtilRest.toJson(c));
            }
            return cuponesUsuario.toString();
        } catch (Exception ex) {
            if (!UtilLog.causaControlada(ex)) {
                UtilLog.generarLog(Usuario.class, ex);
            }
            throw ex;
        }
    }

//    /**
//     * PUT method for updating or creating an instance of GenericResource
//     *
//     * @param content representation for the resource
//     */
//    @PUT
//    @Path("/put/usuario/{content}")
//    @Consumes("application/json")
//    public void putJson(String content) {
//        System.out.println("hola " + content);
//        content = content.replace("%5B", "'");
//        content = content.replace("%5D", "'");
//        content = content.replace("&", ",");
//        System.out.println("hola " + content);
//        JSONObject soapDatainJsonObject = (JSONObject) JSONObject.stringToValue(content);
//        System.out.println(soapDatainJsonObject.toString(PRETTY_PRINT_INDENT_FACTOR));
//    }
    /**
     * Actualiza parcialmente los datos de un usuario.
     *
     * @param objUsuario
     * @throws java.lang.Exception
     *
     */
    @POST
    @Path("/put/usuario")
    @Consumes(MediaType.APPLICATION_JSON)
    public void putUsuario(Object objUsuario) throws Exception {
        Usuario usuario = new Usuario();
        try {
            usuario = (Usuario) UtilidadesGeneral.obtenerObjetoMapa(objUsuario, usuario);
            GestorUsuario gestorUsuario = new GestorUsuario();
            boolean verificarClave = (usuario.getClave() == null || !usuario.getClave().equalsIgnoreCase(""))
                    || (usuario.getClaveConfirmacion() == null || !usuario.getClaveConfirmacion().equalsIgnoreCase(""));

            gestorUsuario.validarAtributos(usuario, verificarClave);
            usuario = gestorUsuario.upperAtributos(usuario);
            gestorUsuario.actualizarUsuario(usuario, verificarClave);
        } catch (Exception ex) {
            if (!UtilLog.causaControlada(ex)) {
                UtilLog.generarLog(Usuario.class, ex);
            }
            throw ex;
        }
    }
    
    /**
     * Crea un nuevo usuario.
     *
     * @param objUsuario
     * @return 
     * @throws java.lang.Exception
     *
     */
    @POST
    @Path("/post/registrar/usuario")
    @Consumes(MediaType.APPLICATION_JSON)
    public Usuarios registrarUsuario(Object objUsuario) throws Exception {
        Usuarios usuarios = new Usuarios();
        
        GestorCupon gestorCupon = new GestorCupon();
        JSONArray cuponesUsuario = new JSONArray();
        List<Cupon> listaCuponesUsuario;
        try {
            usuarios = (Usuarios) UtilidadesGeneral.obtenerObjetoMapa(objUsuario, usuarios);
            GestorUsuario gestorUsuario = new GestorUsuario();
            gestorUsuario.validarAtributos(usuarios, Usuario.VERIFICAR_CLAVE);
//            usuario = gestorUsuario.upperAtributos(usuario);
            gestorUsuario.almacenarUsuario(usuarios);
            return usuarios;
        } catch (Exception ex) {
            if (!UtilLog.causaControlada(ex)) {
                UtilLog.generarLog(Usuario.class, ex);
            }
            throw ex;
        }
    }
}
