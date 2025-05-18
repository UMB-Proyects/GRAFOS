import java.util.*;

public class Grafo {
    private Map<Integer, List<Arista>> adyacencia;  // Mapa o estructura de datos para almacenar la lista de adyacencia
    // Cada clave es un nodo y el valor es una lista de aristas que representan los nodos adyacentes


    public Grafo() {
        adyacencia = new HashMap<>();
    }

    public void agregarArista(int origen, int destino, int peso) {  // agregamos la arista con su origen y destino , al gua que la agregalos la arraylis
        adyacencia.computeIfAbsent(origen, k -> new ArrayList<>()).add(new Arista(destino, peso));
    }

    public Map<Integer, List<Arista>> getAdyacencia() {// nos srive para ver o acceder al grafo desde afuera
        return adyacencia;
    }

    public void recorridoBFS(int inicio) {// explora los grafos por niveles, visitando vecinos primero  antes del nodo actual , utilizamos un queue 
        
        Set<Integer> visitados = new HashSet<>();
        Queue<Integer> cola = new LinkedList<>();
        cola.add(inicio);

// agrega el nodo a la cola , while cola no empty , sacamos el nodo actual y lo visitamos y luego a los nodos vecinos o adyacentes 
        while (!cola.isEmpty()) {
            int actual = cola.poll();
            if (!visitados.contains(actual)) {
                System.out.print(actual + " ");
                visitados.add(actual);
                if (adyacencia.containsKey(actual)) {
                    for (Arista arista : adyacencia.get(actual)) {
                        cola.add(arista.destino);
                    }
                }
                
            }
            
        }
        
    }
    
    public String recorridoBFSstr(int inicio) {
        Set<Integer> visitados = new HashSet<>();
        Queue<Integer> cola = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        cola.add(inicio);

        while (!cola.isEmpty()) {
            int actual = cola.poll();
            if (!visitados.contains(actual)) {
                sb.append(actual).append(" ");
                visitados.add(actual);
                if (adyacencia.containsKey(actual)) {
                    for (Arista arista : adyacencia.get(actual)) {
                        cola.add(arista.destino);
                    }
                }
            }
        }
        return sb.toString().trim();
    }

    public void recorridoDFS(int inicio) {// usa una cola para recorrer el grafo y marca como visitados los nodos 
        Set<Integer> visitados = new HashSet<>();
        dfs(inicio, visitados);
    }

    private void dfs(int actual, Set<Integer> visitados) {// recorre el grafo de forma profunda, visitando todo hasta que no avance mas luego se regresa 
        // y visita los nodos que no ha visitado, usamos la recursion en la linea dfs(inicio, visitados);
        if (visitados.contains(actual)) return;

        System.out.print(actual + " ");
        visitados.add(actual);

        if (adyacencia.containsKey(actual)) {
            for (Arista arista : adyacencia.get(actual)) {
                dfs(arista.destino, visitados);
            }
        }
    }
    public String recorridoDFSstr(int inicio) {
        Set<Integer> visitados = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        dfsStr(inicio, visitados, sb);
        return sb.toString().trim();
    }

    private void dfsStr(int actual, Set<Integer> visitados, StringBuilder sb) {
        if (visitados.contains(actual)) return;
        sb.append(actual).append(" ");
        visitados.add(actual);
        if (adyacencia.containsKey(actual)) {
            for (Arista arista : adyacencia.get(actual)) {
                dfsStr(arista.destino, visitados, sb);
            }
        }
    }

    public int dijkstra(int origen, int destino) {
        Map<Integer, Integer> dist = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (Integer nodo : adyacencia.keySet()) {
            dist.put(nodo, Integer.MAX_VALUE);
        }
        dist.put(origen, 0);
        pq.add(new int[]{origen, 0});

        while (!pq.isEmpty()) {
            int[] actual = pq.poll();
            int nodo = actual[0];
            int costo = actual[1];

            if (nodo == destino) return costo;

            if (adyacencia.containsKey(nodo)) {
                for (Arista arista : adyacencia.get(nodo)) {
                    int nuevoCosto = costo + arista.peso;
                    if (nuevoCosto < dist.getOrDefault(arista.destino, Integer.MAX_VALUE)) {
                        dist.put(arista.destino, nuevoCosto);
                        pq.add(new int[]{arista.destino, nuevoCosto});
                    }
                }
            }
        }
        return -1; // No hay camino
    }

    public int costoMaximo(int origen, int destino) {
        Set<Integer> visitados = new HashSet<>();
        return dfsMax(origen, destino, visitados, 0);
    }

    private int dfsMax(int actual, int destino, Set<Integer> visitados, int costoActual) {
        if (actual == destino) return costoActual;
        visitados.add(actual);
        int max = -1;
        if (adyacencia.containsKey(actual)) {
            for (Arista arista : adyacencia.get(actual)) {
                if (!visitados.contains(arista.destino)) {
                    int costo = dfsMax(arista.destino, destino, visitados, costoActual + arista.peso);
                    if (costo > max) max = costo;
                }
            }
        }
        visitados.remove(actual);
        return max;
    }

    public static class Arista { // con esta clase creamos la arista que contiene el destino y el peso y de igual forma la mostramos en texto
        // con el to string
        int destino;
        int peso;

        public Arista(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }

        @Override
        public String toString() {
            return "-> " + destino + " (" + peso + ")";
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var entry : adyacencia.entrySet()) {
            sb.append(entry.getKey()).append(": ");
            for (Arista arista : entry.getValue()) {
                sb.append("-> ").append(arista.destino).append(" (").append(arista.peso).append(") ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
