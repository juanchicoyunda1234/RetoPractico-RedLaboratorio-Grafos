package modelo;

public class Vertice {
    private String id;
    private Vertice siguiente;
    private Arista primeraArista;
    private boolean visitado;

    public Vertice(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Vertice getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Vertice siguiente) {
        this.siguiente = siguiente;
    }

    public Arista getPrimeraArista() {
        return primeraArista;
    }

    public void setPrimeraArista(Arista primeraArista) {
        this.primeraArista = primeraArista;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
}
