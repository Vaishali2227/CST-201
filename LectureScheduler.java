package week7;

import java.util.Arrays;

public class LectureScheduler {
    private static final int V = 7; // Number of vertices (lectures)

    // Function to determine if it's safe to assign color to vertex
    private static boolean isSafe(int v, int[][] graph, int[] color, int c) {
        // Check if any adjacent vertex has the same color
        for (int i = 0; i < V; i++) {
            if (graph[v][i] == 1 && color[i] == c) {
                return false; // If conflict found, it's not safe
            }
        }
        return true; // If no conflict found, it's safe to assign the color
    }

    // Recursive function to solve the coloring problem
    private static boolean graphColoringUtil(int[][] graph, int m, int[] color, int v) {
        if (v == V) {
            return true; // If all lectures are colored, return true
        }

        for (int c = 1; c <= m; c++) {
            if (isSafe(v, graph, color, c)) {
                color[v] = c; // Assign the color to the current lecture

                // Recur for the next lecture
                if (graphColoringUtil(graph, m, color, v + 1)) {
                    return true; // If coloring successful, return true
                }

                color[v] = 0; // Backtrack: if coloring fails, reset the color
            }
        }
        return false; // If no color assignment works, return false
    }

    // Function to solve the coloring problem
    public static int graphColoring(int[][] graph, int maxColors) {
        int[] color = new int[V]; // Array to store color assignments for lectures
        Arrays.fill(color, 0); // Initialize all colors to 0 (no color)

        // Try different numbers of colors until a valid coloring is found
        for (int m = 1; m <= maxColors; m++) {
            if (graphColoringUtil(graph, m, color, 0)) {
                return m; // Return the minimum number of colors found
            }
        }
        return -1; // If no solution found within maxColors, return -1
    }

    // Function to calculate the number of unique conflicts
    public static int calculateUniqueConflicts(int[][] graph) {
        int conflicts = 0; // Initialize conflicts counter
        boolean[] visited = new boolean[V]; // Array to mark visited lectures

        // Iterate over the upper triangle of the matrix
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                // If there's a conflict between lectures i and j and j is not visited
                if (graph[i][j] == 1 && !visited[j]) {
                    conflicts++; // Increment conflicts counter
                }
            }
            visited[i] = true; // Mark lecture i as visited
        }

        return conflicts; // Return the total number of unique conflicts
    }

    public static void main(String[] args) {
        //matrix representing conflicts between lectures
        int[][] graph = {
            {0, 1, 1, 0, 1, 0, 0},
            {1, 0, 1, 1, 0, 1, 0},
            {1, 1, 0, 1, 0, 1, 1},
            {0, 1, 1, 0, 0, 1, 1},
            {1, 0, 0, 0, 0, 1, 1},
            {0, 1, 1, 1, 1, 0, 0},
            {0, 0, 1, 1, 1, 0, 0}
        };

        int maxColors = V; 

        // Find the minimum number of separate times (colors)
        int minColors = graphColoring(graph, maxColors);
        if (minColors != -1) {
            System.out.println("Minimum number of separate times for the lectures: " + minColors);
        } else {
            System.out.println("Solution does not exist within " + maxColors + " colors.");
        }

        // Calculate the minimum number of different students with conflicts
        int uniqueConflicts = calculateUniqueConflicts(graph);
        System.out.println("Minimum number of different students that have conflicts in lectures: " + uniqueConflicts);
    }
}
