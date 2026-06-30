public class GestionarHistorial {
    private int contadorRespuesta;

    public GestionarHistorial() {
        contadorRespuesta = 0;
    }

    public String verHistorialEstados(Ticket ticket) throws Exception {
        if (ticket == null) {
            throw new Exception("Debe seleccionar un ticket.");
        }

        if (ticket.getHistorialEstados().isEmpty()) {
            return "No existe historial de estados.";
        }

        String texto = "";
        for (String estado : ticket.getHistorialEstados()) {
            texto += estado + "\n";
        }

        return texto;
    }

    public String verRespuestas(Ticket ticket) throws Exception {
        if (ticket == null) {
            throw new Exception("Debe seleccionar un ticket.");
        }

        if (ticket.getRespuestas().isEmpty()) {
            return "El ticket aún no tiene respuestas.";
        }

        String texto = "";
        for (Respuesta r : ticket.getRespuestas()) {
            texto += r.toString() + "\n-------------------------\n";
        }

        return texto;
    }

    public void registrarRespuesta(Ticket ticket, String mensaje, String autor) throws Exception {
        if (ticket == null) {
            throw new Exception("Debe seleccionar un ticket.");
        }

        if (mensaje == null || mensaje.trim().isEmpty()) {
            throw new Exception("Ingrese una respuesta.");
        }

        contadorRespuesta++;
        Respuesta respuesta = new Respuesta(contadorRespuesta, mensaje, autor);
        ticket.agregarRespuesta(respuesta);
    }

    public String calcularTiempoResolucion(Ticket ticket) throws Exception {
        if (ticket == null) {
            throw new Exception("Debe seleccionar un ticket.");
        }

        if (ticket.getEstado().equals("FINALIZADO")) {
            return "Tiempo de resolución registrado al finalizar el ticket.";
        }

        return "El ticket aún no ha sido finalizado.";
    }
}