package co.edu.uniquindio.poo;

import java.io.Serializable;
import java.util.Objects;

import java.util.Objects;

// Implementamos Comparable tipado a <Paquete>
public class Paquete implements Comparable<Paquete> {

    private String codigo;
    private double peso;
    private int prioridad;
    private int tiempoEstimado;
    private String destino;

    public Paquete(String codigo, double peso, int prioridad, int tiempoEstimado, String destino) {

        if (peso <= 0) throw new IllegalArgumentException("El peso debe ser mayor que cero.");
        if (prioridad < 1 || prioridad > 5) throw new IllegalArgumentException("La prioridad debe estar entre 1 y 5.");
        if (tiempoEstimado <= 0) throw new IllegalArgumentException("El tiempo estimado debe ser mayor que cero.");
        if (destino == null || destino.trim().isEmpty()) throw new IllegalArgumentException("El destino no puede estar vacío.");

        this.codigo = codigo;
        this.peso = peso;
        this.prioridad = prioridad;
        this.tiempoEstimado = tiempoEstimado;
        this.destino = destino;
    }


    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public int getPrioridad() { return prioridad; }
    public void setPrioridad(int prioridad) { this.prioridad = prioridad; }

    public int getTiempoEstimado() { return tiempoEstimado; }
    public void setTiempoEstimado(int tiempoEstimado) { this.tiempoEstimado = tiempoEstimado; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }


    @Override
    public int compareTo(Paquete otro) {
        return this.codigo.compareTo(otro.codigo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paquete paquete = (Paquete) o;
        return Objects.equals(codigo, paquete.codigo); // Dos paquetes son iguales si tienen el mismo código
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    
}