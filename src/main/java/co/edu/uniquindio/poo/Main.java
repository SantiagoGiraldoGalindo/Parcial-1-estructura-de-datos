package co.edu.uniquindio.poo;

import java.util.Scanner;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        QuindioExpress empresa = new QuindioExpress();

        String[] destinos = {"Armenia", "Calarcá", "Salento", "Quimbaya", "Filandia"};

        // Crear y agregar los 6 repartidores obligatorios
        Repartidor r1 = new Repartidor("Juan Pérez", "Armenia", 101, true, null);
        Repartidor r2 = new Repartidor("María Gómez", "Calarcá", 102, true, null);
        Repartidor r3 = new Repartidor("Carlos Ruiz", "Salento", 103, true, null);
        Repartidor r4 = new Repartidor("Ana López", "Quimbaya", 104, true, null);
        Repartidor r5 = new Repartidor("Luis Díaz", "Filandia", 105, true, null);
        Repartidor r6 = new Repartidor("Sofía Castro", "Armenia", 106, true, null);

        empresa.agregarRepartidor(r1);
        empresa.agregarRepartidor(r2);
        empresa.agregarRepartidor(r3);
        empresa.agregarRepartidor(r4);
        empresa.agregarRepartidor(r5);
        empresa.agregarRepartidor(r6);

        System.out.println("--- GENERANDO 30 PAQUETES AUTOMÁTICAMENTE ---");
        for (int i = 1; i <= 30; i++) {
            String codigo = "PQ" + (100 + (i * 3));
            double peso = 2.0 + (i * 0.5);
            int prioridad = (i % 5) + 1;
            int tiempo = 15 + (i * 2);
            String destino = destinos[i % 5];

            Paquete p = new Paquete(codigo, peso, prioridad, tiempo, destino);
            empresa.agregarPaquete(p);
        }
        System.out.println("--- CARGA DE DATOS COMPLETADA ---\n");

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n============= QuindioExpress =============");
            System.out.println("1: Agregar un Paquete manual");
            System.out.println("2: Mostrar paquetes ordenados (Criterios)");
            System.out.println("3: Buscar paquete Rápido (Por Código)");
            System.out.println("4: Buscar paquete Binario (Por Código)");
            System.out.println("5: Obtener paquete más pesado (Divide y Vencerás)");
            System.out.println("6: Calcular peso total por Municipio (Recursivo)");
            System.out.println("7: Contar paquetes por prioridad mínima (Recursivo)");
            System.out.println("8: Despachar paquete con mayor prioridad");
            System.out.println("9: Ver historial de entregas (Estrucutra propia)");
            System.out.println("10: Asignar paquete a repartidor (MANUAL)");
            System.out.println("0: Salir");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    System.out.println("\n-- AGREGAR PAQUETE --");
                    System.out.print("Código: ");
                    String cod = scanner.nextLine();
                    System.out.print("Peso (ej. 2,5): ");
                    double pes = scanner.nextDouble();
                    System.out.print("Prioridad (1 a 5): ");
                    int prio = scanner.nextInt();
                    System.out.print("Tiempo estimado (min): ");
                    int tiempo = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                    System.out.print("Destino (Municipio): ");
                    String dest = scanner.nextLine();

                    try {
                        Paquete nuevo = new Paquete(cod, pes, prio, tiempo, dest);
                        empresa.agregarPaquete(nuevo);
                    } catch (Exception e) {
                        System.out.println("Error al crear paquete: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("\n-- ORDENAR PAQUETES --");
                    System.out.print("Criterio (prioridad, peso, tiempo, o deje vacío para código natural): ");
                    String criterio = scanner.nextLine();
                    empresa.mostrarPaquetesOrdenadosPor(criterio);
                    break;

                case 3:
                    System.out.println("\n-- BÚSQUEDA RÁPIDA (HASHMAP) --");
                    System.out.print("Ingrese el código a buscar: ");
                    String codBuscar = scanner.nextLine();
                    empresa.buscarPaquete(codBuscar);
                    break;

                case 4:
                    System.out.println("\n-- BÚSQUEDA BINARIA --");
                    System.out.print("Ingrese el código a buscar: ");
                    String codBinario = scanner.nextLine();
                    Paquete enc = empresa.buscarPaqueteBinario(codBinario);
                    if(enc != null) {
                        System.out.println("Encontrado: " + enc.getCodigo() + " con destino a " + enc.getDestino());
                    } else {
                        System.out.println("Paquete no encontrado mediante búsqueda binaria.");
                    }
                    break;

                case 5:
                    System.out.println("\n-- PAQUETE MÁS PESADO (DYV) --");
                    Paquete pMayor = empresa.obtenerPaqueteMayorPeso();
                    if(pMayor != null) {
                        System.out.println("El paquete más pesado es " + pMayor.getCodigo() + " con " + pMayor.getPeso() + "kg");
                    }
                    break;

                case 6:
                    System.out.println("\n-- PESO TOTAL POR MUNICIPIO (RECURSIVO) --");
                    System.out.print("Ingrese el municipio: ");
                    String muni = scanner.nextLine();
                    double pesoTotal = empresa.obtenerPesoTotalPorMunicipio(muni);
                    System.out.println("El peso total de los paquetes para " + muni + " es: " + pesoTotal + "kg");
                    break;

                case 7:
                    System.out.println("\n-- CONTAR POR PRIORIDAD (RECURSIVO) --");
                    System.out.print("Ingrese la prioridad mínima: ");
                    int prioMin = scanner.nextInt();
                    int cantidad = empresa.contarPaquetesPorPrioridad(prioMin);
                    System.out.println("Hay " + cantidad + " paquetes con prioridad igual o mayor a " + prioMin);
                    break;

                case 8:
                    System.out.println("\n-- DESPACHAR PAQUETE --");
                    // Sacamos el paquete de mayor prioridad de la PriorityQueue
                    Paquete pDespachar = empresa.getListaPaqueteDespachoPrioridad().poll();
                    if (pDespachar != null) {
                        empresa.asignarRepartidorYGuardarHistorial(pDespachar);
                    } else {
                        System.out.println("No hay paquetes pendientes para despachar.");
                    }
                    break;

                case 9:
                    System.out.println("\n-- HISTORIAL DE ENTREGAS (LISTA ENLAZADA PROPIA) --");
                    if (empresa.getHistorialEntregas().estaVacia()) {
                        System.out.println("Aún no se ha entregado ningún paquete.");
                    } else {
                        for (Paquete p : empresa.getHistorialEntregas()) {
                            System.out.println("Entregado -> " + p.getCodigo() + " a " + p.getDestino());
                        }
                    }
                    break;
                case 10:
                    System.out.println("\n-- ASIGNAR PAQUETE MANUALMENTE --");
                    // Mostrar repartidores disponibles para que el usuario sepa cuáles elegir
                    System.out.println("Repartidores disponibles:");
                    for (Repartidor r : empresa.getListaRepartidor()) {
                        if (r.isDisponible()) {
                            System.out.println("ID: " + r.getIdentificacion() + " | Nombre: " + r.getNombre() + " | Zona: " + r.getZona());
                        }
                    }

                    System.out.print("\nIngrese el código del paquete a despachar (Ej. PQ103): ");
                    String codManual = scanner.nextLine();

                    System.out.print("Ingrese el ID del repartidor: ");
                    int idRep = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer

                    empresa.asignarPaqueteManual(codManual, idRep);
                    break;

                case 0:
                    System.out.println("Saliendo de QuindioExpress");
                    continuar = false;
                    break;

                default:
                    System.out.println(">> Opción no válida. Por favor, intenta de nuevo.");
            }
        }
        scanner.close();
    }
}