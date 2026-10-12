package co.edu.uniquindio.poo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Paquete paquete1= new Paquete("P001", 5,2,50,"Armenia");
        Paquete paquete2= new Paquete("P002", 6,4,40,"Armenia");

        Repartidor repartidor1 = new Repartidor("Robinson", "Bucaramanga", 1234, true, paquete1);


        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;



        while (continuar) {
            System.out.println("\n------------- QuindioExpress ------------");
            System.out.println("Opciones:");
            System.out.println("1: Agregar un Paquete");
            System.out.println("2: Eliminar un Paquete");
            System.out.println("3: Mostrar Paquetes");
            System.out.println("4: Salir");
            System.out.print("Elige una opción: ");

            // Leemos la opción elegida por el usuario
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println(">> Has seleccionado: Agregar un elemento");

                    break;
                case 2:
                    System.out.println(">> Has seleccionado: Eliminar un elemento");

                    break;
                case 3:
                    System.out.println(">> Has seleccionado: Mostrar elementos");

                    break;
                case 4:
                    System.out.println("Saliendo de QuindioExpress... ¡Hasta pronto!");
                    continuar = false;
                    break;
                default:
                    System.out.println(">> Opción no válida. Por favor, intenta de nuevo.");
            }
        }

        scanner.close();
    }
}
