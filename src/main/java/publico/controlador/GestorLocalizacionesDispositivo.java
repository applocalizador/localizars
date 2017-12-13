/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publico.controlador;

import general.controlador.Gestor;
import publico.bd.LocalizacionesDispositivoDAO;
import publico.modelo.LocalizacionesDispositivo;
import utilidades.modelo.UtilLog;

/**
 *
 * @author juliano
 */
public class GestorLocalizacionesDispositivo extends Gestor {

    public GestorLocalizacionesDispositivo() throws Exception {
        super();    
    }

    public void validarAtributosLocalizacionesDispositivo(LocalizacionesDispositivo l) throws Exception {
        if (l.getLocalizacionesDispositivoPK() == null || l.getLocalizacionesDispositivoPK().getCorreo() == null) {
            throw new Exception("No fue posible determinar el correo del usuario", UtilLog.TW_VALIDACION);
        }
        if (l.getLocalizacionesDispositivoPK().getCodDispositivo() <= 0) {
            throw new Exception("El usuario no tiene un dispositivo asociado", UtilLog.TW_VALIDACION);
        }
    }

    public void almacenarLocalizacionesDispositivo(LocalizacionesDispositivo localizacionesDispositivo) throws Exception {
        try {
            this.abrirConexion();
            LocalizacionesDispositivoDAO localizacionesDispositivoDAO = new LocalizacionesDispositivoDAO(conexion);
            localizacionesDispositivoDAO.insertarLocalizacionesDispositivo(localizacionesDispositivo);
        } finally {
            this.cerrarConexion();
        }
    }

}
