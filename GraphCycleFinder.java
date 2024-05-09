
public class GraphCycleFinder {
    // Use a very large value to represent infinity (no direct path between nodes)
    static final int INF = Integer.MAX_VALUE;

    static void findMinimumCycles(int[][] graph) {
        int n = graph.length; // Number of vertices in the graph
        int[][] dist = new int[n][n]; // Distance matrix
        int[][] next = new int[n][n]; // Next matrix to store the successor node in path

        // Step 1: Initialize distance and next matrices
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = graph[i][j]; // Initial distance is the edge weight
                if (graph[i][j] != INF && i != j) {
                    next[i][j] = j; // Successor of i in the path to j is j itself
                } else {
                    next[i][j] = -1; // No successor if no direct edge
                }
            }
        }

        // Step 2: Run the Floyd-Warshall algorithm to find shortest paths
        for (int k = 0; k < n; k++) { // Consider each vertex as an intermediate vertex
            for (int i = 0; i < n; i++) { // For each source vertex
                for (int j = 0; j < n; j++) { // For each destination vertex
                    // If a new shorter path via vertex k is found, update dist[i][j]
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j]; // Update the distance
                        next[i][j] = next[i][k]; // Update the path to go via k
                    }
                }
            }
        }

        // Step 3: Detect and report the minimum cycle
        int minCycleWeight = INF; // Initialize minimum cycle weight to infinity
        for (int i = 0; i < n; i++) {
            // Check for cycles involving each node i (dist[i][i] would be negative if a negative cycle is reachable)
            if (dist[i][i] < minCycleWeight) {
                minCycleWeight = dist[i][i]; // Update the minimum cycle weight
            }
        }

        // Print the minimum cycle weight found
        System.out.println("Minimum cycle weight: " + minCycleWeight);
    }

    public static void main(String[] args) {
        // DEfining the graph
        int[][] graph = {
            {0, 3, INF, INF, 1},
            {INF, 0, 6, INF, 3},
            {1, INF, 0, INF, INF},
            {-4, INF, 5, 0, INF},
            {INF, INF, 2, 2, 0}
        };

        // Execute the cycle finding function
        findMinimumCycles(graph);
    }
}
