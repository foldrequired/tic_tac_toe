package cli_projects.tic_tac_toe;

public class Board {
    private final int rowSize= 3;
    private final int colSize= 3;
    private final String DEFAULT_CELL_VALUE = " ";
    private final String TICK_CELL_VALUE = "V";

    private String[][] _board = new String[rowSize][colSize];

    public Board() {
        for (int i = 0; i < _board.length; i++) {
            for (int j = 0; j < _board[i].length; j++) {
                _board[i][j] = DEFAULT_CELL_VALUE;
            }
        }
    }

    public Board(String[][] other) {
        this();
        // get the given array length
        int n = other.length;

        for (int i = 0; i < _board.length; i++) {
            for (int j = 0; j < _board[i].length; j++) {
                /*
                    Check if param array length is larger than i + 1,
                    If it is larger -> check if array[i].length > j -> twoDemiArr[i][j] = array[i][j] value
                */
                if (n >= i + 1)
                    if (other[i].length > j)
                        _board[i][j] = other[i][j];
            }
        }
    }

    public Board(Board other) {
        this(other._board);
    }

    public void updateBoard(int row, int col, String shape) {
        _board[row][col] = shape;
    }

    public boolean isRowAndColValid(int row, int col) {
        // check out of array boundaries
        if (row < 0 || row > _board.length - 1 || col < 0 || col > _board.length - 1)
            return false;
        return _board[row][col].equals(" ");
    }

    /*
    public boolean isPlayerBoardWon(Board board) {
        for (int i = 0; i < boardVictoryPatterns.length - 1; i++) {
            if (boardVictoryPatterns[i].equals(board))
                return true;
        }
        return false;
    }
    */

    public String getCell(int row, int col) {
        // using validRowAndCol helper method to check if row and col are valid
        if (_board[row][col].equals(" ")) {
            return DEFAULT_CELL_VALUE;
        } else
            return _board[row][col];
    }

    public String toString() {
        StringBuilder boardTemplate = new StringBuilder();
        int k = 1;
        boardTemplate.append("R\\C\t").append("1\t").append(" 2\t").append("  3\n");
        for (int i = 0; i < _board.length; i++) {
            boardTemplate.append(k).append(" ");
            for (int j = 0; j < _board[i].length; j++) {
                boardTemplate.append("| ").append(_board[i][j]).append(" |");
            }
            if (i != _board.length - 1) {
                boardTemplate.append("\n");
                k++;
            }
        }
        return boardTemplate.toString();
    }
}
