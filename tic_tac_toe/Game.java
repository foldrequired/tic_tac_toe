package cli_projects.tic_tac_toe;

import java.util.Scanner;

public class Game {

    // Array of Boards that are valid Victory patterns
    private Board[] boardVictoryPatterns = new Board[] {
            new Board(new String[][] {
                    {"V","V","V"},
                    {" "," "," "},
                    {" "," "," "}
            }),
            new Board(new String[][] {
                    {" "," "," "},
                    {"V","V","V"},
                    {" "," "," "}
            }),
            new Board(new String[][] {
                    {" "," "," "},
                    {" "," "," "},
                    {"V","V","V"}
            }),
            new Board(new String[][] {
                    {"V"," "," "},
                    {"V"," "," "},
                    {"V"," "," "}
            }),
            new Board(new String[][] {
                    {" ","V"," "},
                    {" ","V"," "},
                    {" ","V"," "}
            }),
            new Board(new String[][] {
                    {" "," ","V"},
                    {" "," ","V"},
                    {" "," ","V"}
            }),
            new Board(new String[][] {
                    {"V"," "," "},
                    {" ","V"," "},
                    {" "," ","V"}
            }),
            new Board(new String[][] {
                    {" "," ","V"},
                    {" ","V"," "},
                    {"V"," "," "}
            }),
    };

    Board board;
    Player playerSwitch, player1, player2;
    private boolean gameOver = false;
    private String winnerName = "default";
    Scanner scanner = new Scanner(System.in);

    public Game() {
        board = new Board();
        player1 = new Player("1", "X", new Board());
        player2 = new Player("2", "O", new Board());
        // Set first player
        playerSwitch = new Player(player1);

        // Start Game Message
        System.out.println("Welcome to Tic Tac Toe CLI Game - Made by Rotem Bar-Sela\n");
    }

    public void StartGame() {
        int rowInput, colInput, countTurns;
        countTurns = 1;

        while (!gameOver) {
            System.out.println(board.toString() + "\n");
            // Check if Row and Col are valid
            while (true) {
                System.out.print("Row(1/2/3): ");
                rowInput = scanner.nextInt() - 1; // set input to rowInput - 1 because array start from 0
                System.out.print("Col(1/2/3): ");
                colInput = scanner.nextInt() - 1; // set input to colInput - 1 because array start from 0
                System.out.println();
                if (isRowAndColValid(rowInput, colInput)) {
                    board.updateBoard(rowInput, colInput, playerSwitch.getShape());
                    playerSwitch.getBoard().updateBoard(rowInput,colInput,"V");
                    break;
                }
            }

            // Check if turn 4 ended && check if Player won
            if (countTurns > 4 && checkIfPlayerWon()) {
                winnerName = playerSwitch.getName();
                gameOver = true;
            }
            // Switch players turn
            else {
                if (playerSwitch.isEqual(player1))
                    playerSwitch.setPlayer(player2);
                else
                    playerSwitch.setPlayer(player1);
            }
            // increase turns counter
            countTurns++;
        }
        System.out.println(board.toString() + "\n");
        System.out.println("Player" + winnerName + " is the WINNER!");
        System.out.println("[n]New Game, [q]Quit");
        String input;
        do {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("n")) {
                gameOver = false;
                StartGame();
            }
            else if (input.equalsIgnoreCase("q")) {
                break;
            }
            System.out.println("Please write 'n' or 'q'");
        }
        while (true);
        System.out.println("Thank you for playing Tic Tac Toe, hope to see you soon!");
    }

    // Check if Player won
    private boolean checkIfPlayerWon() {
        int countThreeVs;
        for (int i = 0; i < boardVictoryPatterns.length - 1; i++) {
            countThreeVs = 0;
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if(boardVictoryPatterns[i].getCell(j,k).equals("V") && playerSwitch.getBoard().getCell(j,k).equals("V")) {
                        countThreeVs++;
                    }
                    if (countThreeVs == 3)
                        return true;
                }
            }
        }
        return false;
    }

    // Check if row and col is already in use
    private boolean isRowAndColValid(int row, int col) {
        if (!board.isRowAndColValid(row,col)) {
            System.out.println("WARNING: [" + (row + 1) + "," + (col + 1) + "] is already in use or is out of bounds, " +
                    "Please write a valid location between (1/2/3)\n");
            return false;
        }
        return true;
    }
}
