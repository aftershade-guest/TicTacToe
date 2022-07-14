import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class game {
    static int player = 1;
    static boolean GameFinished = false;
    static String[] board = {"_", "_", "_", "_", "_", "_", "_", "_", "_"};
    static int[][] winCases = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6},
            {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    static String Mark = "X";

    public static void main(String[] args) {
        printBoard();


        while (!GameFinished) {

            if (player % 2 != 0) {
                System.out.println("Player 1's turn");
                Mark = "X";
            } else {
                System.out.println("Player 2's turn");
                Mark = "O";
            }

            game_();

        }


    }

    static void game_() {
        String coord = null;
        coord = getInput();

        if (coord == null) {
            coord = getInput();
        }

        if (checkBoard(coord)) {
            board[Integer.parseInt(coord)] = Mark;
            printBoard();
            checkWin();
            player += 1;
        } else {
            System.out.println("That is already filled.");
        }
    }


    static void printBoard() {
        System.out.printf("%s | %s | %s \n", board[0], board[1], board[2]);
        System.out.printf("%s | %s | %s \n", board[3], board[4], board[5]);
        System.out.printf("%s | %s | %s \n", board[6], board[7], board[8]);
    }

    static String getInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter coordinates (0 to 8): ");
        String coord = scanner.nextLine().strip();

        if (!coord.isEmpty()) {

            if (coord.matches("[0-8]")) {
                return coord;
            } else {
                System.out.println("Coordinate should be from 0 to 8");
                return null;
            }

        } else {
            System.out.println("Please enter the coordinates");
            return null;
        }

    }

    static boolean checkBoard(String strCord) {
        int co_ = Integer.parseInt(strCord);

        if (board[co_] == "_") {
            return true;
        } else {
            return false;
        }

    }

    static void checkWin() {
        ArrayList<Integer> player1 = new ArrayList<>();
        ArrayList<Integer> player2 = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            if (board[i].equals("X")) {
                player1.add(i);
            } else if (board[i].equals("O")) {
                player2.add(i);
            }
        }

        if (Math.abs((player1.size() - player2.size())) > 1) {
            GameFinished = true;
            System.out.println("Impossible");
        } else {
            if (is_winner(player1)) {
                GameFinished = true;
                System.out.println("Player 1 wins");
            } else if (is_winner(player2)) {
                GameFinished = true;
                System.out.println("Player 2 wins");
            } else {
                boolean found = false;

                for (String i : board) {
                    if (i.equals("_")) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    GameFinished = false;
                } else {
                    GameFinished = true;
                    System.out.println("Draw");
                }

            }
        }

    }

    static boolean is_winner(ArrayList<Integer> playerList) {

        //Object[] player = playerList.toArray();
        boolean match = true;
        Pattern pat;
        Matcher mat;

        //System.out.println(playerList);

        for (int[] i : winCases) {
            String yy = Arrays.toString(i);
            pat = Pattern.compile(yy);
            mat = pat.matcher(String.valueOf(playerList));
            mat.reset();
            //System.out.print(Arrays.toString(i));
            //boolean matchF = true;

            for (int j = 0; j < 3; j++) {
                if (!mat.find()){
                    match = false;
                    break;
                }
            }

        }
        //System.out.println();

        return match;

    }

}
