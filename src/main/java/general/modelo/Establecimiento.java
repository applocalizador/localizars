/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package general.modelo;

import java.util.Date;

/**
 *
 * @author Julian
 */
public class Establecimiento {
    private int codigo;
    private String nombre;
    private String prefijo;
    private Date fechaCierreDiario;
    private String tipoEstablecimiento;

    public Establecimiento() {
    }

    public Establecimiento(int codigo) {
        this.codigo = codigo;
    }

    public Establecimiento(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
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
     * @return the prefijo
     */
    public String getPrefijo() {
        return prefijo;
    }

    /**
     * @param prefijo the prefijo to set
     */
    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    /**
     * @return the fechaCierreDiario
     */
    public Date getFechaCierreDiario() {
        return fechaCierreDiario;
    }

    /**
     * @param fechaCierreDiario the fechaCierreDiario to set
     */
    public void setFechaCierreDiario(Date fechaCierreDiario) {
        this.fechaCierreDiario = fechaCierreDiario;
    }

    /**
     * @return the tipoEstablecimiento
     */
    public String getTipoEstablecimiento() {
        return tipoEstablecimiento;
    }

    /**
     * @param tipoEstablecimiento the tipoEstablecimiento to set
     */
    public void setTipoEstablecimiento(String tipoEstablecimiento) {
        this.tipoEstablecimiento = tipoEstablecimiento;
    }

}
