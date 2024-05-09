
public class DynamicProgramming {
	 public static int knapsackDP(int[] weights, int[] values, int capacity) {
	        int n = weights.length; // Total number of items
	        // Create a 2D array to store the maximum value that can be attained with different weights
	        int[][] dp = new int[n + 1][capacity + 1];

	        // Build the DP table in bottom up manner
	        for (int i = 1; i <= n; i++) { // Iterate over each item
	            for (int w = 0; w <= capacity; w++) { // Iterate over each possible capacity up to the maximum
	                // If the weight of the current item is less than or equal to the current capacity
	                if (weights[i - 1] <= w) {
	                    // Include the item and see if it gives a better solution than not including it
	                    dp[i][w] = Math.max(dp[i - 1][w], values[i - 1] + dp[i - 1][w - weights[i - 1]]);
	                } else {
	                    // If the item is too heavy to include, use the best solution without this item
	                    dp[i][w] = dp[i - 1][w];
	                }
	            }
	        }
	        return dp[n][capacity]; // The bottom right cell of the matrix will have the answer
	    }

	    public static void main(String[] args) {
	        // Weights and values of the items
	        int[] weights = {20, 30, 40, 60, 70, 90};
	        int[] values = {70, 80, 90, 110, 120, 200};
	        int capacity = 280; // Maximum weight that the knapsack can hold

	        // Output the maximum value that can be achieved within the given capacity
	        System.out.println("Maximum value achievable: " + knapsackDP(weights, values, capacity));
	    }
	

}
