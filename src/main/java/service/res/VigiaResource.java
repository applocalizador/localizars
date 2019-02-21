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
import publico.modelo.Dispositivos;
import utilidades.modelo.UtilLog;
import utilidades.modelo.UtilRest;

/**
 * REST Web Service
 *
 * @author Julian D Osorio G
 */
@Path("vigia")
public class VigiaResource {

    @Context
    private UriInfo context;
    private static final int PRETTY_PRINT_INDENT_FACTOR = 4;

    /**
     * Creates a new instance of VigiaResource
     */
    public VigiaResource() {
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
     * Retrieves plaque information
     *
     * @param plaque
     * @return
     * @throws java.io.IOException
     */
    @GET
    @Path("/get/plaque/{plaque}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDispositivosUsuario(@PathParam("plaque") String plaque) throws IOException {
        try {
            JSONObject o = new JSONObject();
            switch (plaque) {
                case "PLY121":
                    o.put("placa", "PLY121");
                    o.put("tipo", "VISITANTE");
                    o.put("asignacion", "NA");
                    o.put("bloque", "1");
                    o.put("apartamento", "401");
                    o.put("infracciones", "3");
                    break;
                case "IGR113":
                    o.put("placa", "IGR113");
                    o.put("tipo", "PROPIETARIO");
                    o.put("asignacion", "COMUNAL");
                    o.put("bloque", "2");
                    o.put("apartamento", "601");
                    o.put("infracciones", "0");
                    break;
                case "DPR145":
                    o.put("placa", "DPR145");
                    o.put("tipo", "PROPIETARIO");
                    o.put("asignacion", "PRIVADO");
                    o.put("bloque", "4");
                    o.put("apartamento", "303");
                    o.put("infracciones", "1");
                    break;
            }

            return o.toString();
        } catch (Exception ex) {
            UtilLog.generarLog(this.getClass(), ex);
        }
        return null;
    }

    /**
     * PUT method for updating or creating an instance of VigiaResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
