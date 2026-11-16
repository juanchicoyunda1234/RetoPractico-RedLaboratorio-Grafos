package negocio;

import modelo.Arista;
import modelo.Vertice;

public class Grafo {
    private Vertice primero;
    private int cantidad;

    public Vertice buscar(String id) {
        Vertice actual = primero;
        while (actual != null) {
            if (actual.getId().equals(id)) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public boolean agregarVertice(String id) {
        if (buscar(id) != null) {
            return false;
        }
        Vertice nuevo = new Vertice(id);
        if (primero == null) {
            primero = nuevo;
        } else {
            Vertice actual = primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        cantidad++;
        return true;
    }

    public boolean agregarArista(String origen, String destino, int latencia) {
        Vertice o = buscar(origen);
        Vertice d = buscar(destino);
        if (o == null || d == null || buscarArista(o, destino) != null) {
            return false;
        }
        Arista nueva = new Arista(d, latencia);
        if (o.getPrimeraArista() == null) {
            o.setPrimeraArista(nueva);
        } else {
            Arista actual = o.getPrimeraArista();
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nueva);
        }
        return true;
    }

    private Arista buscarArista(Vertice origen, String destino) {
        Arista actual = origen.getPrimeraArista();
        while (actual != null) {
            if (actual.getDestino().getId().equals(destino)) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public boolean modificarLatencia(String origen, String destino, int latencia) {
        Vertice o = buscar(origen);
        if (o == null) {
            return false;
        }
        Arista a = buscarArista(o, destino);
        if (a == null) {
            return false;
        }
        a.setLatencia(latencia);
        return true;
    }

    public boolean eliminarArista(String origen, String destino) {
        Vertice o = buscar(origen);
        if (o == null) {
            return false;
        }
        Arista actual = o.getPrimeraArista();
        Arista anterior = null;
        while (actual != null) {
            if (actual.getDestino().getId().equals(destino)) {
                if (anterior == null) {
                    o.setPrimeraArista(actual.getSiguiente());
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
        return false;
    }

    public boolean eliminarVertice(String id) {
        Vertice objetivo = buscar(id);
        if (objetivo == null) {
            return false;
        }
        Vertice actual = primero;
        while (actual != null) {
            eliminarArista(actual.getId(), id);
            actual = actual.getSiguiente();
        }
        if (primero == objetivo) {
            primero = objetivo.getSiguiente();
        } else {
            Vertice anterior = primero;
            while (anterior.getSiguiente() != objetivo) {
                anterior = anterior.getSiguiente();
            }
            anterior.setSiguiente(objetivo.getSiguiente());
        }
        cantidad--;
        return true;
    }

    public int grado(String id) {
        Vertice v = buscar(id);
        if (v == null) {
            return -1;
        }
        int salida = 0;
        Arista a = v.getPrimeraArista();
        while (a != null) {
            salida++;
            a = a.getSiguiente();
        }
        int entrada = 0;
        Vertice actual = primero;
        while (actual != null) {
            if (buscarArista(actual, id) != null) {
                entrada++;
            }
            actual = actual.getSiguiente();
        }
        return entrada + salida;
    }

    private void limpiarVisitados() {
        Vertice actual = primero;
        while (actual != null) {
            actual.setVisitado(false);
            actual = actual.getSiguiente();
        }
    }

    private int recorrerAnchura(Vertice inicio, StringBuilder orden) {
        limpiarVisitados();
        Cola cola = new Cola();
        inicio.setVisitado(true);
        cola.encolar(inicio);
        int visitados = 0;
        while (!cola.estaVacia()) {
            Vertice v = cola.desencolar();
            visitados++;
            if (orden.length() > 0) {
                orden.append(" -> ");
            }
            orden.append(v.getId());
            Arista a = v.getPrimeraArista();
            while (a != null) {
                Vertice d = a.getDestino();
                if (!d.isVisitado()) {
                    d.setVisitado(true);
                    cola.encolar(d);
                }
                a = a.getSiguiente();
            }
        }
        return visitados;
    }

    public String bfs(String id) {
        Vertice inicio = buscar(id);
        if (inicio == null) {
            return "El equipo " + id + " no existe";
        }
        StringBuilder orden = new StringBuilder();
        recorrerAnchura(inicio, orden);
        return orden.toString();
    }

    public boolean esConexo(String id) {
        Vertice inicio = buscar(id);
        if (inicio == null) {
            return false;
        }
        return recorrerAnchura(inicio, new StringBuilder()) == cantidad;
    }

    public String noAlcanzables(String id) {
        Vertice inicio = buscar(id);
        if (inicio == null) {
            return "";
        }
        recorrerAnchura(inicio, new StringBuilder());
        StringBuilder sb = new StringBuilder();
        Vertice actual = primero;
        while (actual != null) {
            if (!actual.isVisitado()) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(actual.getId());
            }
            actual = actual.getSiguiente();
        }
        return sb.toString();
    }

    public void mostrar() {
        Vertice actual = primero;
        while (actual != null) {
            StringBuilder linea = new StringBuilder(String.format("%-10s", actual.getId()));
            linea.append("|");
            Arista a = actual.getPrimeraArista();
            if (a == null) {
                linea.append(" (sin enlaces)");
            }
            while (a != null) {
                linea.append(" -> ").append(a.getDestino().getId()).append(" (").append(a.getLatencia()).append(" ms)");
                a = a.getSiguiente();
            }
            System.out.println(linea);
            actual = actual.getSiguiente();
        }
    }

    public int getCantidad() {
        return cantidad;
    }
}
