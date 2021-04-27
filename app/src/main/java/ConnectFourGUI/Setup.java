package ConnectFourGUI;
import processing.core.PApplet;

public class Setup extends PApplet {
    private final int resolutionWidth;
    private final int resolutionHeight;
    private final Board board;

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
        this.board = new Board(this, resolutionWidth, resolutionHeight);
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
        background(0);
        noLoop();
    }

    /**
     * initializes the Board
     */
    // TODO have the board draw the array of discs in draw
    public void draw() {
        board.initialize();
    }

    public void mouseClicked() {
        board.placeDisc(mouseX, mouseY);
    }
}