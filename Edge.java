  // Class to represent a graph edge
    public class Edge {
        int source;
        int destination;
        int weight;
        
        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
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
