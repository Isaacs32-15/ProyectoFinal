public class ArbolTickets {

    private NodoTicket raiz;

    public ArbolTickets() {
        raiz = null;
    }

    public void agregar(Ticket dato) {
        if (raiz == null) {
            raiz = new NodoTicket(dato);
        } else {
            agregar(raiz, dato);
        }
    }

    private void agregar(NodoTicket actual, Ticket dato) {
        if (dato.getCodigo() < actual.getDato().getCodigo()) {
            if (actual.getIzquierda() == null) {
                actual.setIzquierda(new NodoTicket(dato));
            } else {
                agregar(actual.getIzquierda(), dato);
            }
        } else {
            if (actual.getDerecha() == null) {
                actual.setDerecha(new NodoTicket(dato));
            } else {
                agregar(actual.getDerecha(), dato);
            }
        }
    }

    public Ticket buscar(int codigo) {
        return buscar(raiz, codigo);
    }

    private Ticket buscar(NodoTicket actual, int codigo) {
        if (actual == null) {
            return null;
        }

        if (codigo == actual.getDato().getCodigo()) {
            return actual.getDato();
        }

        if (codigo < actual.getDato().getCodigo()) {
            return buscar(actual.getIzquierda(), codigo);
        } else {
            return buscar(actual.getDerecha(), codigo);
        }
    }

    public String inOrden() {
        if (raiz == null) {
            return "No hay tickets registrados.";
        }
        return inOrden(raiz);
    }

    private String inOrden(NodoTicket actual) {
        if (actual != null) {
            return inOrden(actual.getIzquierda()) +
                    actual.getDato().toString() +
                    "\n-------------------------\n" +
                    inOrden(actual.getDerecha());
        }
        return "";
    }
}