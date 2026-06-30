public class NodoTicket {

    private Ticket dato;
    private NodoTicket izquierda;
    private NodoTicket derecha;

    public NodoTicket(Ticket dato) {
        this.dato = dato;
        izquierda = derecha = null;
    }

    public Ticket getDato() {
        return dato;
    }

    public void setDato(Ticket dato) {
        this.dato = dato;
    }

    public NodoTicket getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoTicket izquierda) {
        this.izquierda = izquierda;
    }

    public NodoTicket getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoTicket derecha) {
        this.derecha = derecha;
    }
}