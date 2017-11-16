package configuracion.modelo;

public class Rol {

    public static int ADMINISTRADOR = 1;

    private int codigo;

    private String nombre;

    public Rol() {
    }

    public Rol(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int val) {
        this.codigo = val;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String val) {
        this.nombre = val;
    }

}
