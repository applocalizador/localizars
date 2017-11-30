/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publico.controlador;

import general.controlador.Gestor;
import java.util.ArrayList;
import java.util.List;
import publico.bd.GrupoDAO;
import publico.modelo.Grupos;

/**
 *
 * @author Walter Osorio
 */
public class GestorGrupos extends Gestor {
    
      public GestorGrupos() throws Exception {
        super();
    }
      
     public List<Grupos> consultaGrupos(String correo) throws Exception {
        try {
            List<Grupos> gruposList = new ArrayList<>();
            this.abrirConexion();

            GrupoDAO grupoDao = new GrupoDAO(conexion);
            gruposList.addAll(grupoDao.cargarGrupos(correo));
           
            return gruposList;
        } finally {
            this.cerrarConexion();
        }
    }   
      
    
}
