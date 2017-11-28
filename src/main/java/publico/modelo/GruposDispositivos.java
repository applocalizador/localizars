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
public class GruposDispositivos implements Serializable {

    private static final long serialVersionUID = 1L;
   
    protected GruposDispositivosPK gruposDispositivosPK;
    private Date fecha;
    private Date fechaAprobacion;
    private Boolean aprobado;
    private Dispositivos dispositivos;
    private Grupos grupos;

    public GruposDispositivos() {
    }

    public GruposDispositivos(GruposDispositivosPK gruposDispositivosPK) {
        this.gruposDispositivosPK = gruposDispositivosPK;
    }

    public GruposDispositivos(String correoAdministrador, int codGrupo, String correo, int codDispositivo) {
        this.gruposDispositivosPK = new GruposDispositivosPK(correoAdministrador, codGrupo, correo, codDispositivo);
    }

    public GruposDispositivosPK getGruposDispositivosPK() {
        return gruposDispositivosPK;
    }

    public void setGruposDispositivosPK(GruposDispositivosPK gruposDispositivosPK) {
        this.gruposDispositivosPK = gruposDispositivosPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public Boolean getAprobado() {
        return aprobado;
    }

    public void setAprobado(Boolean aprobado) {
        this.aprobado = aprobado;
    }

    public Dispositivos getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(Dispositivos dispositivos) {
        this.dispositivos = dispositivos;
    }

    public Grupos getGrupos() {
        return grupos;
    }

    public void setGrupos(Grupos grupos) {
        this.grupos = grupos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gruposDispositivosPK != null ? gruposDispositivosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GruposDispositivos)) {
            return false;
        }
        GruposDispositivos other = (GruposDispositivos) object;
        if ((this.gruposDispositivosPK == null && other.gruposDispositivosPK != null) || (this.gruposDispositivosPK != null && !this.gruposDispositivosPK.equals(other.gruposDispositivosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "publico.modelo.GruposDispositivos[ gruposDispositivosPK=" + gruposDispositivosPK + " ]";
    }
    
}
