package ConnectFourBackend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class ConnectFourTest {

    private final ConnectFour connectFour = new ConnectFour(7, 6);

    @BeforeEach
    public void setUp() {
        connectFour.initializeBoard();
    }

    @Test
    @DisplayName("Board gets initialized with zeroes")
    public void boardIsZero() {
        int[][] testBoard = connectFour.getBoard();

        for (int i : testBoard[0]) {
            for (int j : testBoard[1]) {
                assertEquals(0, testBoard[i][j]);
            }
        }
    }

    @Test
    @DisplayName("If the Board is smaller then 4 it should throw an exception")
    public void smallBoard() {
        assertThrows(IllegalArgumentException.class, () -> connectFour.setBoardDimensions(3,1));
        assertThrows(IllegalArgumentException.class, () -> connectFour.setBoardDimensions(-10,-8));
    }

    @Test
    @DisplayName("All possible valid moves")
    public void validMoves() {
        connectFour.move(3);
        int[][] testBoard = connectFour.getBoard();
        assertEquals(1, testBoard[3][0]);

        connectFour.move(3);
        testBoard = connectFour.getBoard();
        assertEquals(2, testBoard[3][1]);

        connectFour.move(5);
        testBoard = connectFour.getBoard();
        assertEquals(1, testBoard[5][0]);
    }

    @Test
    @DisplayName("Can not place two discs on top of each other")
    public void illegalMove() {
        for (int i = 0; i < 6; i++) {
            connectFour.move(3);
        }
        assertThrows(IllegalArgumentException.class, () -> connectFour.move(3));
    }

//    @Test
//    @DisplayName("horizontal win")
//    public void winHorizontal() {
//        for (int i = 0; i < 4; i++) {
//            board.move(i, 1);
//        }
//        assertTrue(board.hasWon(1));
//    }

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