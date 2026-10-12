package co.edu.uniquindio.poo;


import java.util.Iterator;

public class ListaSimplementeEnlazada<T> implements ColeccionQuindioExpress<T> {

    private NodeSimple<T> cabeza;
    private int tamano;

    public ListaSimplementeEnlazada() {
        this.cabeza = null;
        this.tamano = 0;
    }

    @Override
    public boolean estaVacia() {
        return cabeza == null;
    }

    @Override
    public int obtenerTamano() {
        return tamano;
    }

    @Override
    public void agregar(T elemento) {
        NodeSimple<T> nuevoNodeSimple = new NodeSimple<>(elemento);

        // Caso: La lista está vacía
        if (estaVacia()) {
            cabeza = nuevoNodeSimple;
        } else {
            // Caso: Insertar al final para mantener el orden de llegada/entrega
            NodeSimple<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodeSimple);
        }
        tamano++;
    }

    @Override
    public void eliminar(int posicion) {
        // Validación de elemento inexistente
        if (posicion < 0 || posicion >= tamano) {
            throw new IndexOutOfBoundsException("La posición " + posicion + " es inválida o no existe.");
        }

        // Caso: Eliminación del primer elemento
        if (posicion == 0) {
            cabeza = cabeza.getSiguiente();
        } else {
            // Caso: Eliminación intermedio o del último elemento
            NodeSimple<T> actual = cabeza;
            for (int i = 0; i < posicion - 1; i++) {
                actual = actual.getSiguiente();
            }

            // "Saltamos" el NodeSimple que queremos eliminar, puenteando al siguiente
            NodeSimple<T> nodoAEliminar = actual.getSiguiente();
            actual.setSiguiente(nodoAEliminar.getSiguiente());
        }
        tamano--;
    }

    @Override
    public T obtener(int posicion) {
        if (posicion < 0 || posicion >= tamano) {
            throw new IndexOutOfBoundsException("La posición " + posicion + " es inválida o no existe.");
        }

        NodeSimple<T> actual = cabeza;
        for (int i = 0; i < posicion; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getValor();
    }

    @Override
    public Iterator<T> iterator() {
        // Retornamos directamente una interfaz Iterator (Clase anónima)
        return new Iterator<T>() {

            private NodeSimple<T> actual = cabeza;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException("No hay más elementos en la lista");
                }
                T valor = actual.getValor();
                actual = actual.getSiguiente();
                return valor;
            }
        };
    }
}