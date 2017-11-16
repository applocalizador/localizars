/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuponio.modelo;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author jose
 */
@ManagedBean
@SessionScoped

public class Cupon {

    public static int LONGITUD_CADENA_VERIFICACION = 8;

    private Tienda tienda;
    private Categoria categoria;
    private Integer codigo;
    private String nombre;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private byte[] imagen;
    private String imagenBase64;
    private String codVerificacion;

    public Cupon() {
        tienda = new Tienda();
        categoria = new Categoria();
    }

    /**
     * @return the tienda
     */
    public Tienda getTienda() {
        return tienda;
    }

    /**
     * @param tienda the tienda to set
     */
    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

//    @Override
//    public String toString() {
//        return "Cupon [nombre=" + nombre + " , imagen=" + Arrays.toString(imagen) + "]";
//    }
    /**
     * @return the imagen
     */
    public byte[] getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the imagenBase64
     */
    public String getImagenBase64() {
        return imagenBase64;
    }

    /**
     * @param imagenBase64 the imagenBase64 to set
     */
    public void setImagenBase64(String imagenBase64) {
        this.imagenBase64 = imagenBase64;
    }

    /**
     * @return the codVerificacion
     */
    public String getCodVerificacion() {
        return codVerificacion;
    }

    /**
     * @param codVerificacion the codVerificacion to set
     */
    public void setCodVerificacion(String codVerificacion) {
        this.codVerificacion = codVerificacion;
    }

}
