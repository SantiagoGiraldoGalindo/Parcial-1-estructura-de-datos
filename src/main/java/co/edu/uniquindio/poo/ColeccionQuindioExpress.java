package co.edu.uniquindio.poo;



public interface ColeccionQuindioExpress<T> extends Iterable<T> {

    void agregar(T elemento);


    void eliminar(int posicion);

    T obtener(int posicion);

    int obtenerTamano();

    boolean estaVacia();
}