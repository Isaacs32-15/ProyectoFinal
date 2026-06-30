public class Respuesta {
    private int idRespuesta;
    private String mensaje;
    private String autor;

    public Respuesta(int idRespuesta, String mensaje, String autor) {
        this.idRespuesta = idRespuesta;
        this.mensaje = mensaje;
        this.autor = autor;
    }

    public int getIdRespuesta() {
        return idRespuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return "Respuesta #" + idRespuesta +
                "\nAutor: " + autor +
                "\nMensaje: " + mensaje;
    }
}