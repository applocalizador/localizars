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
public class Dispositivos implements Serializable {

    private static final long serialVersionUID = 1L;

    protected DispositivosPK dispositivosPK;
    private String identificador;
    private Date fecha;
    private String serial;
    private Usuarios usuarios;
    private LocalizacionesDispositivo localizacionesDispositivo;
    private Collection<LocalizacionesDispositivo> localizacionesDispositivoCollection;
    private Collection<GruposDispositivos> gruposDispositivosCollection;

    public Dispositivos() {
    }

    public Dispositivos(DispositivosPK dispositivosPK, String identificador, String serial) {
        this.dispositivosPK = dispositivosPK;
        this.identificador = identificador;
        this.serial = serial;
    }

    public Dispositivos(DispositivosPK dispositivosPK, String identificador, Date fecha, String serial) {
        this.dispositivosPK = dispositivosPK;
        this.identificador = identificador;
        this.fecha = fecha;
        this.serial = serial;
    }

    public Dispositivos(DispositivosPK dispositivosPK, String identificador, Date fecha, Usuarios usuarios, LocalizacionesDispositivo localizacionesDispositivo) {
        this.dispositivosPK = dispositivosPK;
        this.identificador = identificador;
        this.fecha = fecha;
        this.usuarios = usuarios;
        this.localizacionesDispositivo = localizacionesDispositivo;

    }

    public Dispositivos(DispositivosPK dispositivosPK) {
        this.dispositivosPK = dispositivosPK;
    }

    public Dispositivos(String correo, int codDispositivo) {
        this.dispositivosPK = new DispositivosPK(correo, codDispositivo);
    }

    public DispositivosPK getDispositivosPK() {
        return dispositivosPK;
    }

    public void setDispositivosPK(DispositivosPK dispositivosPK) {
        this.dispositivosPK = dispositivosPK;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<LocalizacionesDispositivo> getLocalizacionesDispositivoCollection() {
        return localizacionesDispositivoCollection;
    }

    public void setLocalizacionesDispositivoCollection(Collection<LocalizacionesDispositivo> localizacionesDispositivoCollection) {
        this.localizacionesDispositivoCollection = localizacionesDispositivoCollection;
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
        hash += (dispositivosPK != null ? dispositivosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dispositivos)) {
            return false;
        }
        Dispositivos other = (Dispositivos) object;
        if ((this.dispositivosPK == null && other.dispositivosPK != null) || (this.dispositivosPK != null && !this.dispositivosPK.equals(other.dispositivosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "publico.modelo.Dispositivos[ dispositivosPK=" + dispositivosPK + " ]";
    }

    /**
     * @return the localizacionesDispositivo
     */
    public LocalizacionesDispositivo getLocalizacionesDispositivo() {
        return localizacionesDispositivo;
    }

    /**
     * @param localizacionesDispositivo the localizacionesDispositivo to set
     */
    public void setLocalizacionesDispositivo(LocalizacionesDispositivo localizacionesDispositivo) {
        this.localizacionesDispositivo = localizacionesDispositivo;
    }

    /**
     * @return the serial
     */
    public String getSerial() {
        return serial;
    }

    /**
     * @param serial the serial to set
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }

}
