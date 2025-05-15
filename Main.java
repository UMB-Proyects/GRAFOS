import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        // Agregar aristas según la imagen
        grafo.agregarArista(1, 2, 1);
        grafo.agregarArista(1, 3, 1);
        grafo.agregarArista(2, 3, 1);
        grafo.agregarArista(2, 4, 3);
        grafo.agregarArista(3, 4, 2);
        grafo.agregarArista(3, 5, 3);
        grafo.agregarArista(4, 6, 5);
        grafo.agregarArista(5, 6, 3);
        grafo.agregarArista(6, 7, 1);
        grafo.agregarArista(6, 8, 2);
        grafo.agregarArista(7, 9, 1);
        grafo.agregarArista(8, 7, 3);
        grafo.agregarArista(8, 9, 5);

        JFrame frame = new JFrame("Grafo Dirigido con Peso");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.add(new GrafoPanel(grafo));
        frame.setVisible(true);

        System.out.print("Recorrido BFS desde 1: ");
        grafo.recorridoBFS(1);
        System.out.print("\nRecorrido DFS desde 1: ");
        grafo.recorridoDFS(1);
    }
}
