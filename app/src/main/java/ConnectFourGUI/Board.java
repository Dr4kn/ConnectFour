package ConnectFourGUI;
import ConnectFourBackend.ConnectFour;
import processing.core.PApplet;

/**
 * The Connect Four board can create the playing field and place discs in the gui
 */
public class Board {
    private final PApplet pApplet;
    private final int boardRows, boardColumns, boardWidth, boardHeight, boardPositionWidth, boardPositionHeight;
    private final int discSize = 50;
    private final int puffer = discSize + 10;
    private final ConnectFour connectFour;

    /**
     * @param pApplet the processing instances
     * @param boardRows default: 7 the number of columns the board should have
     * @param boardColumns default: 6 the number of rows the board should have
     * @param resolutionWidth the resolution of the processing window. used to center the board
     * @param resolutionHeight the resolution of the processing window. used to center the board
     */
    protected Board(PApplet pApplet, int boardRows, int boardColumns, int resolutionWidth, int resolutionHeight) {
        this.pApplet = pApplet;
        this.boardRows = boardRows;
        this.boardColumns = boardColumns;

        this.boardWidth = boardRows * puffer + (puffer - discSize);
        this.boardHeight = boardColumns * puffer + (puffer - discSize);
        this.boardPositionWidth = (resolutionWidth - boardWidth) / 2;
        this.boardPositionHeight = resolutionHeight - boardHeight - 10;

        connectFour = new ConnectFour(boardRows, boardColumns);
    }

    /**
     * initializes the board with empty looking spaces for discs
     */
    protected void initializeBoard() {
        pApplet.background(0);
        pApplet.fill(0, 50, 255);
        pApplet.rect(boardPositionWidth, boardPositionHeight, boardWidth, boardHeight, 7);
        for (int x = 0; x < boardRows; x++) {
            for (int y = 0; y < boardColumns; y++) {
                colorDiscs(x, y, Disc.Color.BLACK);
            }
        }
    }

    /**
     * @param mouseX processing mouse position X axes
     * @param mouseY processing mouse position X axes
     */
    protected void placeDisc(int mouseX, int mouseY) {
        // determines if the mouse is inside the board
        if (mouseX >= boardPositionWidth && mouseX <= boardPositionWidth + boardWidth) {
            if (mouseY >= boardPositionHeight && mouseY <= boardPositionHeight + boardHeight) {
                // determines the column if it the mouse didn't click over the board
                int inBounds = (mouseX - boardPositionWidth - ((puffer - discSize) / 2)) / puffer;

                if (inBounds < boardRows) {
                    final int column = connectFour.move(inBounds);
                    colorDiscs(inBounds, column, determineColor());
                }
            }
        }
    }

    protected void colorDiscs(int row, int column, Disc.Color color) {
        /*
        sets the empty discs at the top left corner of the board with equal distance
        (puffer - discSize) / 2 is necessary, because those are already applied when two discs are next to each other
        if it wouldn't be done the discs at the top and left border would look to near to it
        */
        int posX = boardPositionWidth + puffer / 2 + (puffer - discSize) / 2;
        int posY = boardPositionHeight + boardHeight - (discSize / 2) - (puffer - discSize);

        posX = posX + puffer * row;
        posY = posY - puffer * column;

        new Disc(pApplet, posX, posY, color).draw();
    }

    /**
     * @return Enum of the color corresponding to the player
     */
    private Disc.Color determineColor() {
        if (connectFour.getCurrentPlayer() == ConnectFourBackend.Board.Player.ONE) {
            return Disc.Color.RED;
        } else {
            return Disc.Color.YELLOW;
        }
    }

    protected ConnectFourBackend.Board.Player getPlayerTurn() {
        return connectFour.getCurrentPlayer();
    }

    protected boolean hasWon() {
        return connectFour.hasWon();
    }

    protected void restart() {
        connectFour.setBoardDimensions(boardWidth, boardHeight);
        initializeBoard();
    }
}
