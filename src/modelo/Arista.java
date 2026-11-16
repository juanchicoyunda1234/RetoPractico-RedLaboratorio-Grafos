package modelo;

public class Arista {
    private Vertice destino;
    private int latencia;
    private Arista siguiente;

    public Arista(Vertice destino, int latencia) {
        this.destino = destino;
        this.latencia = latencia;
    }

    public Vertice getDestino() {
        return destino;
    }

    public int getLatencia() {
        return latencia;
    }

    public void setLatencia(int latencia) {
        this.latencia = latencia;
    }

    public Arista getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Arista siguiente) {
        this.siguiente = siguiente;
    }
}
