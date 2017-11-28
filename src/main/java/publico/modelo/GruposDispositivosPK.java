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

public class GruposDispositivosPK implements Serializable {
    
    private String correoAdministrador;
    private int codGrupo;
    private String correo;
    private int codDispositivo;

    public GruposDispositivosPK() {
    }

    public GruposDispositivosPK(String correoAdministrador, int codGrupo, String correo, int codDispositivo) {
        this.correoAdministrador = correoAdministrador;
        this.codGrupo = codGrupo;
        this.correo = correo;
        this.codDispositivo = codDispositivo;
    }

    public String getCorreoAdministrador() {
        return correoAdministrador;
    }

    public void setCorreoAdministrador(String correoAdministrador) {
        this.correoAdministrador = correoAdministrador;
    }

    public int getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(int codGrupo) {
        this.codGrupo = codGrupo;
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
        hash += (correoAdministrador != null ? correoAdministrador.hashCode() : 0);
        hash += (int) codGrupo;
        hash += (correo != null ? correo.hashCode() : 0);
        hash += (int) codDispositivo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GruposDispositivosPK)) {
            return false;
        }
        GruposDispositivosPK other = (GruposDispositivosPK) object;
        if ((this.correoAdministrador == null && other.correoAdministrador != null) || (this.correoAdministrador != null && !this.correoAdministrador.equals(other.correoAdministrador))) {
            return false;
        }
        if (this.codGrupo != other.codGrupo) {
            return false;
        }
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
        return "publico.modelo.GruposDispositivosPK[ correoAdministrador=" + correoAdministrador + ", codGrupo=" + codGrupo + ", correo=" + correo + ", codDispositivo=" + codDispositivo + " ]";
    }
    
}
