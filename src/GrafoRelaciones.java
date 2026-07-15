import java.util.ArrayList;
import java.util.HashMap;

public class GrafoRelaciones {

    private HashMap<String, ArrayList<String>> grafo;

    public GrafoRelaciones() {
        grafo = new HashMap<>();
    }

    public void construirGrafo(ArrayList<Ticket> tickets) {

        grafo.clear();

        for (Ticket t : tickets) {

            String cliente = t.getCliente().getNombre();
            String ticket = "Ticket " + t.getCodigo();
            String soporte = t.getTipoSoporte();

            grafo.putIfAbsent(cliente, new ArrayList<>());
            grafo.get(cliente).add(ticket);

            grafo.putIfAbsent(ticket, new ArrayList<>());
            grafo.get(ticket).add(soporte);
        }
    }

    public String mostrarRelaciones() {

        String texto = "";

        for (String nodo : grafo.keySet()) {

            texto += nodo + " -> ";

            for (String vecino : grafo.get(nodo)) {
                texto += vecino + " ";
            }

            texto += "\n";
        }

        return texto;
    }
}