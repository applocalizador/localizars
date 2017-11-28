/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publico.modelo;

import java.io.Serializable;

/**
 *
 * @author juliano
 */

public class LocalizacionesDispositivoPK implements Serializable {

    private String correo;
    private int codDispositivo;
    private int codLocalizacion;

    public LocalizacionesDispositivoPK() {
    }

    public LocalizacionesDispositivoPK(String correo, int codDispositivo, int codLocalizacion) {
        this.correo = correo;
        this.codDispositivo = codDispositivo;
        this.codLocalizacion = codLocalizacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getCodDispositivo() {
        return codDispositivo;
    }

    public void setCodDispositivo(int codDispositivo) {
        this.codDispositivo = codDispositivo;
    }

    public int getCodLocalizacion() {
        return codLocalizacion;
    }

    public void setCodLocalizacion(int codLocalizacion) {
        this.codLocalizacion = codLocalizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (correo != null ? correo.hashCode() : 0);
        hash += (int) codDispositivo;
        hash += (int) codLocalizacion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocalizacionesDispositivoPK)) {
            return false;
        }
        LocalizacionesDispositivoPK other = (LocalizacionesDispositivoPK) object;
        if ((this.correo == null && other.correo != null) || (this.correo != null && !this.correo.equals(other.correo))) {
            return false;
        }
        if (this.codDispositivo != other.codDispositivo) {
            return false;
        }
        if (this.codLocalizacion != other.codLocalizacion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "publico.modelo.LocalizacionesDispositivoPK[ correo=" + correo + ", codDispositivo=" + codDispositivo + ", codLocalizacion=" + codLocalizacion + " ]";
    }
}
