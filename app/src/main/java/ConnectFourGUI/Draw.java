package ConnectFourGUI;
import processing.core.PApplet;

public class Draw extends PApplet {
    private final int resolutionWidth;
    private final int resolutionHeight;

    /**
     * sets the revolution for the processing windows with the default values (1080 * 720)
     */
    public Draw() {
        this(1080, 720);
    }

    /**
     * @param resolutionWidth the resolution the processing windows should have (default 1080)
     * @param resolutionHeight the resolution the processing windows should have (default 720)
     */
    public Draw(int resolutionWidth, int resolutionHeight) {
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
        background(0);
        noLoop();
    }

    /**
     * initializes the Board
     */
    public void draw() {
        Board board = new Board(this, resolutionWidth, resolutionHeight);
        board.initialize();
    }
}