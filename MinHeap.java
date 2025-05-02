   // Class to represent the min heap
    class MinHeap {
        int size;
        int capacity;
        int[] pos; // To store the position of vertex in heap array
        HeapNode[] heapArray;
        
        public MinHeap(int capacity) {
            this.capacity = capacity;
            pos = new int[capacity];
            heapArray = new HeapNode[capacity];
            size = 0;
        }
        
        public void swapNodes(int a, int b) {
            HeapNode temp = heapArray[a];
            heapArray[a] = heapArray[b];
            heapArray[b] = temp;
        }
        
        // Standard function to heapify at given index
        public void heapify(int index) {
            int smallest = index;
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            
            if (left < size && heapArray[left].key < heapArray[smallest].key) {
                smallest = left;
            }
            
            if (right < size && heapArray[right].key < heapArray[smallest].key) {
                smallest = right;
            }
            
            if (smallest != index) {
                // The nodes to be swapped in min heap
                HeapNode smallestNode = heapArray[smallest];
                HeapNode indexNode = heapArray[index];
                
                // Swap positions
                pos[smallestNode.vertex] = index;
                pos[indexNode.vertex] = smallest;
                
                // Swap nodes
                swapNodes(smallest, index);
                
                heapify(smallest);
            }
        }
        
        // Check if heap is empty
        public boolean isEmpty() {
            return size == 0;
        }
        
        // Extract minimum node from heap
        public HeapNode extractMin() {
            if (isEmpty()) {
                return null;
            }
            
            // Store the root node
            HeapNode root = heapArray[0];
            
            // Replace root node with last node
            HeapNode lastNode = heapArray[size - 1];
            heapArray[0] = lastNode;
            
            // Update position of last node
            pos[root.vertex] = size - 1;
            pos[lastNode.vertex] = 0;
            
            // Reduce heap size and heapify root
            size--;
            heapify(0);
            
            return root;
        }
        
        // Decrease key value of a given vertex
        public void decreaseKey(int vertex, int key) {
            // Get the index of vertex in heap array
            int i = pos[vertex];
            
            // Update the key value
            heapArray[i].key = key;
            
            // Travel up while the complete tree is not heapified
            while (i > 0 && heapArray[i].key < heapArray[(i - 1) / 2].key) {
                // Swap this node with its parent
                pos[heapArray[i].vertex] = (i - 1) / 2;
                pos[heapArray[(i - 1) / 2].vertex] = i;
                swapNodes(i, (i - 1) / 2);
                
                // Move to parent index
                i = (i - 1) / 2;
            }
        }
        
        // Check if a vertex is in min heap
        public boolean isInMinHeap(int vertex) {
            return pos[vertex] < size;
        }
    }
    
    // Function to print MST
    public void printMST(int[] parent, int[] key, int vertices) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < vertices; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + key[i]);
        }
    }
    
    // Function to construct and print MST using Prim's algorithm
    public void primMST(Graph graph) {
        int vertices = graph.vertices;
        int[] parent = new int[vertices]; // Array to store constructed MST
        int[] key = new int[vertices]; // Key values used to pick minimum weight edge
        
        // Initialize min heap for all vertices
        MinHeap minHeap = new MinHeap(vertices);
        
        // Initialize min heap with all vertices and infinite key values
        for (int v = 1; v < vertices; v++) {
            parent[v] = -1;
            key[v] = Integer.MAX_VALUE;
            minHeap.heapArray[v] = new HeapNode();
            minHeap.heapArray[v].vertex = v;
            minHeap.heapArray[v].key = key[v];
            minHeap.pos[v] = v;
        }
        
        // Make key value of 0th vertex as 0 so it's extracted first
        key[0] = 0;
        minHeap.heapArray[0] = new HeapNode();
        minHeap.heapArray[0].vertex = 0;
        minHeap.heapArray[0].key = key[0];
        minHeap.pos[0] = 0;
        
        minHeap.size = vertices;
        
        while (!minHeap.isEmpty()) {
            // Extract the vertex with minimum key value
            HeapNode extractedNode = minHeap.extractMin();
            int u = extractedNode.vertex;
            
            // Traverse through all adjacent vertices of u
            LinkedList<Edge> list = graph.adjacencyList[u];
            for (Edge edge : list) {
                int v = edge.destination;
                
                // If v is not yet included in MST and weight of u-v is less than key of v
                if (minHeap.isInMinHeap(v) && edge.weight < key[v]) {
                    key[v] = edge.weight;
                    parent[v] = u;
                    minHeap.decreaseKey(v, key[v]);
                }
            }
        }
        
        // Print the constructed MST
        printMST(parent, key, vertices);
    }
