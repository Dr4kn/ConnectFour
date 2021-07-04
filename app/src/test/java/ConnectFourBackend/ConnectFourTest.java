package ConnectFourBackend;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class ConnectFourTest {

    private final ConnectFour connectFour = new ConnectFour(7, 6);

    @BeforeEach
    void setUp() {
        connectFour.setBoardDimensions(7, 6);
    }

    @Test
    @DisplayName("The Constructor is able to set a win length")
    public void connectFour() {
        final ConnectFour connectFour = new ConnectFour(7, 6, 5);
        Assertions.assertEquals(5, connectFour.getWinLength());
    }

    @Test
    @DisplayName("The Player is an enum")
    public void playerIsEnum() {
        Assertions.assertEquals(Player.TWO, connectFour.getCurrentPlayer());
    }

    @Test
    @DisplayName("If the Board is smaller then 4 it should throw an exception")
    public void setBoardDimensions() {
        assertThrows(IllegalArgumentException.class, () -> connectFour.setBoardDimensions(3, 1));
        assertThrows(IllegalArgumentException.class, () -> connectFour.setBoardDimensions(-8, -5));
    }

    @Test
    @DisplayName("checks if the Board has the correct length")
    public void getBoard() {
        Player[][] player = connectFour.getBoard();
        Assertions.assertEquals(6, player[0].length);
    }

    @Test
    @DisplayName("It's possible to move to max height")
    public void moveToMaxHeight() {
        for (int i = 0; i < 6; i++) {
            connectFour.move(0);
        }
    }

    @Test
    @DisplayName("It's not possible to move to over max height")
    public void moveOverMaxHeight() {
        for (int i = 0; i < 6; i++) {
            connectFour.move(0);
        }
        assertThrows(IllegalArgumentException.class, () -> connectFour.move(0));
    }

    @Test
    @DisplayName("The complete Board can be used")
    public void moveToMaxWidth() {
        for (int i = 0; i < 7; i++) {
            connectFour.move(i);
        }
    }

    @Test
    @DisplayName("It's not possible to move over max width")
    public void moveOverMaxWidth() {
        for (int i = 0; i < 7; i++) {
            connectFour.move(i);
        }
        assertThrows(IllegalArgumentException.class, () -> connectFour.move(8));
    }

    @Test
    @DisplayName("You can win in Horizontal direction")
    public void hasWonHorizontal() {
        for (int i = 0; i < 4; i++) {
            connectFour.move(i);
            connectFour.move(i);
        }
        assertTrue(connectFour.hasWon());

    }

    @Test
    @DisplayName("You can win in Vertical Direction")
    public void hasWonVertical() {
        for (int i = 0; i < 4; i++) {
            connectFour.move(0);
            connectFour.move(1);
        }
        assertTrue(connectFour.hasWon());
    }

    @Test
    @DisplayName("You can win Diagonal top left to bottom right")
    public void hasWonDiagonalLeftTop() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                connectFour.move(i);
            }
        }
        assertTrue(connectFour.hasWon());
    }

    @Test
    @DisplayName("It is possible to win from top right to bottom left")
    public void hasWonDiagonalLeftBottom() {
        for (int i = 5; i > 0; i--) {
            for (int j = 0; j < 5; j++) {
                connectFour.move(i);
            }
        }
        assertTrue(connectFour.hasWon());
    }

    @Test
    @DisplayName("The Player that last made a move is returned")
    public void getCurrentPlayer() {
        connectFour.move(1);
        Assertions.assertEquals(Player.ONE, connectFour.getCurrentPlayer());
    }

    @Test
    @DisplayName("The Win Length can be changed")
    public void setWinLength() {
        connectFour.setWinLength(3);
        Assertions.assertEquals(3, connectFour.getWinLength());
    }

    @Test
    @DisplayName("The Win Length can't be made larger then either rows or columns")
    public void setWinLengthToBig() {
        assertThrows(IllegalArgumentException.class, () -> connectFour.setWinLength(10));
    }

    @Test
    @DisplayName("The Win Length can't be made larger then either rows or columns even when negative")
    public void setWinLengthToBigNegative() {
        assertThrows(IllegalArgumentException.class, () -> connectFour.setWinLength(-2));
    }
}