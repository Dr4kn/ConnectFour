package ConnectFourBackend;

public class Board {
    private int[][] board;
    private final int width;
    private final int height;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        board = new int[width][height];
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                board[i][j] = 0;
            }
        }
    }

    private void move(int row, int column, int playerNumber) {
        int boardNumber = board[row][column];
        if(boardNumber != 0) {
            board[row][column] = playerNumber;
        }
        else {
            throw new IllegalArgumentException("move already made");
        }
    }
}

