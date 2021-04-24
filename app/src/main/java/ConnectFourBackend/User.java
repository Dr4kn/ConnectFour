package ConnectFourBackend;

public class User {
    int playerNumber;

    /**
     * @param playerNumber can be either one or two;
     */
    public User(int playerNumber) throws Exception{
        if (playerNumber > 2 || playerNumber < 1) {
            throw new Exception("Wrong Number either one or two");
        }
        this.playerNumber = playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    protected int[][] playerTurn(int[][] board, int[] move) {
        board[move[0]][move[1]] = playerNumber;

        return board;
    }
    public void dab(int number) {
        number += 1;
    }
}
