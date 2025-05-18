import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        // Agregar aristas según la imagen
        grafo.agregarArista(1, 2, 1);
        grafo.agregarArista(1, 3, 2);
        grafo.agregarArista(2, 3, 1);
        grafo.agregarArista(2, 4, 3);
        grafo.agregarArista(2, 5, 2);
        grafo.agregarArista(3, 4, 2);
        grafo.agregarArista(3, 5, 2);
        grafo.agregarArista(4, 6, 3);
        grafo.agregarArista(4, 7, 5);
        grafo.agregarArista(6,5,1);
        grafo.agregarArista(5, 4, 3);
        grafo.agregarArista(6, 7, 1);
        grafo.agregarArista(6, 8, 2);
        grafo.agregarArista(8, 6, 2);
        grafo.agregarArista(7, 9, 1);
        grafo.agregarArista(8, 7, 3);
        grafo.agregarArista(8, 9, 5);

        JFrame frame = new JFrame("Grafo Dirigido con Peso");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.add(new GrafoPanel(grafo));
        frame.setVisible(true);

        String bfsResult = grafo.recorridoBFSstr(1);
        String dfsResult = grafo.recorridoDFSstr(1);
        System.out.println("Lista de adyacencia:");
        System.out.println(grafo);
        System.out.print("Recorrido BFS desde 1: " + bfsResult);
        System.out.print("\nRecorrido DFS desde 1: " + dfsResult);
        JOptionPane.showMessageDialog(frame,"Recorrido BFS desde 1: " + bfsResult + "\nRecorrido DFS desde 1: " + dfsResult, "Resultados", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("\nCosto mínimo de 1 a 9: " + grafo.dijkstra(1, 9));
        System.out.println("Costo máximo de 1 a 9: " + grafo.costoMaximo(1, 9));
        JOptionPane.showMessageDialog(frame, "Costo mínimo de 1 a 9: " + grafo.dijkstra(1, 9) + "\nCosto máximo de 1 a 9: " + grafo.costoMaximo(1, 9), "Resultados", JOptionPane.INFORMATION_MESSAGE);
        
    }
}
