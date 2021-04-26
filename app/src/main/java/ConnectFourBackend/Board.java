package ConnectFourBackend;

/**
 * ConnectFour playing board
 * also includes the logic
 */
public class Board {
    private final int winLength;
    private int[][] board;
    private int width;
    private int height;

    /**
     * @param width int generates the width of the array
     * @param height int generates the width of the array
     */
    public Board(int width, int height) {
        this(width, height, 4);
    }

    /**
     * @param width int generates the width of the array
     * @param height int generates the width of the array
     * @param winLength 4 = default. the number of discs you need to win.
     *                  if not needed use the other constructor
     */
    public Board(int width, int height, int winLength) {
        this.winLength = winLength;
        setBoardDimensions(width, height);
    }

    /**
     * @param width has to be positive sets width of the Board
     * @param height has to be positive sets height of the Board
     *               automatically initializes the new Board
     */
    public void setBoardDimensions(int width, int height) {
        if (width >= winLength && height >= winLength) {
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
        board = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board[i][j] = 0;
            }
        }
    }

    /**
     * @param row starts from 0 place the column height is determined automatically
     * @param playerNumber either one or two
     */
    public void move(int row, int playerNumber) {
        if (board[row][height - 1] == 0) {
            for (int i = height - 1; i >= 0; i--) {
                if (board[row][i] != 0) {
                    board[row][i + 1] = playerNumber;
                } else if (i == 0) {
                    board[row][0] = playerNumber;
                }
            }
        }
        else {
            throw new IllegalArgumentException("row full");
        }
    }

    /**
     * @param player 1 or 2 only that number is used to check the board
     * @return true if player has won
     */
    public boolean hasWon(int player) {
        for (int column = 0; column < height; column++) { // bottom to top because discs always start from the bottom
            for (int row = 0; row < width; row++) { // left to right
                if (board[row][column] != player) { // when the disc isn't from the current player skip it
                    continue;
                }

                if (row + winLength - 1 < width) { // checks discs to the right
                    for (int i = 1; true; i++) { // true == i < winLength
                        if (player != board[row + i][column]) {
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

    /**
     * @return board as 2d array with [row][height]
     */
    public int[][] getBoard() {
        return board;
    }
}

