import java.util.ArrayList;
import java.util.Stack;

public class Ticket implements Comparable<Ticket> {
    private int codigo;
    private String titulo;
    private String descripcion;
    private String tipoSoporte;
    private String prioridad;
    private String estado;
    private Usuario cliente;
    private String respuestaSoporte;

    private Stack<String> historialEstados;
    private ArrayList<Respuesta> respuestas;

    public Ticket(int codigo, String titulo, String descripcion,
                  String tipoSoporte, String prioridad, Usuario cliente) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipoSoporte = tipoSoporte;
        this.prioridad = prioridad;
        this.cliente = cliente;
        this.estado = "PENDIENTE";
        this.respuestaSoporte = "";

        historialEstados = new Stack<>();
        respuestas = new ArrayList<>();
        historialEstados.push("Ticket creado con estado PENDIENTE");
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipoSoporte() {
        return tipoSoporte;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public String getEstado() {
        return estado;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public String getRespuestaSoporte() {
        return respuestaSoporte;
    }

    public Stack<String> getHistorialEstados() {
        return historialEstados;
    }

    public ArrayList<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void cambiarEstado(String estado) {
        this.estado = estado;
        historialEstados.push("Estado cambiado a: " + estado);
    }

    public void responder(String respuesta) {
        this.respuestaSoporte = respuesta;

        Respuesta nuevaRespuesta = new Respuesta(
                respuestas.size() + 1,
                respuesta,
                "Soporte"
        );

        respuestas.add(nuevaRespuesta);
    }

    public void agregarRespuesta(Respuesta respuesta) {
        respuestas.add(respuesta);
    }

    @Override
    public int compareTo(Ticket otro) {
        return valorPrioridad(this.prioridad) - valorPrioridad(otro.prioridad);
    }

    private int valorPrioridad(String prioridad) {
        if (prioridad.equals("ALTA")) {
            return 1;
        } else if (prioridad.equals("MEDIA")) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    public String toString() {
        return "Código: " + codigo +
                "\nTítulo: " + titulo +
                "\nDescripción: " + descripcion +
                "\nTipo de soporte: " + tipoSoporte +
                "\nPrioridad: " + prioridad +
                "\nEstado: " + estado +
                "\nCliente: " + cliente.getNombre() +
                "\nRespuesta soporte: " +
                (respuestaSoporte.isEmpty() ? "Sin respuesta" : respuestaSoporte);
    }
}