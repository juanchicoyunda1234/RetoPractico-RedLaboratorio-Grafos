package app;

import negocio.Grafo;

public class Main {
    private static final String[] EQUIPOS = {"Router", "Switch", "PC1", "PC2", "Impresora", "Servidor", "Proyector"};

    public static void main(String[] args) {
        Grafo red = new Grafo();
        parte1(red);
    }

    private static void titulo(String texto) {
        System.out.println();
        System.out.println("==================================================");
        System.out.println(" " + texto);
        System.out.println("==================================================");
    }

    private static void parte1(Grafo red) {
        titulo("PARTE 1: CONSTRUIR");
        red.agregarVertice("Router");
        red.agregarVertice("Switch");
        red.agregarVertice("PC1");
        red.agregarVertice("PC2");
        red.agregarVertice("Impresora");
        red.agregarVertice("Servidor");
        red.agregarArista("Router", "Switch", 1);
        red.agregarArista("Router", "Servidor", 4);
        red.agregarArista("Switch", "PC1", 2);
        red.agregarArista("Switch", "PC2", 2);
        red.agregarArista("PC2", "Impresora", 3);
        red.agregarArista("Servidor", "PC2", 5);
        red.mostrar();
        System.out.println();
        System.out.println("agregarVertice(\"Router\") otra vez      -> " + red.agregarVertice("Router"));
        System.out.println("agregarArista(PC1 -> Proyector, 3)     -> " + red.agregarArista("PC1", "Proyector", 3));
    }
}
