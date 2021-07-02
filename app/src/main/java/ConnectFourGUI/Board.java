package ConnectFourGUI;
import ConnectFourBackend.ConnectFour;
import processing.core.PApplet;

/**
 * The Connect Four board can create the playing field and place discs in the gui
 */
public class Board {
    private final PApplet pApplet;
    private int boardRows, boardColumns, boardWidth, boardHeight, boardPositionWidth, boardPositionHeight;
    private final int resolutionWidth, resolutionHeight;
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
        this.resolutionWidth = resolutionWidth;
        this.resolutionHeight = resolutionHeight;

        initializeBoard();
        connectFour = new ConnectFour(boardRows, boardColumns);
    }

    /**
     * initializes the board with empty looking spaces for discs
     */
    protected void initializeBoard() {
        this.boardWidth = boardRows * puffer + (puffer - discSize);
        this.boardHeight = boardColumns * puffer + (puffer - discSize);
        this.boardPositionWidth = (resolutionWidth - boardWidth) / 2;
        this.boardPositionHeight = resolutionHeight - boardHeight - 10;

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
                    colorDiscs(inBounds, connectFour.move(inBounds), determineColor());
                }
            }
        }
    }

    /**
     * @param row the row the disc should be placed
     * @param column the column the disc should be displaced
     * @param color Red, Yellow or Black found more could be added in Disc.Color
     */
    protected void colorDiscs(int row, int column, Disc.Color color) {
        /*
        sets the empty discs at the top left corner of the board with equal distance
        (puffer - discSize) / 2 is necessary, because those are already applied when two discs are next to each other
        if it wouldn't be done the discs at the top and left border would look to near to it
        */
        int posX = (boardPositionWidth + puffer / 2 + (puffer - discSize) / 2) + puffer * row;
        int posY = (boardPositionHeight + boardHeight - (discSize / 2) - (puffer - discSize)) - puffer * column;

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

    /**
     * @return the last player that made a move
     */
    protected ConnectFourBackend.Board.Player getPlayerTurn() {
        return connectFour.getCurrentPlayer();
    }

    /**
     * @return Enum of Board.Player of the player that has won the game
     */
    protected boolean hasWon() {
        return connectFour.hasWon();
    }

    /**
     * increases the board row size by 1
     */
    protected void increaseBoardRows() {
        boardRows += 1;
    }

    /**
     * decreases the board row size by 1
     */
    protected void decreaseBoardRows() {
        boardRows -= 1;
    }

    /**
     * increases the board column size by 1
     */
    protected void increaseBoardColumns() {
        boardColumns += 1;
    }

    /**
     * decreases the board column size by 1
     */
    protected void decreaseBoardColumns() {
        boardColumns -= 1;
    }

    /**
     * initializes a new GUI Board and makes a backend board with the current rows and columns
     */
    protected void restart() {
        connectFour.setBoardDimensions(boardRows, boardColumns);
        initializeBoard();
    }
}
