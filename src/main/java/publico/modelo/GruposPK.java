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

public class GruposPK implements Serializable {

    
    private String correo;
    private int codGrupo;

    public GruposPK() {
    }

    public GruposPK(String correo, int codGrupo) {
        this.correo = correo;
        this.codGrupo = codGrupo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getCodGrupo() {
        return codGrupo;
    }

    public void setCodGrupo(int codGrupo) {
        this.codGrupo = codGrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (correo != null ? correo.hashCode() : 0);
        hash += (int) codGrupo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GruposPK)) {
            return false;
        }
        GruposPK other = (GruposPK) object;
        if ((this.correo == null && other.correo != null) || (this.correo != null && !this.correo.equals(other.correo))) {
            return false;
        }
        if (this.codGrupo != other.codGrupo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "publico.modelo.GruposPK[ correo=" + correo + ", codGrupo=" + codGrupo + " ]";
    }
    
}
