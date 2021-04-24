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
        board = new int[width][height];

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                board[i][j] = 0;
            }
        }
    }

    /**
     * @param row starts from 0 place in matrix where the move should occur
     * @param column starts from 0 place in matrix where the move should occur
     * @param playerNumber either one or two
     */
    private void move(int row, int column, int playerNumber) {
        int boardNumber = board[row][column];
        if(boardNumber == 0) {
            board[row][column] = playerNumber;
        }
        else {
            throw new IllegalArgumentException("move already made");
        }
    }


}

