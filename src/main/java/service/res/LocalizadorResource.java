/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.res;

import java.io.IOException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import publico.controlador.GestorDispositivos;
import publico.controlador.GestorGrupos;
import publico.modelo.Dispositivos;
import publico.modelo.Grupos;
import utilidades.modelo.UtilLog;
import utilidades.modelo.UtilRest;

/**
 * REST Web Service
 *
 * @author juliano
 */
@Path("localizador")
public class LocalizadorResource {

    private static final int PRETTY_PRINT_INDENT_FACTOR = 4;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LocalizadorResource
     */
    public LocalizadorResource() {
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
        String xml
                = //"<?xml version='1.0' encoding='UTF-8' ?>"
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
     * Devuelve la lista de dispositivos del usuario
     *
     * @param correo
     * @return
     * @throws java.io.IOException
     */
    @GET
    @Path("/get/dispositivos/usuario/{correo}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDispositivosUsuario(@PathParam("correo") String correo) throws IOException {
        try {
            JSONArray dispositivos = new JSONArray();
            GestorDispositivos gestorDispositivos = new GestorDispositivos();

            List<Dispositivos> dispositivosList = gestorDispositivos.cargarDispositivosUsuario(correo);
            for (Dispositivos d : dispositivosList) {
                dispositivos.put(UtilRest.toJson(d));
            }
            return dispositivos.toString();
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
        return null;
    }

    /**
     * Devuelve la lista de dispositivos del grupo por usuario
     *
     * @param correo
     * @param codGrupo
     * @return
     * @throws java.io.IOException
     */
    @GET
    @Path("/get/dispositivos/grupo/usuario/{correo}/{codGrupo}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDispositivosGrupoUsuario(@PathParam("correo") String correo, @PathParam("codGrupo") int codGrupo) throws IOException {
        try {
            JSONArray dispositivos = new JSONArray();
            GestorDispositivos gestorDispositivos = new GestorDispositivos();
            List<Dispositivos> dispositivoses = gestorDispositivos.cargarDispositivosGrupoUsuario(correo, codGrupo);
            for (Dispositivos d : dispositivoses) {
                dispositivos.put(UtilRest.toJson(d));
            }
            return dispositivos.toString();
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
        return null;
    }

    /**
     * Devuelve la lista de grupos del usuario
     *
     * @param correo
     * @return
     * @throws java.io.IOException
     */
    @GET
    @Path("/get/grupos/usuario/{correo}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getGrupos(@PathParam("correo") String correo) throws IOException {
        try {
            JSONArray grupos = new JSONArray();
            GestorGrupos gestorGrupos = new GestorGrupos();

            List<Grupos> gruposList = gestorGrupos.consultaGrupos(correo);
            for (Grupos g : gruposList) {
                grupos.put(UtilRest.toJson(g));
            }
            return grupos.toString();
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
        return null;
    }

    /**
     * PUT method for updating or creating an instance of LocalizadorResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

}
