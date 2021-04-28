package ConnectFourBackend;

import java.util.ArrayList;
import java.util.List;

public class ConnectFour {
    private boolean moveMade = false;
    private int playerTurn = 2;
    Board board;


    public ConnectFour(int numberOfPlayers) throws Exception {
        this(numberOfPlayers, 7, 6);
    }

    public ConnectFour(int numberOfPlayers, int boardWidth, int boardHeight) {
        board = new Board(boardWidth, boardHeight, 4);
//        switch (numberOfPlayers) {
//            case 0:
//                // create 2 ai playing against each other
//                break;
//            case 1:
//                // create 1 human 1 ai playing against each other
//                break;
//            case 2:
//
//                break;
//            default:
//                throw new IllegalArgumentException("There can only be two human players");
//        }
    }

    /**
     * @param row 0 to boardSize - 1
     * @return the column where the move should be made
     */
    public int makeMove(int row) {
        if (playerTurn == 2) {
            playerTurn = 1;
        }
        else {
          playerTurn = 2;
        }
        return board.move(row, playerTurn);
    }

    /**
     * @return boolean if the player that set the last token has won
     */
    public boolean hasWon() {
        return board.hasWon(playerTurn);
    }

    /**
     * @return 1 or 2 the number of the last player that placed a disc
     */
    public int getPlayerTurn() {
        return playerTurn;
    }

    /**
     * @return current state of the board
     */
    public int[][] getBoard() {
        return board.getBoard();
    }
}
