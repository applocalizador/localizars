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

public class Grupos implements Serializable {

    private static final long serialVersionUID = 1L;
    protected GruposPK gruposPK;
    private String nombre;
    private Date fechaCreacion;
    private Usuarios usuarios;
    private Collection<GruposDispositivos> gruposDispositivosCollection;

    public Grupos() {
    }

    public Grupos(GruposPK gruposPK) {
        this.gruposPK = gruposPK;
    }

    public Grupos(String correo, int codGrupo) {
        this.gruposPK = new GruposPK(correo, codGrupo);
    }

    public Grupos(GruposPK gruposPK, String nombre) {
        this.gruposPK = gruposPK;
        this.nombre = nombre;
    }
    
    

    public GruposPK getGruposPK() {
        return gruposPK;
    }

    public void setGruposPK(GruposPK gruposPK) {
        this.gruposPK = gruposPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<GruposDispositivos> getGruposDispositivosCollection() {
        return gruposDispositivosCollection;
    }

    public void setGruposDispositivosCollection(Collection<GruposDispositivos> gruposDispositivosCollection) {
        this.gruposDispositivosCollection = gruposDispositivosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gruposPK != null ? gruposPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupos)) {
            return false;
        }
        Grupos other = (Grupos) object;
        if ((this.gruposPK == null && other.gruposPK != null) || (this.gruposPK != null && !this.gruposPK.equals(other.gruposPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "publico.modelo.Grupos[ gruposPK=" + gruposPK + " ]";
    }
    
}
