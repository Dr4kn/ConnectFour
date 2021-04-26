package ConnectFourBackend;

import java.util.ArrayList;
import java.util.List;

public class ConnectFour {
    private boolean moveMade = false;
    private int playerTurn = 1;
    Board board;


    public ConnectFour(int numberOfPlayers) throws Exception {
        this(numberOfPlayers, 7, 6);
    }

    public ConnectFour(int numberOfPlayers, int boardWidth, int boardHeight) throws Exception {
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

    public boolean makeMove(int row) {
        boolean hasWon = board.move(row, playerTurn);
        if (playerTurn == 1) {
            playerTurn = 2;
        }
        else {
          playerTurn = 1;
        }
        return hasWon;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public int[][] getBoard() {
        return board.getBoard();
    }
}
