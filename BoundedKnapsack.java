public class BoundedKnapsack {

    // This method implements the bounded knapsack dynamic programming algorithm
    public static int boundedKnapsack(int[] weights, int[] values, int[] quantities, int capacity) {
        int n = weights.length; // Number of different item types

        // Arrays to hold the expanded item list considering the quantities
        int[] realWeights = new int[100]; // Ensure this array is large enough to hold all items
        int[] realValues = new int[100];
        int index = 0; // This will track the total number of items including quantities

        // Populate the realWeights and realValues arrays based on the quantities of each item
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < quantities[i]; j++) {
                realWeights[index] = weights[i];
                realValues[index] = values[i];
                index++;
            }
        }

        // DP array where dp[i][w] represents the max value with the first i items and max weight w
        int[][] dp = new int[index + 1][capacity + 1];

        // Build the DP table
        for (int i = 1; i <= index; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (realWeights[i - 1] <= w) {
                    // Include the current item if it improves the value
                    dp[i][w] = Math.max(dp[i - 1][w], realValues[i - 1] + dp[i - 1][w - realWeights[i - 1]]);
                } else {
                    // Otherwise, do not include the current item
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[index][capacity]; // The maximum value achievable within the given capacity
    }

    public static void main(String[] args) {
        int[] weights = {20, 30, 40, 60, 70, 90};
        int[] values = {70, 80, 90, 110, 120, 200};
        int[] quantities = {1, 2, 1, 3, 1, 2};
        int capacity = 280;

        System.out.println("Maximum value achievable with quantities: " + boundedKnapsack(weights, values, quantities, capacity));
    }
}
