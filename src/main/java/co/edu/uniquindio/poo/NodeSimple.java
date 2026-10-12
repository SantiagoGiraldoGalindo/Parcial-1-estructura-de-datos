package co.edu.uniquindio.poo;


public class NodeSimple<T> {
    private T valor;
    private NodeSimple<T> siguiente;

    public NodeSimple(T valor) {
        this.valor = valor;
        this.siguiente = null;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public NodeSimple<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodeSimple<T> siguiente) {
        this.siguiente = siguiente;
    }
}


