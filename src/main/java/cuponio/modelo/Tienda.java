/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuponio.modelo;

/**
 *
 * @author jose
 */
public class Tienda {

    private CentroComercial centroComercial;
    private String codigo;
    private String nombre;
    private String email;
    private String clave;

    public Tienda(CentroComercial centroComercial, String codigo, String nombre) {
        this.centroComercial = centroComercial;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Tienda() {
        centroComercial = new CentroComercial();
    }

    /**
     * @return the centroComercial
     */
    public CentroComercial getCentroComercial() {
        return centroComercial;
    }

    /**
     * @param centroComercial the centroComercial to set
     */
    public void setCentroComercial(CentroComercial centroComercial) {
        this.centroComercial = centroComercial;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


}
