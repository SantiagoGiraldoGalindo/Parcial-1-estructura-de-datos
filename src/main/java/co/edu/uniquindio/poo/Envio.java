package co.edu.uniquindio.poo;

public class Envio {

    private int id;
    private Paquete paquete;


    public Envio(int id, Paquete paquete) {
        this.id = id;
        this.paquete = paquete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }
}
