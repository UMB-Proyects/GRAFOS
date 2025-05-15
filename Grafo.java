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

    public void recorridoBFS(int inicio) {
        Set<Integer> visitados = new HashSet<>();
        Queue<Integer> cola = new LinkedList<>();
        cola.add(inicio);

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

    public void recorridoDFS(int inicio) {// usa una cola para recorrer el grafo y marca como visitados los nodos 
        Set<Integer> visitados = new HashSet<>();
        dfs(inicio, visitados);
    }

    private void dfs(int actual, Set<Integer> visitados) {// recorre el grafo de forma recursiva
        // si el nodo ya fue visitado no lo vuelve a visitar como lo explica el if 
        if (visitados.contains(actual)) return;

        System.out.print(actual + " ");
        visitados.add(actual);

        if (adyacencia.containsKey(actual)) {
            for (Arista arista : adyacencia.get(actual)) {
                dfs(arista.destino, visitados);
            }
        }
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
            return destino + "(" + peso + ")";
        }
    }
}
