package cli_projects.tic_tac_toe;

public class Player {
    private String _name;
    private String _shape;
    private Board _board;

    public Player(String name, String shape, Board board) {
        _name = name;
        _shape = shape;
        _board = new Board(board);
    }

    public Player(Player player) {
        _name = player._name;
        _shape = player._shape;
        _board = player.getBoard();
    }

    public void setPlayer(Player player) {
        _name = player.getName();
        _shape = player.getShape();
        _board = player.getBoard();
    }

    public String getName() {
        return _name;
    }

    public String getShape() {
        return _shape;
    }

    public Board getBoard() {
        return _board;
    }

    public boolean isEqual(Player other) {
        return _name.equals(other._name) && _shape.equals(other._shape);
    }

    public String toString() {
        return _name;
    }
}
