package ConnectFourBackend;

public class Board {
    private Player[][] board;
    private int winLength, width, height;

    /**
     * @param width width of the board
     * @param height height of the board
     * @param winLength number of discs needed to win
     */
    protected Board(int width, int height, int winLength) {
        this.winLength = winLength;
        this.width = width;
        this.height = height;
        this.board = new Player[width][height];
    }

    /**
     * @param width has to be positive sets width of the Board
     * @param height has to be positive sets height of the Board
     *               automatically initializes the new Board
     */
    protected void setBoardDimensions(int width, int height) {
        if (width >= winLength && height >= winLength) {
            this.width = width;
            this.height = height;
            initializeBoard();
        } else {
            throw new IllegalArgumentException("board dimensions have to be at least as large as the win length");
        }
    }

    /**
     * creates a new matrix with the set width & height
     * the empty board gets filled with zeros
     */
    protected void initializeBoard() {
        this.board = new Player[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board[i][j] = Player.EMPTY;
            }
        }
    }

    /**
     * @param row int of board row. bottom to top
     * @param column int of board column. left to right
     * @return Player Enum at that position
     */
    protected Player getPlayerAtPosition(int row, int column) {
        return board[row][column];
    }

    /**
     * @param row int of board row. bottom to top
     * @param column int of board column. left to right
     * @param player Player enum to set at that position
     */
    protected void setPlayerAtPosition(int row, int column, Player player) {
        board[row][column] = player;
    }

    /**
     * @return the current board
     */
    protected Player[][] getBoard() {
        return board;
    }

    /**
     * @param winLength the number of discs you need to win
     */
    protected void setWinLength(int winLength) {
        this.winLength = winLength;
    }
}