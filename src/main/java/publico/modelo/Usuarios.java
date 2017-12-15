/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publico.modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author juliano
 */
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String correo;
    private Integer codDocumento;
    private String documentoUsuario;
    private String nombre;
    private String apellido;
    private String clave;
    private Boolean activo;
    private Date fechaRegistro;
    private Collection<Dispositivos> dispositivosCollection;
    private Collection<Grupos> gruposCollection;
    private Dispositivos dispositivos;
    private Boolean success = false;

    public Usuarios() {
    }

    public Usuarios(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getCodDocumento() {
        return codDocumento;
    }

    public void setCodDocumento(Integer codDocumento) {
        this.codDocumento = codDocumento;
    }

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Dispositivos> getDispositivosCollection() {
        return dispositivosCollection;
    }

    public void setDispositivosCollection(Collection<Dispositivos> dispositivosCollection) {
        this.dispositivosCollection = dispositivosCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Grupos> getGruposCollection() {
        return gruposCollection;
    }

    public void setGruposCollection(Collection<Grupos> gruposCollection) {
        this.gruposCollection = gruposCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (correo != null ? correo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.correo == null && other.correo != null) || (this.correo != null && !this.correo.equals(other.correo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "publico.modelo.Usuarios[ correo=" + correo + " ]";
    }

    /**
     * @return the dispositivos
     */
    public Dispositivos getDispositivos() {
        return dispositivos;
    }

    /**
     * @param dispositivos the dispositivos to set
     */
    public void setDispositivos(Dispositivos dispositivos) {
        this.dispositivos = dispositivos;
    }

    /**
     * @return the success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    
}
