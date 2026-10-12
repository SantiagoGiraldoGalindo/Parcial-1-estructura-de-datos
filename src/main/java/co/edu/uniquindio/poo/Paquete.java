package co.edu.uniquindio.poo;

import java.io.Serializable;
import java.util.Objects;

public class Paquete implements Comparable {

    private String codigo;
    private int peso;
    private int prioridad;
    private int tiempoEstimado;
    private String destino;

   public Paquete( String codigo, int peso, int prioridad, int tiempoEstimado,String destino) {
       this.codigo = codigo;
       this.peso = peso;
       this.prioridad = prioridad;
       this.tiempoEstimado = tiempoEstimado;
       this.destino = destino;

   }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(int tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Paquete paquete = (Paquete) o;
        return peso == paquete.peso && prioridad == paquete.prioridad && tiempoEstimado == paquete.tiempoEstimado && Objects.equals(codigo, paquete.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, peso, prioridad, tiempoEstimado);
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}
