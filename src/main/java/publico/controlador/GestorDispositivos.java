/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publico.controlador;

import general.controlador.Gestor;
import java.util.ArrayList;
import java.util.List;
import publico.bd.DispositivosDAO;
import publico.modelo.Dispositivos;

/**
 *
 * @author juliano
 */
public class GestorDispositivos extends Gestor {

    public GestorDispositivos() throws Exception {
        super();
    }

    public List<Dispositivos> cargarDispositivosUsuario(String correo) throws Exception {
        try {
            List<Dispositivos> dispositivosList = new ArrayList<>();
            this.abrirConexion();

            DispositivosDAO dispositivosDAO = new DispositivosDAO(conexion);
            dispositivosList.addAll(dispositivosDAO.cargarDispositivosUsuario(correo));
            for (Dispositivos d : dispositivosList) {
                d.setLocalizacionesDispositivoCollection(dispositivosDAO.cargarLocalizacionesDispositivo(d.getDispositivosPK().getCorreo(), d.getDispositivosPK().getCodDispositivo()));
            }
            return dispositivosList;
        } finally {
            this.cerrarConexion();
        }
    }

    public List<Dispositivos> cargarDispositivosGrupoUsuario(String correo, int codGrupo) throws Exception {
        try {
            List<Dispositivos> dispositivosList = new ArrayList<>();
            this.abrirConexion();

            DispositivosDAO dispositivosDAO = new DispositivosDAO(conexion);
            dispositivosList.addAll(dispositivosDAO.cargarDispositivosGrupoUsuario(correo, codGrupo));
            for (Dispositivos d : dispositivosList) {
                d.setLocalizacionesDispositivoCollection(dispositivosDAO.cargarLocalizacionesDispositivo(d.getDispositivosPK().getCorreo(), d.getDispositivosPK().getCodDispositivo()));
            }
            return dispositivosList;
        } finally {
            this.cerrarConexion();
        }
    }

}
