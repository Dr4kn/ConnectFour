package ConnectFourBackend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board = new Board(7, 6);

    @BeforeEach
    public void setUp() {
        board.initializeBoard();
    }

    @Test
    @DisplayName("Board gets initialized with zeroes")
    public void boardIsZero() {
        int[][] testBoard = board.getBoard();

        for (int i : testBoard[0]) {
            for (int j : testBoard[1]) {
                assertEquals(0, testBoard[i][j]);
            }
        }
    }

    @Test
    @DisplayName("If the Board is smaller then 4 it should throw an exception")
    public void smallBoard() {
        assertThrows(IllegalArgumentException.class, () -> board.setBoardDimensions(3,1));
        assertThrows(IllegalArgumentException.class, () -> board.setBoardDimensions(-10,-8));
    }

    @Test
    @DisplayName("All possible valid moves")
    public void validMoves() {
        board.move(3, 1);
        int[][] testBoard = board.getBoard();
        assertEquals(1, testBoard[3][0]);

        board.move(3, 2);
        testBoard = board.getBoard();
        assertEquals(2, testBoard[3][1]);

        board.move(5, 1);
        testBoard = board.getBoard();
        assertEquals(1, testBoard[5][0]);
    }

    @Test
    @DisplayName("Can not place two discs on top of each other")
    public void illegalMove() {
        for (int i = 0; i < 6; i++) {
            board.move(3, 1);
        }
        assertThrows(IllegalArgumentException.class, () -> board.move(3, 2));
    }

    @Test
    @DisplayName("horizontal win")
    public void winHorizontal() {
        for (int i = 0; i < 3; i++) {
            board.move(i, 1);
        }
        assertTrue(board.move(3, 1));
    }

//    @Test
//    @DisplayName("vertical win")
//    public void winVertical() {
//        for (int i = 0; i < 4; i++) {
//            board.move(i, 0, 1);
//        }
//        assertTrue(board.hasWon(1));
//    }

    // diagonal tests are missing for now
}