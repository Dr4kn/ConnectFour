package ConnectFourBackend;

public interface ConnectFourInterface {
    void setBoardDimensions(int width, int height);

    int move(int row);

    boolean hasWon();

    Board.Player getCurrentPlayer();

    void setWinLength(int winLength);
}
