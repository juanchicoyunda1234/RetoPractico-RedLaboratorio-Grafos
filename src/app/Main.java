package app;

import negocio.Grafo;

public class Main {
    private static final String[] EQUIPOS = {"Router", "Switch", "PC1", "PC2", "Impresora", "Servidor", "Proyector"};

    public static void main(String[] args) {
        Grafo red = new Grafo();
        parte1(red);
        parte2(red);
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

    private static void imprimirGrados(Grafo red) {
        for (String e : EQUIPOS) {
            System.out.println(String.format("grado(\"%s\")", e) + " ".repeat(14 - e.length()) + "= " + red.grado(e));
        }
    }

    private static void parte2(Grafo red) {
        titulo("PARTE 2: CONSULTAR");
        System.out.println("bfs(\"Router\"): " + red.bfs("Router"));
        System.out.println();
        imprimirGrados(red);
        System.out.println();
        System.out.println("modificarLatencia(Router -> Servidor, 2) -> " + red.modificarLatencia("Router", "Servidor", 2));
        System.out.println("eliminarArista(Servidor -> PC2)          -> " + red.eliminarArista("Servidor", "PC2"));
        System.out.println();
        red.mostrar();
        System.out.println();
        imprimirGrados(red);
    }
}
