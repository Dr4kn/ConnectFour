package ConnectFourGUI;
import processing.core.PApplet;

public class Setup extends PApplet {
    private final int resolutionWidth;
    private final int resolutionHeight;
    private Board board;
    private boolean nextGame, settings = false;

    /**
     * @param resolutionWidth the resolution the processing windows should have (default 1080)
     * @param resolutionHeight the resolution the processing windows should have (default 720)
     */
    public Setup(int resolutionWidth, int resolutionHeight) {
        this.resolutionWidth = resolutionWidth;
        this.resolutionHeight = resolutionHeight;
    }

    /**
     * sets the resolution of the processing window
     */
    public void settings() {
        size(resolutionWidth, resolutionHeight);
    }

    /**
     * makes background black and sets the title of the window
     */
    public void setup() {
        surface.setTitle("Connect Four");
        board = new Board(this, 7, 6, resolutionWidth, resolutionHeight);
        gameSettings();
    }

    /**
     * draw method from processing that gets executed every frame
     */
    public void draw() {
        if (board.hasWon() && !nextGame) {
            displayWinner();
            nextGame();
        }
    }

    /**
     * displays text and color of the player who won
     */
    private void displayWinner() {
        textSize(50);
        if (board.getPlayerTurn() == ConnectFourBackend.Board.Player.ONE) {
            fill(102, 0, 0);
        } else {
            fill(153, 153, 0);
        }
        textAlign(CENTER);
        text(String.format("Player %d has won", board.getPlayerTurn() == ConnectFourBackend.Board.Player.ONE ? 1 : 2),
                (resolutionWidth >> 1), 80);
    }

    /**
     * prints a dialog to play again and turn on a variable to start the game
     */
    private void nextGame() {
        fill(0, 50, 255);
        textSize(35);
        textAlign(CENTER);
        text("Do you want to play again?", (resolutionWidth >> 1), 140);
        textSize(25);
        text("press enter or S for Settings ", (resolutionWidth >> 1), 180);
        nextGame = true;
    }

    private void gameSettings() {
        board.initializeBoard();
        fill(0, 50, 255);
        textSize(35);
        textAlign(CENTER);
        text("to in-/decrease the board size\npress the arrow keys", (resolutionWidth >> 1), 80);
        textSize(25);
        text("press enter to start a game", (resolutionWidth >> 1), 180);
        settings = true;
    }

    public void mouseClicked() {
        if (!settings) {
            board.placeDisc(mouseX, mouseY);
        }
    }

    public void keyPressed() {
        if (key == CODED && settings) {
            switch (keyCode) {
                case LEFT -> board.decreaseBoardRows();
                case RIGHT -> board.increaseBoardRows();
                case UP -> board.increaseBoardColumns();
                case DOWN -> board.decreaseBoardColumns();
            }
            gameSettings();
        } else {
            if ((key == ENTER || key == RETURN) && (nextGame || settings)) {
                nextGame = false;
                settings = false;
                board.restart();
            } else if (nextGame && (key == 's' || key == 'S')) {
                background(0);
                gameSettings();
            }
        }
    }
}