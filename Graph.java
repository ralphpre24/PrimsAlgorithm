import java.util.LinkedList;
// Class to represent a graph
   public class Graph {
        int vertices;
        LinkedList<Edge>[] adjacencyList;
        
        Graph(int vertices) {
            this.vertices = vertices;
            adjacencyList = new LinkedList[vertices];
            
            for (int i = 0; i < vertices; i++) {
                adjacencyList[i] = new LinkedList<>();
            }
        }
