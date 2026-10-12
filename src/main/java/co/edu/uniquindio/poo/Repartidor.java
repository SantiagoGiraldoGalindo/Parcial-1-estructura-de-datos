package co.edu.uniquindio.poo;

public class Repartidor {

    private String nombre;
    private String zona;
    private int identificacion;
    private boolean disponible;
    private Paquete paquete;

    public Repartidor( String nombre, String zona, int identificacion, boolean disponible, Paquete paquete ) {
        this.nombre = nombre;
        this.zona = zona;
        this.identificacion = identificacion;
        this.disponible = disponible;
        this.paquete = paquete;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }
}
