import java.util.*;

public class PrimsAlgorithm {
    
    // Class to represent a graph edge
    class Edge {
        int source;
        int destination;
        int weight;
        
        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
