import java.util.ArrayList;

public class GestionarReportes {

    public int contarPorPrioridad(ArrayList<Ticket> tickets, String prioridad) {
        int contador = 0;

        for (Ticket t : tickets) {
            if (t.getPrioridad().equals(prioridad)) {
                contador++;
            }
        }

        return contador;
    }

    public int contarPorTipoSoporte(ArrayList<Ticket> tickets, String tipoSoporte) {
        int contador = 0;

        for (Ticket t : tickets) {
            if (t.getTipoSoporte().equals(tipoSoporte)) {
                contador++;
            }
        }

        return contador;
    }

    public int contarFinalizados(ArrayList<Ticket> tickets) {
        int contador = 0;

        for (Ticket t : tickets) {
            if (t.getEstado().equals("FINALIZADO")) {
                contador++;
            }
        }

        return contador;
    }

    public String generarReporteGeneral(ArrayList<Ticket> tickets) {
        if (tickets.isEmpty()) {
            return "No hay tickets registrados.";
        }

        return "REPORTE GENERAL\n" +
                "-------------------------\n" +
                "Tickets prioridad ALTA: " + contarPorPrioridad(tickets, "ALTA") + "\n" +
                "Tickets prioridad MEDIA: " + contarPorPrioridad(tickets, "MEDIA") + "\n" +
                "Tickets prioridad BAJA: " + contarPorPrioridad(tickets, "BAJA") + "\n\n" +
                "Tickets tipo TECNICO: " + contarPorTipoSoporte(tickets, "TECNICO") + "\n" +
                "Tickets tipo OPERATIVO: " + contarPorTipoSoporte(tickets, "OPERATIVO") + "\n" +
                "Tickets tipo CONOCIMIENTO: " + contarPorTipoSoporte(tickets, "CONOCIMIENTO") + "\n\n" +
                "Tickets finalizados: " + contarFinalizados(tickets);
    }
}