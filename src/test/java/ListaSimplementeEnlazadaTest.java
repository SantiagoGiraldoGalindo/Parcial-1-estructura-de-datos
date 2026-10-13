

import co.edu.uniquindio.poo.ListaSimplementeEnlazada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListaSimplementeEnlazadaTest {

    private ListaSimplementeEnlazada<String> lista;

    @BeforeEach
    public void setUp() {
        // Usamos String para probar la lista genérica de forma sencilla
        lista = new ListaSimplementeEnlazada<>();
    }

    @Test
    public void testAgregarYTamano() {
        assertTrue(lista.estaVacia(), "La lista debería empezar vacía");

        lista.agregar("Paquete1");
        lista.agregar("Paquete2");

        assertFalse(lista.estaVacia(), "La lista ya no debería estar vacía");
        assertEquals(2, lista.obtenerTamano(), "El tamaño debería ser 2");
    }

    @Test
    public void testObtenerElemento() {
        lista.agregar("PaqueteA");
        lista.agregar("PaqueteB");

        assertEquals("PaqueteA", lista.obtener(0), "El primer elemento debe ser PaqueteA");
        assertEquals("PaqueteB", lista.obtener(1), "El segundo elemento debe ser PaqueteB");
    }

    @Test
    public void testEliminarPrimerElemento() {
        lista.agregar("Paquete1");
        lista.agregar("Paquete2");

        lista.eliminar(0); // Eliminamos el primero

        assertEquals(1, lista.obtenerTamano(), "El tamaño debe reducirse a 1");
        assertEquals("Paquete2", lista.obtener(0), "El elemento restante debe pasar a la posición 0");
    }

    @Test
    public void testEliminarElementoIntermedio() {
        lista.agregar("P1");
        lista.agregar("P2");
        lista.agregar("P3");

        lista.eliminar(1); // Eliminamos "P2"

        assertEquals(2, lista.obtenerTamano(), "El tamaño debe ser 2");
        assertEquals("P3", lista.obtener(1), "P3 debió recorrerse a la posición 1");
    }

    @Test
    public void testEliminarUltimoElemento() {
        lista.agregar("P1");
        lista.agregar("P2");

        lista.eliminar(1); // Eliminamos el último ("P2")

        assertEquals(1, lista.obtenerTamano());
        assertEquals("P1", lista.obtener(0));
    }

    @Test
    public void testEliminarFueraDeRangoLanzaExcepcion() {
        lista.agregar("P1");

        // Verificamos que al intentar eliminar algo que no existe, lance el error correcto
        assertThrows(IndexOutOfBoundsException.class, () -> {
            lista.eliminar(5);
        }, "Debería lanzar excepción si la posición no existe");

        // Al intentar eliminar en una lista vacía
        ListaSimplementeEnlazada<String> listaVacia = new ListaSimplementeEnlazada<>();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            listaVacia.eliminar(0);
        });
    }
}
