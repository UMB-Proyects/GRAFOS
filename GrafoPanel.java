import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GrafoPanel extends JPanel {
    private Grafo grafo;
    private Map<Integer, Point> nodos; 

    public GrafoPanel(Grafo grafo) {
        this.grafo = grafo;
        nodos = new HashMap<>();

        // nodos fijos para cada nodo (según la imagen)
        nodos.put(1, new Point(50, 150));
        nodos.put(2, new Point(150, 50));
        nodos.put(3, new Point(150, 250));
        nodos.put(4, new Point(250, 150));
        nodos.put(5, new Point(350, 250));
        nodos.put(6, new Point(350, 50));
        nodos.put(7, new Point(450, 150));
        nodos.put(8, new Point(550, 250));
        nodos.put(9, new Point(650, 150));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (var entrada : grafo.getAdyacencia().entrySet()) {
            int origen = entrada.getKey();
            Point pOrigen = nodos.get(origen);

            for (Grafo.Arista arista : entrada.getValue()) {
                Point pDestino = nodos.get(arista.destino);
                g.setColor(Color.RED);
                g.drawLine(pOrigen.x, pOrigen.y, pDestino.x, pDestino.y);
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(arista.peso),
                             (pOrigen.x + pDestino.x) / 2,
                             (pOrigen.y + pDestino.y) / 2);
            }
        }

        // Dibuja los nodos
        for (var entry : nodos.entrySet()) {
            int idNodo = entry.getKey();
            Point p = entry.getValue();
            g.setColor(Color.BLUE);
            g.fillOval(p.x - 10, p.y - 10, 20, 20);
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(idNodo), p.x - 5, p.y + 5);
        }
    }
}
