package ConnectFourBackend;

/**
 * ConnectFour playing board
 * also includes the logic
 */
public class ConnectFour implements ConnectFourInterface{
    private int winLength, width, height;
    private int[][] board;
    private int playerNumber = 1;
    private boolean alreadyWon = false;

    /**
     * @param width int generates the width of the array
     * @param height int generates the width of the array
     */
    public ConnectFour(int width, int height) {
        this(width, height, 4);
    }

    /**
     * @param width int generates the width of the array
     * @param height int generates the width of the array
     * @param winLength 4 = default. the number of discs you need to win.
     *                  if not needed use the other constructor
     */
    public ConnectFour(int width, int height, int winLength) {
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
            throw new IllegalArgumentException("board dimensions have to be at least as large as the win length");
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
     * @param row starts from 0. the column height is determined automatically
     * @return the column position where the disc should be placed
     */
    public int move(int row) {
        if (board[row][height - 1] == 0 && !alreadyWon) {
            if (playerNumber == 1) {
                playerNumber = 2;
            } else {
                playerNumber = 1;
            }

            for (int i = height - 1; i >= 0; i--) {
                if (board[row][i] != 0) {
                    board[row][i + 1] = getPlayerTurn();
                    return (i + 1);
                } else if (i == 0) {
                    board[row][0] = getPlayerTurn();
                }
            }
        } else {
            throw new IllegalArgumentException("row full");
        }
        return (0);
    }

    /**
     * @return true if player has won
     */
    public boolean hasWon() {
        int player = getPlayerTurn();
        alreadyWon = true;

        // TODO remove both for loops and only check the last move
        // has to check all around the last placed one
        for (int column = 0; column < height; column++) { // bottom to top because discs always start from the bottom
            for (int row = 0; row < width; row++) { // left to right
                if (board[row][column] != player) { // when the disc isn't from the current player skip it
                    continue;
                }

                if (row + winLength - 1 < width) { // checks discs horizontal
                    for (int i = 1; true; i++) { // true == i < winLength
                        if (player != board[row + i][column]) {
                            break;
                        } else if (i == winLength - 1) {
                            return true;
                        }
                    }
                }

                if (column + winLength - 1 < height) {
                    for (int i = 1; i < winLength; i++) { // checks discs vertical
                        if (player != board[row][column + i]) {
                            break;
                        } else if (i == winLength - 1) {
                            return true;
                        }
                    }

                    if (row - (winLength - 1) >= 0) { // checks discs diagonal left
                        for (int i = 1; i < winLength; i++) {
                            if (player != board[row - i][column + i]) {
                                break;
                            } else if (i == winLength - 1) {
                                return true;
                            }
                        }
                    }

                    if (row + (winLength - 1) < width) { // checks discs diagonal right
                        for (int i = 1; i < winLength; i++) {
                            if (player != board[row + i][column + i]) {
                                break;
                            } else if (i == winLength - 1) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        alreadyWon = false;
        return false;
    }

    public int getPlayerTurn() {
        return switch (playerNumber) {
            case 1 -> 2;
            case 2 -> 1;
            default -> 0;
        };
    }

    /**
     * @return board as 2d array with [row][height]
     */
    protected int[][] getBoard() {
        return board;
    }

    /**
     * @param winLength int the number of discs needed to win
     */
    public void setWinLength(int winLength) {
        this.winLength = winLength;
    }
}

