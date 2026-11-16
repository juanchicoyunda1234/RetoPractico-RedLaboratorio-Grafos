package negocio;

import modelo.Vertice;

public class Cola {
    private class NodoCola {
        Vertice dato;
        NodoCola siguiente;

        NodoCola(Vertice dato) {
            this.dato = dato;
        }
    }

    private NodoCola frente;
    private NodoCola fin;

    public boolean estaVacia() {
        return frente == null;
    }

    public void encolar(Vertice v) {
        NodoCola nuevo = new NodoCola(v);
        if (estaVacia()) {
            frente = nuevo;
        } else {
            fin.siguiente = nuevo;
        }
        fin = nuevo;
    }

    public Vertice desencolar() {
        if (estaVacia()) {
            return null;
        }
        Vertice v = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        return v;
    }
}
