package ConnectFourGUI;
import processing.core.PApplet;

public class Setup extends PApplet {
    private final int resolutionWidth;
    private final int resolutionHeight;
    private Board board;
    private boolean clearBackground = false;
    private boolean nextGame = false;

    /**
     * sets the revolution for the processing windows with the default values (1080 * 720)
     */
    public Setup() {
        this(1080, 720);
    }

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
        noSmooth();
        size(resolutionWidth, resolutionHeight);
    }

    /**
     * makes background black and sets the title of the window
     */
    public void setup() {
        surface.setTitle("Connect Four");
        board = new Board(this, 7, 6, resolutionWidth, resolutionHeight);

        background(0);
        noLoop();
        board.initialize();
    }

    /**
     * initializes the Board
     */
    public void draw() {
        board.draw();
        if (board.hasWon()) {
            displayWinner();
            nextGame();
        }
        if (clearBackground) {
            clearBackground = false;
            background(0);
            board.restart();
        }

    }

    private void displayWinner() {
        textSize(50);
        if (board.getPlayerTurn() == 1) {
            fill(102, 0, 0);
        } else {
            fill(153, 153, 0);
        }
        textAlign(CENTER);
        text(String.format("Player %d has won", board.getPlayerTurn()), (int)(resolutionWidth / 2), 80);
    }

    private void nextGame() {
        fill(0, 50, 255);
        textSize(35);
        textAlign(CENTER);
        text("Do you want to play again?", (int)(resolutionWidth / 2), 140);
        textSize(25);
        text("(press enter)", (int)(resolutionWidth / 2), 180);
        nextGame = true;
    }

    public void mouseClicked() {
        board.placeDisc(mouseX, mouseY);
    }

    public void keyPressed() {
        if (nextGame) {
            if (key == ENTER || key == RETURN) {
                nextGame = false;
                clearBackground = true;
                redraw();
            }
        }
    }
}