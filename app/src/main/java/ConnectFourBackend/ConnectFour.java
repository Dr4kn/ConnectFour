package ConnectFourBackend;

/**
 * ConnectFour playing board
 * also includes the logic
 */
public class ConnectFour implements ConnectFourInterface{
    private int winLength;
    private int width, height;
    private boolean alreadyWon = false;

    private final Board board;
    private Player playerNumber = Player.ONE;


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
        this.width = width;
        this.height = height;
        board = new Board(width, height, winLength);
        setBoardDimensions(width, height);
    }

    /**
     * @param width has to be positive sets width of the Board
     * @param height has to be positive sets height of the Board
     *               automatically initializes the new Board
     */
    public void setBoardDimensions(int width, int height) {
        this.width = width;
        this.height = height;
        board.setBoardDimensions(width, height);
    }

    /**
     * @param row starts from 0. the column height is determined automatically
     * @return the column position where the disc should be placed
     */
    public int move(int row) {
        if (row > width) {
            throw new IllegalArgumentException("The given row is to large for the board");
        }

        if (board.getPlayerAtPosition(row, height - 1) == Player.EMPTY && !alreadyWon) {

            if (playerNumber == Player.ONE) {
                playerNumber = Player.TWO;
            } else {
                playerNumber = Player.ONE;
            }

            for (int i = height - 1; i >= 0; i--) {
                if (board.getPlayerAtPosition(row, i) != Player.EMPTY) {
                    board.setPlayerAtPosition(row, i + 1, getCurrentPlayer());
                    return (i + 1);
                } else if (i == 0) {
                    board.setPlayerAtPosition(row, 0, getCurrentPlayer());
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
        Player player = getCurrentPlayer();
        alreadyWon = true;

        // TODO remove both for loops and only check the last move
        // has to check all around the last placed one
        for (int column = 0; column < height; column++) { // bottom to top because discs always start from the bottom
            for (int row = 0; row < width; row++) { // left to right
                if (board.getPlayerAtPosition(row, column) != player) { // when the disc isn't from the current player skip it
                    continue;
                }

                if (row + winLength - 1 < width) { // checks discs horizontal
                    for (int i = 1; true; i++) { // true == i < winLength
                        if (player != board.getPlayerAtPosition(row + i, column)) {

                            break;
                        } else if (i == winLength - 1) {
                            return true;
                        }
                    }
                }

                if (column + winLength - 1 < height) {
                    for (int i = 1; i < winLength; i++) { // checks discs vertical
                        if (player != board.getPlayerAtPosition(row, column + i)) {
                            break;
                        } else if (i == winLength - 1) {
                            return true;
                        }
                    }

                    if (row - (winLength - 1) >= 0) { // checks discs diagonal left
                        for (int i = 1; i < winLength; i++) {
                            if (player != board.getPlayerAtPosition(row - 1, column + i)) {
                                break;
                            } else if (i == winLength - 1) {
                                return true;
                            }
                        }
                    }

                    if (row + (winLength - 1) < width) { // checks discs diagonal right
                        for (int i = 1; i < winLength; i++) {
                            if (player != board.getPlayerAtPosition(row + i, column + i)) {
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

    public Player getCurrentPlayer() {
        return switch (playerNumber) {
            case ONE -> Player.TWO;
            case TWO -> Player.ONE;
            default -> Player.EMPTY;
        };
    }

    /**
     * @return board as 2d array with [row][height] filled with Player enums
     */
    public Player[][] getBoard() {
        return board.getBoard();
    }

    /**
     * @param winLength int the number of discs needed to win
     */
    public void setWinLength(int winLength) {
        if (1 >= winLength || winLength > height || winLength > width) {
            throw new IllegalArgumentException("board dimensions have to be at least as large as the win length");
        }
        this.winLength = winLength;
        board.setWinLength(winLength);
    }

    /**
     * @return the number of discs to have next to each other to win
     */
    public int getWinLength() {
        return this.winLength;
    }
}

