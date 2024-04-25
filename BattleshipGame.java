
import java.util.*;


public class BattleshipGame {

    private char[][] playerBoard; // The player's board
    private char[][] computerBoard; // The computer's board
    private Random random; // Random number generator to place the ships 

    // Constructor to initialize the game
    public BattleshipGame() {
        playerBoard = new char[10][10]; //board of 10 rows and 10 columns 
        computerBoard = new char[10][10]; //board of 10 rows and 10 columns 
        random = new Random();

        initializeBoards(); // Initialize both player and computer boards
        placeShips(playerBoard); // Place ships on player's board
        placeShips(computerBoard); // Place ships on computer's board
    }

    // Method to initialize both player and computer boards with empty cells
    private void initializeBoards() {
        for (int i = 0; i < 10; i++) {
            Arrays.fill(playerBoard[i], '*');
            Arrays.fill(computerBoard[i],'*');
        }
    }

    // Method to place ships on the board
    private void placeShips(char[][] board) {
        placeShip(board, 2); // Place Destroyer (2 cells)
        placeShip(board, 3); // Place Submarine (3 cells)
        placeShip(board, 3); // Place Cruiser (3 cells)
    }

    // Method to place a ship on the board
    private void placeShip(char[][] board, int size) {
        boolean placed = false;
        while (!placed) {
            int row = random.nextInt(10);//choose a random row between o and 9
            int col = random.nextInt(10);//choose a random column between 0 and 9
            boolean horizontal = random.nextBoolean();

            // Check if the ship can be placed at that random position
            if (canPlaceShip(board, row, col, size, horizontal)) {
                placeShipOnBoard(board, row, col, size, horizontal); // Place the ship
                placed = true; // Set placed boolean to true , if the ship cannot be placed then we will choose randomly other credentials.
            }
        }
    }

    // Method to check if a ship can be placed at the given position on the board
    private boolean canPlaceShip(char[][] board, int row, int col, int size, boolean horizontal) {
        if (horizontal && col + size > 10) return false; // Check if ship exceeds board boundaries horizontally
        if (!horizontal && row + size > 10) return false; // Check if ship exceeds board boundaries vertically

        // Check if cells are already occupied by another ship
        for (int i = 0; i < size; i++) {
            if (horizontal && board[row][col + i] != '*') return false;
            if (!horizontal && board[row + i][col] != '*') return false;
        }
        return true; // Return true if ship can be placed at the position
    }

    // Method to place a ship on the board
    private void placeShipOnBoard(char[][] board, int row, int col, int size, boolean horizontal) {
        for (int i = 0; i < size; i++) {
            if (horizontal) {
                board[row][col + i] = 'S'; // Place ship horizontally
            } else {
                board[row + i][col] = 'S'; // Place ship vertically
            }
        }
    }

    // Method to start and manage the game
    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean playerTurn = true; // Boolean to track player's turn
        boolean gameOver = false; // Boolean to track game over

        // Loop play until the game is not over 
        while (!gameOver) {
            if (playerTurn) {
                System.out.println("Your turn:");
                printBoard(computerBoard); // Print computer's board (hidden)
                System.out.print("Enter row : ");
                int row = scanner.nextInt();
                System.out.print("Enter column : ");
                int col = scanner.nextInt();

                // Check if player's move is valid
                if (isValidMove(computerBoard, row, col)) {
                    if (computerBoard[row][col] == 'S') {
                        System.out.println("Hit!");
                        computerBoard[row][col] = 'H'; //Hit
                        if (isGameOver(computerBoard)) {
                            System.out.println("You win!");
                            gameOver = true;
                        }
                    } else {
                        System.out.println("Miss!");
                        computerBoard[row][col] = 'M';//Miss
                        playerTurn = false; // Switch to computer's turn
                    }
                } else {
                    System.out.println("Invalid move ");
                    continue;
                }
            } else {
                System.out.println("Computer's turn:");
                int row = random.nextInt(10);
                int col = random.nextInt(10);

                while (!isValidMove(playerBoard, row, col)) {
                    row = random.nextInt(10);
                    col = random.nextInt(10);
                }

                if (playerBoard[row][col] == 'S') {
                    System.out.println("Computer hit at (" + row + ", " + col + ")!");
                    playerBoard[row][col] = 'H';
                    if (isGameOver(playerBoard)) {
                        System.out.println("Computer wins!");
                        gameOver = true;
                    }
                } else {
                    System.out.println("Computer missed!");
                    playerBoard[row][col] = 'M';
                    playerTurn = true; // Switch to player's turn
                }
            }
        }
        scanner.close();
    }

    // Method to check if a move is valid
    private boolean isValidMove(char[][] board, int row, int col) {
        return row >= 0 && row < 10 && col >= 0 && col < 10 && (board[row][col] == '*' || board[row][col] == 'S');
    }

    // Method to check if the game is over
    private boolean isGameOver(char[][] board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] == 'S') {
                    return false;
                }
            }
        }
        return true;
    }

    // Method to print the board
    private void printBoard(char[][] board) {
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Main method to start the game
    public static void main(String[] args) {
        BattleshipGame game = new BattleshipGame();
        game.play();
    }
}


