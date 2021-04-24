package ConnectFourBackend;

public class Board {
    private int[][] board;
    private final int width;
    private final int height;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;


        initializeBoard();
    }

    /**
     * creates a new matrix with the set width & height
     * the empty board gets filled with zeros
     */
    private void initializeBoard() {
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
    private void move(int column, int row, int playerNumber) {
        if (board[column][row] == 0) {
            board[column][row] = playerNumber;
        }
        else {
            throw new IllegalArgumentException("move already made");
        }
    }

    private boolean hasWon(int player) {
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
}

