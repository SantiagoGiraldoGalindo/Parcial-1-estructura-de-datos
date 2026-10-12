package co.edu.uniquindio.poo;

import java.util.*;

public class QuindioExpress {

 // 1. Declaración correcta de las colecciones
 private Queue<Paquete> listaOrdenLlegada;
 private Map<String, Paquete> mapaBusquedaRapida; // Llave: String (código), Valor: Paquete
 private Set<String> municipiosOrdenados;         // Guarda solo los nombres (Strings)
 private PriorityQueue<Paquete> listaPaqueteDespachoPrioridad;
 private List<Repartidor> listaRepartidor;


 public QuindioExpress() {
  listaOrdenLlegada = new LinkedList<>();
  mapaBusquedaRapida = new HashMap<>();
  municipiosOrdenados = new TreeSet<>();
  listaRepartidor = new ArrayList<>();


  listaPaqueteDespachoPrioridad = new PriorityQueue<>();
 }


 public void agregarPaquete(Paquete paquete) {

  if (mapaBusquedaRapida.containsKey(paquete.getCodigo())) {
   System.out.println("Error: Ya existe un paquete con el código " + paquete.getCodigo());
  } else {

   mapaBusquedaRapida.put(paquete.getCodigo(), paquete);
   listaOrdenLlegada.offer(paquete);
   listaPaqueteDespachoPrioridad.offer(paquete);
   municipiosOrdenados.add(paquete.getDestino());

   System.out.println("Paquete " + paquete.getCodigo() + " agregado con éxito.");
  }
 }


 public void buscarPaquete(String codigo) {
  if(codigo == null) {
   System.out.println("Por favor ingrese un código válido.");
   return;
  }

  Paquete paqueteEncontrado = mapaBusquedaRapida.get(codigo);

  if (paqueteEncontrado != null) {
   System.out.println("Paquete encontrado: Va para " + paqueteEncontrado.getDestino());
  } else {
   System.out.println("No se encontró ningún paquete con el código " + codigo);
  }
 }

 // (Aquí puedes dejar tus getters y setters de las listas si los necesitas)
}

