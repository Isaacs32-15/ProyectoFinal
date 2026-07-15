public class Usuario {
    private int idUsuario;
    private String nombre;
    private String usuario;
    private String contrasenia;
    private String rol;

    public Usuario(int idUsuario, String nombre, String usuario, String contrasenia, String rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getRol() {
        return rol;
    }
}