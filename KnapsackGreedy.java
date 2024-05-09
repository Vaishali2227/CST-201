import java.util.Arrays;
import java.util.Comparator;

public class KnapsackGreedy{

    // Define an Item class with properties for weight, value, and cost (value per unit weight)
    static class Item {
        int weight;
        int value;
        Double cost;

        // Constructor to initialize an item
        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
            this.cost = (double) value / weight; // Calculate cost as value per weight unit
        }
    }

    // Method to perform the greedy knapsack algorithm
    public static int greedyKnapsack(Item[] items, int capacity) {
        // Sort items by their cost in descending order
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o2.cost.compareTo(o1.cost);
            }
        });

        int totalValue = 0; // Initialize total value collected in the knapsack

        // Iterate through the sorted items array
        for (Item item : items) {
            if (capacity == 0) break; // If the knapsack is full, exit the loop

            // Determine the actual weight to take (either the full item or just the remaining capacity)
            int curWeight = Math.min(item.weight, capacity);

            // Increase the total value by the value of the amount of item added
            totalValue += (curWeight * item.cost);

            // Decrease the remaining capacity of the knapsack
            capacity -= curWeight;
        }

        return totalValue; // Return the total value accumulated
    }

    public static void main(String[] args) {
        // Define an array of items with their weights and values
        Item[] items = {
            new Item(20, 70),
            new Item(30, 80),
            new Item(40, 90),
            new Item(60, 110),
            new Item(70, 120),
            new Item(90, 200)
        };
        int capacity = 280; // Maximum capacity of the knapsack

        // Calculate the maximum value achievable with the greedy approach and print it
        System.out.println("Maximum value achievable by greedy approach: " + greedyKnapsack(items, capacity));
    }
}
