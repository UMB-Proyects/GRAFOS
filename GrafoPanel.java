import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
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
        nodos.put(4, new Point(350, 50));
        nodos.put(5, new Point(350, 250));
        nodos.put(6,  new Point(450, 150));
        nodos.put(7, new Point(550, 50));
        nodos.put(8, new Point(550, 250));
        nodos.put(9, new Point(650, 150));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (var entrada : grafo.getAdyacencia().entrySet()) {
            int origen = entrada.getKey();
            Point pOrigen = nodos.get(origen);

            for (Grafo.Arista arista : entrada.getValue()) {
                Point pDestino = nodos.get(arista.destino);
                g2.setColor(Color.RED);
                drawArrow(g2, pOrigen.x, pOrigen.y, pDestino.x, pDestino.y);
                g2.setColor(Color.BLACK);
                g2.drawString(String.valueOf(arista.peso),
                             (pOrigen.x + pDestino.x) / 2,
                             (pOrigen.y + pDestino.y) / 2);
            }
        }

        // Dibuja los nodos
        for (var entry : nodos.entrySet()) {
            int idNodo = entry.getKey();
            Point p = entry.getValue();
            g2.setColor(Color.BLUE);
            g2.fillOval(p.x - 10, p.y - 10, 20, 20);
            g2.setColor(Color.WHITE);
            g2.drawString(String.valueOf(idNodo), p.x - 5, p.y + 5);
        }
    }

    private void drawArrow(Graphics2D g2, int x1, int y1, int x2, int y2) {
        int ARR_SIZE = 5;
        int NODE_RADIUS = 10;
        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy) - NODE_RADIUS;

        // Guarda la transformación original
        AffineTransform at = g2.getTransform();

        // Mueve y rota el sistema de coordenadas
        g2.translate(x1, y1);
        g2.rotate(angle);

        // Dibuja la línea principal
        g2.drawLine(0, 0, len, 0);

        // Dibuja la cabeza de la flecha
        g2.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
                       new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);

        // Restaura la transformación original
        g2.setTransform(at);
    }
}
