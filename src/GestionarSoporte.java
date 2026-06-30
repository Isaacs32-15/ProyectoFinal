import java.util.ArrayList;
import java.util.PriorityQueue;

public class GestionarSoporte {

    private PriorityQueue<Ticket> colaTickets;

    public GestionarSoporte() {
        colaTickets = new PriorityQueue<>();
    }

    public void cargarTickets(ArrayList<Ticket> tickets, String area) {
        colaTickets.clear();

        for (Ticket t : tickets) {
            if ((t.getTipoSoporte().equals(area) ||
                    (area.equals("OPERATIVO") && t.getTipoSoporte().equals("CONOCIMIENTO")))
                    && t.getEstado().equals("PENDIENTE")) {
                colaTickets.add(t);
            }
        }
    }

    public String verTicketsPendientes() {
        if (colaTickets.isEmpty()) {
            return "No hay tickets pendientes para esta área.";
        }

        String texto = "";

        for (Ticket t : colaTickets) {
            texto += t.toString() + "\n-------------------------\n";
        }

        return texto;
    }

    public Ticket abrirTicket() throws Exception {
        if (colaTickets.isEmpty()) {
            throw new Exception("No hay tickets pendientes para atender.");
        }

        Ticket ticket = colaTickets.poll();
        ticket.cambiarEstado("EN PROCESO");

        return ticket;
    }

    public void responderTicket(Ticket ticket, String respuesta) throws Exception {
        if (ticket == null) {
            throw new Exception("Primero debe abrir un ticket.");
        }

        if (respuesta == null || respuesta.trim().isEmpty()) {
            throw new Exception("Ingrese una respuesta para el ticket.");
        }

        ticket.responder(respuesta);
        ticket.cambiarEstado("FINALIZADO");
    }
}