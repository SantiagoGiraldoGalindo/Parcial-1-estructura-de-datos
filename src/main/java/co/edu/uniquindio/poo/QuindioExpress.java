package co.edu.uniquindio.poo;

import java.util.*;

public class QuindioExpress {

 private Queue<Paquete> listaOrdenLlegada;
 private Map<String, Paquete> mapaBusquedaRapida; // Llave: String (código), Valor: Paquete
 private Set<String> municipiosOrdenados;         // Guarda solo los nombres (Strings)
 private PriorityQueue<Paquete> listaPaqueteDespachoPrioridad;
 private List<Repartidor> listaRepartidor;

 private ListaSimplementeEnlazada<Paquete> historialEntregas; //Estructura propia


 public QuindioExpress() {
  listaOrdenLlegada = new LinkedList<>();
  mapaBusquedaRapida = new HashMap<>();
  municipiosOrdenados = new TreeSet<>();
  listaRepartidor = new ArrayList<>();
  historialEntregas = new ListaSimplementeEnlazada<>();

  listaPaqueteDespachoPrioridad = new PriorityQueue<>((p1, p2) -> {
   // 1. Mayor prioridad primero
   if (p1.getPrioridad() != p2.getPrioridad()) {
    return Integer.compare(p2.getPrioridad(), p1.getPrioridad());
   }
   // 2. Menor tiempo estimado primero
   if (p1.getTiempoEstimado() != p2.getTiempoEstimado()) {
    return Integer.compare(p1.getTiempoEstimado(), p2.getTiempoEstimado());
   }
   // 3. Alfabético por código
   return p1.getCodigo().compareTo(p2.getCodigo());
  });
 }

//Metodo para agregar un paquete a todas las listas distintas
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
//Metodo para agregar un repartidor
 public void agregarRepartidor(Repartidor repartidor) {
  listaRepartidor.add(repartidor);
 }


//Bucar paquete por codigo en un mapa
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
//obtener paquete de mayor peso en un mapa
 public Paquete obtenerPaqueteMayorPeso() {
  if (mapaBusquedaRapida.isEmpty()) {
   System.out.println("No hay paquetes registrados.");
   return null;
  }

  // Convertimos los valores del mapa a una Lista para acceder por índices
  List<Paquete> listaPaquetes = new ArrayList<>(mapaBusquedaRapida.values());

  return encontrarMayorDivideYVenceras(listaPaquetes, 0, listaPaquetes.size() - 1);
 }

//Metodo para encontrar el paquete de mayor peso usando DyV
 private Paquete encontrarMayorDivideYVenceras(List<Paquete> paquetes, int inicio, int fin) {


  if (inicio == fin) {
   return paquetes.get(inicio);
  }

  int mitad = (inicio + fin) / 2;

  Paquete paqueteIzquierdo = encontrarMayorDivideYVenceras(paquetes, inicio, mitad);

  Paquete paqueteDerecho = encontrarMayorDivideYVenceras(paquetes, mitad + 1, fin);

  Paquete resultadoFinal;
  if (paqueteIzquierdo.getPeso() > paqueteDerecho.getPeso()) {
   resultadoFinal = paqueteIzquierdo;
  } else {
   resultadoFinal = paqueteDerecho;
  }

  return resultadoFinal;
 }

//Metodo para sumar el peso total de los paquetes por municipios

