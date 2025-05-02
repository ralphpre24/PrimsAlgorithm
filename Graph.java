  // Class to represent a graph
    class Graph {
        int vertices;
        LinkedList<Edge>[] adjacencyList;
        
        Graph(int vertices) {
            this.vertices = vertices;
            adjacencyList = new LinkedList[vertices];
            
            for (int i = 0; i < vertices; i++) {
                adjacencyList[i] = new LinkedList<>();
            }
        }
        
        public void addEdge(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            adjacencyList[source].addFirst(edge);
            
            // Since graph is undirected, add reverse edge also
            edge = new Edge(destination, source, weight);
            adjacencyList[destination].addFirst(edge);
        }
    }
