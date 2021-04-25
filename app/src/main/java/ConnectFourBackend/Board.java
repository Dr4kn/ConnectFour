package ConnectFourBackend;

public class Board {
    private int[][] board;
    private int width;
    private int height;

    public Board(int width, int height) {
        setBoardDimensions(width, height);
    }

    /**
     * @param width has to be positive sets width of the Board
     * @param height has to be positive sets height of the Board
     *               automatically initializes the new Board
     */
    public void setBoardDimensions(int width, int height) {
        if (width >= 4 && height >= 4) {
            this.width = width;
            this.height = height;

            initializeBoard();
        }
        else {
            throw new IllegalArgumentException("board dimensions have to be at least 4");
        }

    }

    /**
     * creates a new matrix with the set width & height
     * the empty board gets filled with zeros
     */
    public void initializeBoard() {
        board = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = 0;
            }
        }
    }

    /**
     * @param column starts from 0 place in matrix where the move should occur
     * @param row starts from 0 place in matrix where the move should occur
     * @param playerNumber either one or two
     */
    public void move(int column, int row, int playerNumber) {
        if (board[column][row] == 0) {
            for (int i = column; i >= 0; i--) {
                if (board[i][row] != 0) {
                    board[i + 1][row] = playerNumber;
                }
                else if (i == 0) {
                    board[i][row] = playerNumber;
                }
            }

        }
        else {
            throw new IllegalArgumentException("move already made");
        }
    }

    /**
     * @param player 1 or 2 only that nubmer is used to check the board
     * @return true if player has won
     */
    public boolean hasWon(int player) {
        final int winLength = 4;

        for (int column = 0; column < height; column++) { // bottom to top because discs always start from the bottom
            for (int row = 0; row < width; row++) { // left to right
                if (board[column][row] != player) { // when the disc isn't from the current player skip it
                    continue;
                }

                if (row + winLength - 1 < width) { // checks discs to the right
                    for (int i = 1; true; i++) { // true == i < winLength
                        if (player != board[column][row + i]) {
                            break;
                        }
                        else if (i == winLength - 1) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public int[][] getBoard() {
        return board;
    }
}

