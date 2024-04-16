import java.util.Scanner;

public class TowerOfHanoiAlgorithm {
	// get the number of moves for each disk 
    public static int hanoiMoves(int n, int i) {
    	//check if i is between n and 1 
        if (i > n || i < 1)
            return -1;  // Invalid input
     
        else {
            return (int) Math.pow(2, n - i);}
        
        //for example for 3 disks we get 4 for disk 1 , 2 for disk 2 and 1 for disk 3 
        // for 4 disks we get 8 for disk 1 , 4 for disk 2 , 2 for disk 3 and 1 for disk 4 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // ask the user to enter the number of disks 
        System.out.print("Enter the total number of disks (n): ");
        int n = scanner.nextInt();
        // ask the user to enter the size of the disk 
        System.out.print("Enter the size of the disk (i): ");
        int i = scanner.nextInt();
        scanner.close();

        int moves = hanoiMoves(n, i);
        if (moves == -1)
            System.out.println("Invalid input! i should be between 1 and n.");
        else
            System.out.println("Number of moves made by the disk " + i + " is equal " + moves);
    }
}


    