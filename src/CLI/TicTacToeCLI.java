package CLI;
import java.util.Scanner;
public class TicTacToeCLI {
    // ini main method
    public static void main(String[] args) {
        pilihMode();
    } // ini akhir dari main method

    // ini method milih mode
    public static void pilihMode() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("| Select Mode :        |");
            System.out.println("| (1) Play with Friend |");
            System.out.println("| (2) Play with Bot    |");
            System.out.println("| (3) Exit             |");
            System.out.print("Your choice : ");
            int mode = input.nextInt();

            if (mode == 1) {
                playWithFriend();
            } else if (mode == 2) {
                playWithBot();
            } else if (mode == 3) {
                System.out.println("Exiting the game. Goodbye!");
                System.exit(0);
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
        
    }
    // akhir method milih mode

    // ini method klo main ama temen
    public static void playWithFriend() {
        String uk = papan();
        if (uk == null) {
            return;
        }
        String[] sel = uk.split("x");
        int rows = Integer.parseInt(sel[0].trim());
        int columns = Integer.parseInt(sel[1].trim());
        int boards = rows * columns;
        String[][] board = new String[rows][columns];

        // Inisialisasi papan
        int angka = 1;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                board[row][col] = String.valueOf(angka++);
            }
        }

        // Menampilkan papan
        displayBoard(board);

        boolean player1 = true; // Player 1 bermain pertama
        while (true) {
            System.out.println((player1 ? "X" : "O") + "'s Turn");
            
            Scanner input = new Scanner(System.in);
            System.out.print("Masukkan angka sel: ");
            int angkaSel = input.nextInt();

            // Validasi input
            if (angkaSel < 1 || angkaSel > boards) {
                System.out.println("Input invalid. Coba lagi.");
                continue;
            }

            boolean validMove = markBoard(board, angkaSel, player1 ? "X" : "O");
            if (!validMove) {
                System.out.println("Sel sudah terisi. Coba lagi.");
                continue;
            }

            displayBoard(board);

            // Cek kemenangan
            String winner = checkWinner(board);
            if (!winner.equals(" ")) {
                System.out.println(winner + " menang!");
                break;
            }

            // Cek seri
            if (boardIsFull(board)) {
                System.out.println("Seri!");
                break;
            }

            player1 = !player1; // Ganti giliran
        }
    }

    // Menandai papan
    public static boolean markBoard(String[][] board, int angkaSel, String simbol) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col].equals(String.valueOf(angkaSel))) {
                    board[row][col] = simbol;
                    return true;
                }
            }
        }
        return false;
    }

    // Menampilkan papan
    public static void displayBoard(String[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                System.out.printf("%-3s", board[row][col]);
                if (col < board[row].length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row < board.length - 1) {
                for (int i = 0; i < board[row].length; i++) {
                    System.out.print("---");
                    if (i < board[row].length - 1) {
                        System.out.print("+");
                    }
                }
                System.out.println();
            }
        }
    }

    // Mengecek pemenang
    public static String checkWinner(String[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        // Cek baris
        for (int i = 0; i < rows; i++) {
            boolean inARow = true;
            String value = board[i][0];
            if (value.equals("X") || value.equals("O")) {
                for (int j = 1; j < cols; j++) {
                    if (!board[i][j].equals(value)) {
                        inARow = false;
                        break;
                    }
                }
                if (inARow) return value;
            }
        }

        // Cek kolom
        for (int j = 0; j < cols; j++) {
            boolean inACol = true;
            String value = board[0][j];
            if (value.equals("X") || value.equals("O")) {
                for (int i = 1; i < rows; i++) {
                    if (!board[i][j].equals(value)) {
                        inACol = false;
                        break;
                    }
                }
                if (inACol) return value;
            }
        }

        // Cek diagonal 1
        boolean inADiag1 = true;
        String value1 = board[0][0];
        if (value1.equals("X") || value1.equals("O")) {
            for (int i = 1; i < rows; i++) {
                if (!board[i][i].equals(value1)) {
                    inADiag1 = false;
                    break;
                }
            }
            if (inADiag1) return value1;
        }

        // Cek diagonal 2
        boolean inADiag2 = true;
        String value2 = board[0][cols - 1];
        if (value2.equals("X") || value2.equals("O")) {
            for (int i = 1; i < rows; i++) {
                if (!board[i][cols - 1 - i].equals(value2)) {
                    inADiag2 = false;
                    break;
                }
            }
            if (inADiag2) return value2;
        }

        return " "; // Tidak ada pemenang
    }

    // Mengecek apakah papan penuh
    public static boolean boardIsFull(String[][] board) {
        for (String[] row : board) {
            for (String cell : row) {
                if (!cell.equals("X") && !cell.equals("O")) {
                    return false;
                }
            }
        }
        return true;
    }

    // ini method main ama bot
    public static void playWithBot() {
        String uk = papan();
        if (uk == null) {
            return;
        }
        String[] sel = uk.split("x");
        int rows = Integer.parseInt(sel[0].trim());
        int columns = Integer.parseInt(sel[1].trim());
        int boards = rows * columns;
        String[][] board = new String[rows][columns];

        // Inisialisasi papan
        int angka = 1;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                board[row][col] = String.valueOf(angka++);
            }
        }

        // Menampilkan papan
        displayBoard(board);

        boolean player1 = true; // Player 1 bermain pertama
        while (true) {
            if(player1){
            System.out.println("X's Turn");
            
            Scanner input = new Scanner(System.in);
            System.out.print("Masukkan angka sel: ");
            int angkaSel = input.nextInt();
            // Validasi input
            if (angkaSel < 1 || angkaSel > boards) {
                System.out.println("Input invalid. Coba lagi.");
                continue;
            }

            boolean validMove = markBoard(board, angkaSel, player1 ? "X" : "O");
            if (!validMove) {
                System.out.println("Sel sudah terisi. Coba lagi.");
                continue;
            }

            displayBoard(board);

            // Cek kemenangan
            String winner = checkWinner(board);
            if (!winner.equals(" ")) {
                System.out.println(winner + " menang!");
                break;
            }

            // Cek seri
            if (boardIsFull(board)) {
                System.out.println("Seri!");
                break;
            }

            player1 = !player1; // Ganti giliran
        }
            else {
                System.out.println("O's Turn");
                int angkaSel =(int)(Math.random()*boards)+1;
                    if (angkaSel < 1 || angkaSel > boards) {
                    System.out.println("Input invalid. Coba lagi.");
                    continue;
                    }

                boolean validMove = markBoard(board, angkaSel, player1 ? "X" : "O");
                if (!validMove) {
                    System.out.println("Sel sudah terisi. Coba lagi.");
                    continue;
                }

                displayBoard(board);

                // Cek kemenangan
                String winner = checkWinner(board);
                if (!winner.equals(" ")) {
                    System.out.println(winner + " menang!");
                    break;
                }

                // Cek seri
                if (boardIsFull(board)) {
                    System.out.println("Seri!");
                    break;
                }

                player1 = !player1; // Ganti giliran
            }
        }
        }
    

    // ini method untuk memilih ukuran papan
    public static String papan() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("| Select Size :        |");
            System.out.println("| (1) 3 x 3            |");
            System.out.println("| (2) 4 x 4            |");
            System.out.println("| (3) 5 x 5            |");
            System.out.println("| (4) Back             |");
            System.out.print("Your choice : ");
            int size = input.nextInt();

            if (size == 1) return "3x3";
            if (size == 2) return "4x4";
            if (size == 3) return "5x5";
            if (size == 4) return null;
            else System.out.println("Invalid input. Please try again.");
        }
    }
}