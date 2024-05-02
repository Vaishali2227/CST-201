
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SortingAlgorithm {
    
    static int comparisons = 0;
    static int exchanges = 0;
    
    // Method to shuffle an array
    static void shuffleArray(int[] arr) {
        Random rnd = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }
    
    // Selection Sort: repeatedly finds the minimum element from the unsorted part and moves it to the beginning
    static void selectionSort(int[] arr) {
        comparisons = 0;
        exchanges = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
            exchanges++;
        }
    }
    
    // Bubble Sort: repeatedly steps through the list, compares adjacent elements and swaps them if they are in the wrong order
    static void bubbleSort(int[] arr) {
        comparisons = 0;
        exchanges = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    exchanges++;
                }
            }
        }
    }
    
    // Merge Sort: divides the array into two halves, recursively sorts them and then merges them
    static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m); // Sort the first half
            mergeSort(arr, m + 1, r); // Sort the second half
            merge(arr, l, m, r); // Merge the sorted halves
        }
    }
    
    // Helper method for merge sort to merge two sorted arrays
    static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1; // Size of the first subarray
        int n2 = r - m; // Size of the second subarray
        int[] L = new int[n1]; // Create temporary left subarray
        int[] R = new int[n2]; // Create temporary right subarray
        // Copy data to temporary arrays L[] and R[]
        for (int i = 0; i < n1; i++)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[m + 1 + j];
        // Merge the temporary arrays back into arr[l..r]
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            comparisons++;
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            exchanges++;
            k++;
        }
        // Copy the remaining elements of L[], if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        // Copy the remaining elements of R[], if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    
    // Quick Sort: selects a 'pivot' element and partitions the array into two sub-arrays around the pivot
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high); // Partitioning index
            quickSort(arr, low, pi - 1); // Recursively sort elements before partition
            quickSort(arr, pi + 1, high); // Recursively sort elements after partition
        }
    }
    
    // Helper method for quick sort to partition the array
    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Pivot element
        int i = low - 1; // Index of smaller element
        for (int j = low; j < high; j++) {
            comparisons++;
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                exchanges++;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        exchanges++;
        return i + 1;
    }
    
    // Custom sorting algorithm (betterSort): using Java's built-in sorting method
    static void betterSort(int[] arr) {
        comparisons = 0;
        exchanges = 0;
        Arrays.sort(arr);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        //get the size of the array 
        int n = scanner.nextInt();
        scanner.close();
        
        int[] originalArray = new int[n];
        
        // Initialize original array
        for (int i = 0; i < n; i++) {
            originalArray[i] = i + 1;
        }
        
        // Repeat for 100 times
        for (int i = 0; i < 100; i++) {
            int[] shuffledArray = Arrays.copyOf(originalArray, originalArray.length);
            //shuffle the array 
            shuffleArray(shuffledArray);
            
            // Selection Sort and print the comparisons and exchanges 
            int[] selectionSortedArray = Arrays.copyOf(shuffledArray, shuffledArray.length);
            selectionSort(selectionSortedArray);
            System.out.println("Selection Sort - Comparisons: " + comparisons + ", Exchanges: " + exchanges);
            
            // Bubble Sort and print the comparisons and exchanges 
            int[] bubbleSortedArray = Arrays.copyOf(shuffledArray, shuffledArray.length);
            bubbleSort(bubbleSortedArray);
            System.out.println("Bubble Sort - Comparisons: " + comparisons + ", Exchanges: " + exchanges);
            
            // Merge Sort and print the comparisons and exchanges 
            int[] mergeSortedArray = Arrays.copyOf(shuffledArray, shuffledArray.length);
            mergeSort(mergeSortedArray, 0, mergeSortedArray.length - 1);
            System.out.println("Merge Sort - Comparisons: " + comparisons + ", Exchanges: " + exchanges);
            
            // Quick Sort and print the comparisons and exchanges 
            int[] quickSortedArray = Arrays.copyOf(shuffledArray, shuffledArray.length);
            quickSort(quickSortedArray, 0, quickSortedArray.length - 1);
            System.out.println("Quick Sort - Comparisons: " + comparisons + ", Exchanges: " + exchanges);
            
            // Better Sort and print the comparisons and exchanges 
            int[] betterSortedArray = Arrays.copyOf(shuffledArray, shuffledArray.length);
            betterSort(betterSortedArray);
            System.out.println("Better Sort - Comparisons: " + comparisons + ", Exchanges: " + exchanges);
        }
    }
}
