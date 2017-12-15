package configuracion.controlador;

import configuracion.bd.UsuarioDAO;
import configuracion.modelo.Usuario;
import general.controlador.Gestor;
import publico.bd.DispositivosDAO;
import publico.bd.GrupoDAO;
import publico.modelo.Dispositivos;
import publico.modelo.DispositivosPK;
import publico.modelo.Grupos;
import publico.modelo.GruposDispositivos;
import publico.modelo.GruposPK;
import publico.modelo.Usuarios;
import utilidades.modelo.UtilCorreo;
import utilidades.modelo.UtilLog;

public class GestorUsuario extends Gestor {

    public GestorUsuario() throws Exception {
        super();
    }

    public Usuario cargarUsuario(String documento) throws Exception {
        try {
            this.abrirConexion();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
            return usuarioDAO.cargarUsuario(documento);
        } finally {
            this.cerrarConexion();
        }
    }

    public Usuario almacenarUsuario(Usuario usuario) throws Exception {
        try {
            this.abrirConexion();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);

//            if (usuarioDAO.existeUsuario(usuario)) {
//                throw new Exception("El correo ingresado ya se encuentra registrado.", UtilLog.TW_VALIDACION);
//            }
            usuario = usuarioDAO.actualizarUsuario(usuario);
            if (usuario.getCodigo() == null || usuario.getCodigo().equalsIgnoreCase("0")) {
                usuario = usuarioDAO.insertarUsuario(usuario);
            }
            return usuario;
        } finally {
            this.cerrarConexion();
        }
    }

    public void validarAtributos(Usuario usuario, boolean validaClave) throws Exception {
        if (!UtilCorreo.validarCorreo(usuario.getCorreo())) {
            throw new Exception("Ingresa un correo correcto.", UtilLog.TW_VALIDACION);
        }
        if (usuario.getNombre() == null || usuario.getNombre().equalsIgnoreCase("")) {
            throw new Exception("Ingresa tu nombre.", UtilLog.TW_VALIDACION);
        }
        if (usuario.getApellido() == null || usuario.getApellido().equalsIgnoreCase("")) {
            throw new Exception("Ingresa tu apellido.", UtilLog.TW_VALIDACION);
        }

        if (validaClave) {
            if (usuario.getClave() == null || usuario.getClave().equalsIgnoreCase("")) {
                throw new Exception("Ingresa tu clave.", UtilLog.TW_VALIDACION);
            }
            if (usuario.getClaveConfirmacion() == null || usuario.getClaveConfirmacion().equalsIgnoreCase("")) {
                throw new Exception("Ingresa tu clave de confirmacion.", UtilLog.TW_VALIDACION);
            }
            if (!usuario.getClave().equalsIgnoreCase(usuario.getClaveConfirmacion())) {
                throw new Exception("Valida que las claves sean iguales.", UtilLog.TW_VALIDACION);
            }
        }
    }

    public void validarAtributosIngreso(Usuarios usuario) throws Exception {
//        if (!UtilCorreo.validarCorreo(usuario.getCorreo())) {
//            throw new Exception("Ingresa un correo correcto.", UtilLog.TW_VALIDACION);
//        }
        if (usuario == null || usuario.getCorreo() == null || usuario.getCorreo().equalsIgnoreCase("")) {
            throw new Exception("Ingresa un correo o usuario correcto.", UtilLog.TW_VALIDACION);
        }
        if (usuario.getClave() == null || usuario.getClave().equalsIgnoreCase("")) {
            throw new Exception("Ingresa tu clave.", UtilLog.TW_VALIDACION);
        }
    }

    public Usuarios validarUsuario(Usuarios usuario) throws Exception {
        try {
            this.abrirConexion();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);

            if (!usuarioDAO.existeUsuario(usuario)) {
                throw new Exception("El correo ingresado no existe, por favor registrate y diviertete.", UtilLog.TW_VALIDACION);
            }
            usuario = usuarioDAO.cargarUsuario(usuario);
            if (usuario == null || usuario.getCorreo() == null || usuario.getCorreo().equalsIgnoreCase("")) {
                throw new Exception("La clave introducida no es correcta.", UtilLog.TW_VALIDACION);
            }
            return usuario;
        } finally {
            this.cerrarConexion();
        }
    }

    public void actualizarUsuario(Usuario usuario, boolean actualizaClave) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
