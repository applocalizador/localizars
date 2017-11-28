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

public class DispositivosPK implements Serializable {

    
    private String correo;
    private int codDispositivo;

    public DispositivosPK() {
    }

    public DispositivosPK(String correo, int codDispositivo) {
        this.correo = correo;
        this.codDispositivo = codDispositivo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (correo != null ? correo.hashCode() : 0);
        hash += (int) codDispositivo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DispositivosPK)) {
            return false;
        }
        DispositivosPK other = (DispositivosPK) object;
        if ((this.correo == null && other.correo != null) || (this.correo != null && !this.correo.equals(other.correo))) {
            return false;
        }
        if (this.codDispositivo != other.codDispositivo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "publico.modelo.DispositivosPK[ correo=" + correo + ", codDispositivo=" + codDispositivo + " ]";
    }
    
}
