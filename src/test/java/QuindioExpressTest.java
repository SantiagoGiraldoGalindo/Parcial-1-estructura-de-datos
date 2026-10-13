

import co.edu.uniquindio.poo.ListaSimplementeEnlazada;
import co.edu.uniquindio.poo.Paquete;
import co.edu.uniquindio.poo.QuindioExpress;
import co.edu.uniquindio.poo.Repartidor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuindioExpressTest {

    private QuindioExpress empresa;

    @BeforeEach
    public void setUp() {
        empresa = new QuindioExpress();

        // Datos controlados para que las aserciones sean exactas
        empresa.agregarPaquete(new Paquete("PQ731", 4.5, 5, 45, "Armenia"));
        empresa.agregarPaquete(new Paquete("PQ105", 3.0, 5, 25, "Salento"));
        empresa.agregarPaquete(new Paquete("PQ942", 2.3, 4, 20, "Armenia"));
        empresa.agregarPaquete(new Paquete("PQ318", 5.5, 2, 45, "Calarcá"));
        empresa.agregarPaquete(new Paquete("PQ567", 6.8, 3, 40, "Salento"));
        empresa.agregarPaquete(new Paquete("PQ829", 7.2, 3, 30, "Armenia"));
    }

    @Test
    public void testBuscarPaqueteBinario_Encontrado() {
        // Ejecución
        Paquete paquete = empresa.buscarPaqueteBinario("PQ731");

        // Verificación
        assertNotNull(paquete, "El paquete no debería ser nulo");
        assertEquals("PQ731", paquete.getCodigo(), "El código debe coincidir");
        assertEquals(4.5, paquete.getPeso(), "El peso debe coincidir");
    }

    @Test
    public void testBuscarPaqueteBinario_NoEncontrado() {
        // Ejecución
        Paquete paquete = empresa.buscarPaqueteBinario("PQ000");

        // Verificación
        assertNull(paquete, "Si el código no existe, debe retornar null");
    }

    @Test
    public void testSumaRecursivaPesoPorMunicipio() {
        // Armenia tiene PQ731 (4.5), PQ942 (2.3) y PQ829 (7.2). Suma = 14.0
        double pesoTotal = empresa.obtenerPesoTotalPorMunicipio("Armenia");

        // Verificación con margen de error (delta) de 0.001 por ser double
        assertEquals(14.0, pesoTotal, 0.001, "La suma recursiva de los pesos para Armenia debe ser 14.0");
    }

    @Test
    public void testContarPrioridadRecursivo() {
        // Prioridad >= 4: PQ731(5), PQ105(5), PQ942(4). Total = 3 paquetes
        int cantidadAltaPrioridad = empresa.contarPaquetesPorPrioridad(4);

        assertEquals(3, cantidadAltaPrioridad, "Deben existir 3 paquetes con prioridad 4 o 5");
    }

    @Test
    public void testDivideYVencerasMayorPeso() {
        // De los registrados, el más pesado es PQ829 con 7.2 kg
        Paquete masPesado = empresa.obtenerPaqueteMayorPeso();

        assertNotNull(masPesado, "El paquete más pesado no debe ser nulo");
        assertEquals(7.2, masPesado.getPeso(), "El peso mayor mediante Divide y Vencerás debe ser 7.2");
        assertEquals("PQ829", masPesado.getCodigo(), "El código del paquete más pesado debe ser PQ829");
    }



    @Test
    public void testEstructuraPropiaHistorial() {
        // Configuramos repartidor y despachamos
        empresa.agregarRepartidor(new Repartidor("Carlos", "Armenia", 123, true, null));
        Paquete p = empresa.getMapaBusquedaRapida().get("PQ731");

        empresa.asignarRepartidorYGuardarHistorial(p);

        // Verificamos que la lista propia funcione y registre el tamaño correcto
        ListaSimplementeEnlazada<Paquete> historial = empresa.getHistorialEntregas();
        assertFalse(historial.estaVacia(), "El historial no debe estar vacío tras un despacho");
        assertEquals(1, historial.obtenerTamano(), "El tamaño del historial debe ser 1");
    }
}