//            usuarioDAO.actualizarUsuario(usuario, actualizaClave);
            this.finTransaccion();
        } finally {
            this.cerrarConexion();
        }
    }

    public Usuario upperAtributos(Usuario usuario) {
        usuario.setNombre(usuario.getNombre().toUpperCase());
        usuario.setApellido(usuario.getApellido().toUpperCase());
        return usuario;
    }

    public Usuario cargarUsuarioCorreo(Usuario usuario) throws Exception {
        try {
            this.abrirConexion();
            return new UsuarioDAO(conexion).cargarUsuarioCorreo(usuario);
        } finally {
            this.cerrarConexion();
        }
    }

    public void validarAtributos(Usuarios usuario, boolean validaClave) throws Exception {
        if (!UtilCorreo.validarCorreo(usuario.getCorreo())) {
            throw new Exception("Ingresa un correo correcto.", UtilLog.TW_VALIDACION);
        }
        if (usuario.getDispositivos() == null || usuario.getDispositivos().getSerial() == null
                || usuario.getDispositivos().getSerial().equalsIgnoreCase("")) {
            throw new Exception("Ingresa un celular correcto.", UtilLog.TW_VALIDACION);
        }
//        if (usuario.getNombre() == null || usuario.getNombre().equalsIgnoreCase("")) {
//            throw new Exception("Ingresa tu nombre.", UtilLog.TW_VALIDACION);
//        }
//        if (usuario.getApellido() == null || usuario.getApellido().equalsIgnoreCase("")) {
//            throw new Exception("Ingresa tu apellido.", UtilLog.TW_VALIDACION);
//        }

        if (validaClave) {
            if (usuario.getClave() == null || usuario.getClave().equalsIgnoreCase("")) {
                throw new Exception("Ingresa tu clave.", UtilLog.TW_VALIDACION);
            }
//            if (usuario.getClaveConfirmacion() == null || usuario.getClaveConfirmacion().equalsIgnoreCase("")) {
//                throw new Exception("Ingresa tu clave de confirmacion.", UtilLog.TW_VALIDACION);
//            }
//            if (!usuario.getClave().equalsIgnoreCase(usuario.getClaveConfirmacion())) {
//                throw new Exception("Valida que las claves sean iguales.", UtilLog.TW_VALIDACION);
//            }
        }
        usuario.setCorreo(usuario.getCorreo().trim().toLowerCase());
        usuario.setClave(usuario.getClave().trim());
    }

    public void almacenarUsuario(Usuarios usuarios) throws Exception {
        try {
            this.abrirConexion();
            this.inicioTransaccion();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
            GrupoDAO grupoDAO = new GrupoDAO(conexion);
            DispositivosDAO dispositivosDAO = new DispositivosDAO(conexion);

            
            usuarioDAO.insertarUsuario(usuarios);

            Grupos g = new Grupos(new GruposPK(usuarios.getCorreo()), "MIS DISPOSITIVOS");
            Dispositivos d = new Dispositivos(new DispositivosPK(usuarios.getCorreo()), "MOVIL", usuarios.getDispositivos().getSerial());
            g = grupoDAO.insertarGrupos(g);
            d = dispositivosDAO.insertarDispositivos(d);

            GruposDispositivos gd = new GruposDispositivos(g.getGruposPK().getCorreo(), g.getGruposPK().getCodGrupo(), d.getDispositivosPK().getCorreo(), d.getDispositivosPK().getCodDispositivo());
            gd.setAprobado(Boolean.TRUE);
            grupoDAO.insertarGruposDispositivos(gd);
            
            this.finTransaccion();
        } catch (Exception e) {
            this.devolverTransaccion();
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }
}
