/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publico.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author juliano
 */

public class LocalizacionesDispositivo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    protected LocalizacionesDispositivoPK localizacionesDispositivoPK;
    private Date fecha;
    private double latitude;
    private double longitud;
    private Dispositivos dispositivos;

    public LocalizacionesDispositivo() {
    }

    public LocalizacionesDispositivo(LocalizacionesDispositivoPK localizacionesDispositivoPK) {
        this.localizacionesDispositivoPK = localizacionesDispositivoPK;
    }

    public LocalizacionesDispositivo(LocalizacionesDispositivoPK localizacionesDispositivoPK, Date fecha, double latitude, double longitud) {
        this.localizacionesDispositivoPK = localizacionesDispositivoPK;
        this.fecha = fecha;
        this.latitude = latitude;
        this.longitud = longitud;
    }

    public LocalizacionesDispositivo(String correo, int codDispositivo, int codLocalizacion) {
        this.localizacionesDispositivoPK = new LocalizacionesDispositivoPK(correo, codDispositivo, codLocalizacion);
    }

    public LocalizacionesDispositivoPK getLocalizacionesDispositivoPK() {
        return localizacionesDispositivoPK;
    }

    public void setLocalizacionesDispositivoPK(LocalizacionesDispositivoPK localizacionesDispositivoPK) {
        this.localizacionesDispositivoPK = localizacionesDispositivoPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public Dispositivos getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(Dispositivos dispositivos) {
        this.dispositivos = dispositivos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (localizacionesDispositivoPK != null ? localizacionesDispositivoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocalizacionesDispositivo)) {
            return false;
        }
        LocalizacionesDispositivo other = (LocalizacionesDispositivo) object;
        if ((this.localizacionesDispositivoPK == null && other.localizacionesDispositivoPK != null) || (this.localizacionesDispositivoPK != null && !this.localizacionesDispositivoPK.equals(other.localizacionesDispositivoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "publico.modelo.LocalizacionesDispositivo[ localizacionesDispositivoPK=" + localizacionesDispositivoPK + " ]";
    }
    
}