 public double obtenerPesoTotalPorMunicipio(String municipio) {
  if (mapaBusquedaRapida.isEmpty() || municipio == null) {
   System.out.println("No hay paquetes registrados o municipio inválido.");
   return 0.0;
  }


  List<Paquete> listaPaquetes = new ArrayList<>(mapaBusquedaRapida.values());


  return sumarPesoRecursivo(listaPaquetes, municipio, 0);
 }

//metodo recursivo para sumar el peso de los paquetes
 private double sumarPesoRecursivo(List<Paquete> paquetes, String municipio, int indice) {
  // 1. CASO BASE: Si el índice llegó al final de la lista, ya no hay más paquetes. Retornamos 0.
  if (indice == paquetes.size()) {
   return 0.0;
  }
  // Tomamos el paquete en la posición actual
  Paquete paqueteActual = paquetes.get(indice);
  double pesoAAgregar = 0.0;

  // Si el destino del paquete es igual al municipio buscado, tomamos su peso
  if (paqueteActual.getDestino().equalsIgnoreCase(municipio)) {
   pesoAAgregar = paqueteActual.getPeso();
  }


  // 2. CASO RECURSIVO Y AVANCE: Sumamos el peso encontrado + la llamada al siguiente índice (indice + 1)
  return pesoAAgregar + sumarPesoRecursivo(paquetes, municipio, indice + 1);
 }


//motrar cuantos paquetes tiene una prioridad igual
 public int contarPaquetesPorPrioridad(int prioridadMinima) {
  if (mapaBusquedaRapida.isEmpty()) {
   System.out.println("No hay paquetes registrados.");
   return 0;
  }

  List<Paquete> listaPaquetes = new ArrayList<>(mapaBusquedaRapida.values());

  return contarPaquetesPrioridadRecursivo(listaPaquetes, prioridadMinima, 0);
 }


 private int contarPaquetesPrioridadRecursivo(List<Paquete> paquetes, int prioridadMinima, int indice) {

  if (indice == paquetes.size()) {
   return 0;
  }

  Paquete paqueteActual = paquetes.get(indice);
  int cuentaActual = 0;

  if (paqueteActual.getPrioridad() >= prioridadMinima) {
   cuentaActual = 1;
  }

  return cuentaActual + contarPaquetesPrioridadRecursivo(paquetes, prioridadMinima, indice + 1);
 }

//metodo para buscar un paquete por su codigo
 public Paquete buscarPaqueteBinario(String codigoBuscado) {
  List<Paquete> listaOrdenada = new ArrayList<>(mapaBusquedaRapida.values());
  Collections.sort(listaOrdenada); // Usa el compareTo de Paquete

  int inicio = 0;
  int fin = listaOrdenada.size() - 1;

  while (inicio <= fin) {
   int medio = inicio + (fin - inicio) / 2;
   Paquete paqueteMedio = listaOrdenada.get(medio);
   int comparacion = paqueteMedio.getCodigo().compareTo(codigoBuscado);

   if (comparacion == 0) return paqueteMedio;
   if (comparacion < 0) inicio = medio + 1;
   else fin = medio - 1;
  }
  return null; // No encontrado
 }
//Metodo para asignar un paque a un repartidor

 public void asignarRepartidorYGuardarHistorial(Paquete paquete) {
  for (Repartidor rep : listaRepartidor) {
   if (rep.isDisponible() && rep.getZona().equals(paquete.getDestino())) {
    rep.setDisponible(false); // Se ocupa
    historialEntregas.agregar(paquete); // Se guarda en la lista propia
    mapaBusquedaRapida.remove(paquete.getCodigo());
    listaOrdenLlegada.remove(paquete);
    System.out.println("Paquete " + paquete.getCodigo() + " despachado con " + rep.getNombre());
    return;
   }
  }
  System.out.println("No hay repartidor disponible para la zona: " + paquete.getDestino());
  listaPaqueteDespachoPrioridad.offer(paquete); // Se devuelve si no hay repartidor
 }


//Orden natural de los paquetes

 public void mostrarPaquetesOrdenadosPor(String criterio) {
  if (mapaBusquedaRapida.isEmpty()) {
   System.out.println("No hay paquetes para ordenar.");
   return;
  }

  // Sacamos los paquetes y los metemos en una lista temporal
  List<Paquete> listaAOrdenar = new ArrayList<>(mapaBusquedaRapida.values());

  // Aplicamos el ordenamiento directamente aquí usando "Lambdas" ( -> )
  switch (criterio.toLowerCase()) {
   case "prioridad":
    // Ordena por prioridad (Mayor a Menor)
    listaAOrdenar.sort((p1, p2) -> Integer.compare(p2.getPrioridad(), p1.getPrioridad()));
    System.out.println("--- Ordenado por PRIORIDAD (Mayor a Menor) ---");
    break;

   case "peso":
    // Ordena por peso (Mayor a Menor)
    listaAOrdenar.sort((p1, p2) -> Double.compare(p2.getPeso(), p1.getPeso()));
    System.out.println("--- Ordenado por PESO (Mayor a Menor) ---");
    break;

   case "tiempo":
    // Ordena por tiempo estimado (Menor a Mayor)
    listaAOrdenar.sort((p1, p2) -> Integer.compare(p1.getTiempoEstimado(), p2.getTiempoEstimado()));
    System.out.println("--- Ordenado por TIEMPO (Menor a Mayor) ---");
    break;

   default:
    // Si no mandan nada válido, usa el orden natural (El compareTo de la clase Paquete)
    Collections.sort(listaAOrdenar);
    System.out.println("--- Ordenado por CÓDIGO (Orden Natural) ---");
  }

  // Imprimimos el resultado
  for (Paquete p : listaAOrdenar) {
   System.out.println("Código: " + p.getCodigo() +
           " | Prioridad: " + p.getPrioridad() +
           " | Peso: " + p.getPeso() + "kg" +
           " | Tiempo: " + p.getTiempoEstimado() + " min");
  }
 }


