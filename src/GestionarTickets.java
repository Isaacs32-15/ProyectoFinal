import java.util.ArrayList;

public class GestionarTickets {
    private ArrayList<Ticket> tickets;
    private ArbolTickets arbolTickets;
    private GestionarClasificacion gestorClasificacion;
    private int contadorCodigo;

    public GestionarTickets() {
        tickets = new ArrayList<>();
        arbolTickets = new ArbolTickets();
        gestorClasificacion = new GestionarClasificacion();
        contadorCodigo = 100;
    }

    public Ticket crearTicket(String titulo, String descripcion,
                              String tipoSoporte, Usuario cliente) throws Exception {

        validarDatos(titulo, descripcion, tipoSoporte, cliente);

        Clasificacion clasificacion = gestorClasificacion.clasificarTicket(tipoSoporte);

        contadorCodigo++;

        Ticket ticket = new Ticket(
                contadorCodigo,
                titulo,
                descripcion,
                clasificacion.getTipoSoporte(),
                clasificacion.getPrioridad(),
                cliente
        );

        tickets.add(ticket);
        arbolTickets.agregar(ticket);

        return ticket;
    }

    private void validarDatos(String titulo, String descripcion,
                              String tipoSoporte, Usuario cliente) throws Exception {

        if (cliente == null) {
            throw new Exception("No existe un cliente logueado.");
        }

        if (titulo == null || titulo.trim().isEmpty()) {
            throw new Exception("Ingrese el título del ticket.");
        }

        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new Exception("Ingrese la descripción del ticket.");
        }

        if (tipoSoporte == null || tipoSoporte.equals("Seleccione")) {
            throw new Exception("Seleccione un tipo de soporte.");
        }
    }

    public Ticket buscarTicketPorCodigo(int codigo) {
        return arbolTickets.buscar(codigo);
    }

    public String visualizarTicket(int codigo) throws Exception {
        Ticket ticket = buscarTicketPorCodigo(codigo);

        if (ticket == null) {
            throw new Exception("No existe un ticket con ese código.");
        }

        return ticket.toString();
    }

    public void eliminarTicketPendiente(int codigo) throws Exception {
        Ticket ticket = buscarTicketPorCodigo(codigo);

        if (ticket == null) {
            throw new Exception("No existe un ticket con ese código.");
        }

        if (!ticket.getEstado().equals("PENDIENTE")) {
            throw new Exception("Solo se pueden eliminar tickets pendientes.");
        }

        ticket.cambiarEstado("ELIMINADO POR CLIENTE");
    }

    public ArrayList<Ticket> listarTickets() {
        return tickets;
    }

    public String listarTicketsOrdenados() {
        return arbolTickets.inOrden();
    }
}