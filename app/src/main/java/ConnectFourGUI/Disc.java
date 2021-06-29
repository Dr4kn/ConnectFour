package ConnectFourGUI;

import processing.core.PApplet;

/**
 * creates a single discs to be put into the board
 */
public class Disc {
    private final PApplet pApplet;
    private final int posX, posY;
    private final Color color;

    /**
     * @param pApplet processing
     * @param posX position in the processing grid it should be placed
     * @param posY position in the processing grid it should be placed
     * @param color red, yellow, or black
     */
    protected Disc(PApplet pApplet, int posX, int posY, Color color) {
        this.pApplet = pApplet;
        this.posX = posX;
        this.posY = posY;
        this.color = color;
    }

    /**
     * gives the current discs its color
     */
    protected void draw() {
        switch (color) {
            case RED -> {
                pApplet.fill(102, 0, 0);
            }
            case YELLOW -> {
                pApplet.fill(153, 153, 0);
            }
            case BLACK -> {
                pApplet.fill(0, 0, 0);
            }
            default -> throw new IllegalArgumentException("color is not allowed");
        }
        pApplet.circle(posX, posY, 50);
    }

    /**
     * used to set the color the disc should have
     */
    protected enum Color {
        RED,
        YELLOW,
        BLACK
    }
}
