import java.util.ArrayList;

public class GrafoRelaciones {

    public String mostrarRelaciones(ArrayList<Ticket> tickets) {
        if (tickets.isEmpty()) {
            return "No existen relaciones porque no hay tickets registrados.";
        }

        String texto = "RELACIÓN CLIENTE → TICKET → SOPORTE\n";
        texto += "-------------------------\n";

        for (Ticket t : tickets) {
            texto += "Cliente: " + t.getCliente().getNombre() +
                    " → Ticket: " + t.getCodigo() +
                    " → Soporte: " + t.getTipoSoporte() + "\n";
        }

        return texto;
    }
}