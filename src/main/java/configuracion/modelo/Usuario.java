package configuracion.modelo;

import java.util.Date;
import java.util.List;

public class Usuario {

    public static boolean VERIFICAR_CLAVE = true;

    private String codigo;
    private String correo;
    private String nombre;
    private String apellido;
    private int edad = 0;
    private String clave;
    private String claveConfirmacion;
    private Date fechaNacimiento;
    private boolean success = false;

    public Usuario() {
    }

    public Usuario(String codigo, String nombre, String apellido) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;

    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String val) {
        this.apellido = val;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String val) {
        this.codigo = val;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String val) {
        this.correo = val;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int val) {
        this.edad = val;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String val) {
        this.nombre = val;
    }

    @Override
    public String toString() {
        return "Usuario[nombre=" + nombre + ", apellido=" + apellido + "]";
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
     * @return the claveConfirmacion
     */
    public String getClaveConfirmacion() {
        return claveConfirmacion;
    }

    /**
     * @param claveConfirmacion the claveConfirmacion to set
     */
    public void setClaveConfirmacion(String claveConfirmacion) {
        this.claveConfirmacion = claveConfirmacion;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
