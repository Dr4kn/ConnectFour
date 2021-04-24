package ConnectFourBackend;

public class ConnectFour {
    private int[][] board;
    private int[] move = new int[2];

    public ConnectFour(int numberOfPlayers) throws Exception {
        this(numberOfPlayers, 7, 6);
    }

    public ConnectFour(int numberOfPlayers, int boardWidth, int boardHeight) throws Exception {
        this.board = new int[boardWidth][boardHeight];

        switch (numberOfPlayers) {
            case 0:
                // create 2 ai playing against each other
                break;
            case 1:
                // create 1 human 1 ai playing against each other
                break;
            case 2:
                // create 2 human playing against each other
                break;
            default:
                throw new Exception("ConnectFourBackend: There can only be 2 players");

        }
    }

    User user = new User(1);
    User player1 = new User(1);
//    user.turn(board, move);

}
