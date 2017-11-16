/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package general.modelo;

import java.util.ArrayList;

/**
 * @author juliano
 */
public class Mensaje {
    public static String DATA = "data";
    public static String ALERT = "alert";

    private String descripcion;
    private String tipo;
    private String ruta;
    private String respuestaSi;
    private String respuestaNo;
    private String etiquetaSi;
    private String etiquetaNo;
    private ArrayList<String> parametros;
    private String titulo;
    private int alto;
    private int ancho;
    private int scrollbars;
    private int rezisable;

    public Mensaje(String descripcion, String tipo) { //Alert
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public Mensaje(String descripcion, String tipo, String ruta, ArrayList<String> parametros) { //WindowOpen
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.ruta = ruta;
        this.parametros = parametros;
    }

    public Mensaje(String descripcion, String tipo, String respuestaSi, String respuestaNo, ArrayList<String> parametros) { //Confirm
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.parametros = parametros;
        this.respuestaSi = respuestaSi;
        this.respuestaNo = respuestaNo;
    }
    /**
     *@JpreguntaSintaxis 
     * Mensaje("Descripcion","jpregunta","Titulo","LabelSi","LabelNo","alert('Hola-SI');", "alert('Hola-NO');");
     *@JmensajeSintaxis 
     * Mensaje("Descripcion","jmensaje","Titulo","LabelSi","null","null", "null");
     *@Desventaja se debe tener en cuenta que este tipo de mensajes no pueden ser agregados varios en el array de 
     * "data", ya que en el momento de mostrarse se sobrepone uno sobre el otro y no se visualiza, en este caso se debe
     * utilizar el alert - confirm.
     */
    public Mensaje(String descripcion, String tipo, String titulo, String etiquetaSi, String etiquetaNo, String respuestaSi, String respuestaNo) { //JPregunta
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.titulo = titulo;
        this.etiquetaSi = etiquetaSi;
        this.etiquetaNo = etiquetaNo;
        this.respuestaSi = respuestaSi;
        this.respuestaNo = respuestaNo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     * @return the respuestaSi
     */
    public String getRespuestaSi() {
        return respuestaSi;
    }

    /**
     * @param respuestaSi the respuestaSi to set
     */
    public void setRespuestaSi(String respuestaSi) {
        this.respuestaSi = respuestaSi;
    }

    /**
     * @return the respuestaNo
     */
    public String getRespuestaNo() {
        return respuestaNo;
    }

    /**
     * @param respuestaNo the respuestaNo to set
     */
    public void setRespuestaNo(String respuestaNo) {
        this.respuestaNo = respuestaNo;
    }

    /**
     * @return the parametros
     */
    public ArrayList<String> getParametros() {
        return parametros;
    }

    /**
     * @param parametros the parametros to set
     */
    public void setParametros(ArrayList<String> parametros) {
        this.parametros = parametros;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getScrollbars() {
        return scrollbars;
    }

    public void setScrollbars(int scrollbars) {
        this.scrollbars = scrollbars;
    }

    public int getRezisable() {
        return rezisable;
    }

    public void setRezisable(int rezisable) {
        this.rezisable = rezisable;
    }

    /**
     * @return the etiquetaSi
     */
    public String getEtiquetaSi() {
        return etiquetaSi;
    }

    /**
     * @param etiquetaSi the etiquetaSi to set
     */
    public void setEtiquetaSi(String etiquetaSi) {
        this.etiquetaSi = etiquetaSi;
    }

    /**
     * @return the etiquetaNo
     */
    public String getEtiquetaNo() {
        return etiquetaNo;
    }

    /**
     * @param etiquetaNo the etiquetaNo to set
     */
    public void setEtiquetaNo(String etiquetaNo) {
        this.etiquetaNo = etiquetaNo;
    }

}