package ConnectFourBackend;

public interface ConnectFourInterface {
    void setBoardDimensions(int width, int height);

    void initializeBoard();

    int move(int row);

    boolean hasWon();

    int getPlayerTurn();

    void setWinLength(int winLength);
}
