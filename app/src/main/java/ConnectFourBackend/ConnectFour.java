package ConnectFourBackend;

/**
 * ConnectFour playing board
 * also includes the logic
 */
public class ConnectFour implements ConnectFourInterface{
    private int winLength;
    private int width, height;

    private final Board board;
    private Board.Player playerNumber = Board.Player.ONE;
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
        if (board.getPlayerAtPosition(row, height - 1) == Board.Player.EMPTY && !alreadyWon) {

            if (playerNumber == Board.Player.ONE) {
                playerNumber = Board.Player.TWO;
            } else {
                playerNumber = Board.Player.ONE;
            }

            for (int i = height - 1; i >= 0; i--) {
                if (board.getPlayerAtPosition(row, i) != Board.Player.EMPTY) {
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
        Board.Player player = getCurrentPlayer();
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

    public Board.Player getCurrentPlayer() {
        return switch (playerNumber) {
            case ONE -> Board.Player.TWO;
            case TWO -> Board.Player.ONE;
            default -> Board.Player.EMPTY;
        };
    }

    /**
     * @return board as 2d array with [row][height] filled with Player enums
     */
    protected Board.Player[][] getBoard() {
        return board.getBoard();
    }

    /**
     * @param winLength int the number of discs needed to win
     */
    public void setWinLength(int winLength) {
        this.winLength = winLength;
        board.setWinLength(winLength);
    }
}