 public void asignarPaqueteManual(String codigoPaquete, int idRepartidor) {

  // 1. Buscamos el paquete en el sistema
  Paquete paquete = mapaBusquedaRapida.get(codigoPaquete);
  if (paquete == null) {
   System.out.println("Error: No se encontró ningún paquete con el código " + codigoPaquete);
   return;
  }

  // 2. Buscamos al repartidor por su identificación
  Repartidor repartidorSeleccionado = null;
  for (Repartidor rep : listaRepartidor) {
   if (rep.getIdentificacion() == idRepartidor) {
    repartidorSeleccionado = rep;
    break;
   }
  }

  if (repartidorSeleccionado == null) {
   System.out.println("Error: No se encontró ningún repartidor con el ID " + idRepartidor);
   return;
  }

  // 3. Validamos que el repartidor esté libre
  if (!repartidorSeleccionado.isDisponible()) {
   System.out.println("El repartidor " + repartidorSeleccionado.getNombre() + " no está disponible en este momento.");
   return;
  }

  // 4. Hacemos la asignación y retiramos el paquete de las estructuras
  repartidorSeleccionado.setDisponible(false); // Lo ocupamos
  historialEntregas.agregar(paquete);          // Se guarda en tu lista enlazada propia

  // Lo sacamos de las colecciones pendientes
  mapaBusquedaRapida.remove(paquete.getCodigo());
  listaOrdenLlegada.remove(paquete);
  listaPaqueteDespachoPrioridad.remove(paquete);

  System.out.println("¡Éxito! Paquete " + paquete.getCodigo() + " asignado manualmente a " + repartidorSeleccionado.getNombre());
 }

//getters and setters
 public Queue<Paquete> getListaOrdenLlegada() {
  return listaOrdenLlegada;
 }

 public void setListaOrdenLlegada(Queue<Paquete> listaOrdenLlegada) {
  this.listaOrdenLlegada = listaOrdenLlegada;
 }

 public Map<String, Paquete> getMapaBusquedaRapida() {
  return mapaBusquedaRapida;
 }

 public void setMapaBusquedaRapida(Map<String, Paquete> mapaBusquedaRapida) {
  this.mapaBusquedaRapida = mapaBusquedaRapida;
 }

 public Set<String> getMunicipiosOrdenados() {
  return municipiosOrdenados;
 }

 public void setMunicipiosOrdenados(Set<String> municipiosOrdenados) {
  this.municipiosOrdenados = municipiosOrdenados;
 }

 public PriorityQueue<Paquete> getListaPaqueteDespachoPrioridad() {
  return listaPaqueteDespachoPrioridad;
 }

 public void setListaPaqueteDespachoPrioridad(PriorityQueue<Paquete> listaPaqueteDespachoPrioridad) {
  this.listaPaqueteDespachoPrioridad = listaPaqueteDespachoPrioridad;
 }

 public List<Repartidor> getListaRepartidor() {
  return listaRepartidor;
 }

 public void setListaRepartidor(List<Repartidor> listaRepartidor) {
  this.listaRepartidor = listaRepartidor;
 }

 public ListaSimplementeEnlazada<Paquete> getHistorialEntregas() {
  return historialEntregas;
 }

 public void setHistorialEntregas(ListaSimplementeEnlazada<Paquete> historialEntregas) {
  this.historialEntregas = historialEntregas;
 }
}

