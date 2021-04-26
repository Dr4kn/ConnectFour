package ConnectFourGUI;
import processing.core.PApplet;

/**
 * The Connect Four board can create the playing field and place discs in the gui
 */
public class Board {
    private final PApplet pApplet;
    private final int boardWidth;
    private final int boardHeight;

    private final int discSize = 50;
    private final int puffer = discSize + 10;
    private final int width;
    private final int height;
    private final int boardPositionWidth;
    private final int boardPositionHeight;
    private Disc[][] discs;

    /**
     * @param pApplet the processing instances
     * @param resolutionWidth the resolution of the processing window. used to center the board
     * @param resolutionHeight the resolution of the processing window. used to center the board
     */
    public Board(PApplet pApplet, int resolutionWidth, int resolutionHeight) {
        this(pApplet, 7, 6, resolutionWidth, resolutionHeight);
    }

    /**
     * @param pApplet the processing instances
     * @param boardWidth default: 7 the number of columns the board should have
     * @param boardHeight default: 6 the number of rows the board should have
     * @param resolutionWidth the resolution of the processing window. used to center the board
     * @param resolutionHeight the resolution of the processing window. used to center the board
     */
    public Board(PApplet pApplet, int boardWidth, int boardHeight, int resolutionWidth, int resolutionHeight) {
        this.pApplet = pApplet;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;

        this.width = boardWidth * puffer + (puffer - discSize);
        this.height = boardHeight * puffer + (puffer - discSize);
        this.boardPositionWidth = (resolutionWidth - width) / 2;
        this.boardPositionHeight = resolutionHeight - height - 10;
    }

    /**
     * initializes the board with empty looking spaces for discs
     */
    public void initialize() {
        createEmptyBoard();
        createDiscSpaces();
    }

    /**
     * creates the board with rounded edges in a darker blue
     * scales with different rows
     */
    private void createEmptyBoard() {
        pApplet.fill(0, 50, 255);
        pApplet.rect(boardPositionWidth, boardPositionHeight, width, height, 7);
    }

    /**
     * puts discs with the same color as the background on top of the board
     * the board is then looking like a board you could look through
     */
    private void createDiscSpaces() {
        /*
        sets the empty discs at the top left corner of the board with equal distance
        (puffer - discSize) / 2 is necessary, because those are already applied when two discs are next to each other
        if it wouldn't be done the discs at the top and left border would look to near to it
        */
        int posY = boardPositionHeight + height - (int)(discSize / 2) - (puffer - discSize);
        int posX = boardPositionWidth + puffer / 2 + (puffer - discSize) / 2;
        int posXCopy = posX;
        int posYCopy = posY;
        discs = new Disc[boardWidth][boardHeight];

        for (int x = 0; x < boardWidth; x++) {
            posY = posYCopy;
            for (int y = 0; y < boardHeight; y++) {
                Disc disc = new Disc(pApplet, posX, posY, "black");
                discs[x][y] = disc;
                discs[x][y].draw();
                posY -= puffer;
            }
            posX += puffer;
        }
    }
}
