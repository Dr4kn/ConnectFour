package ConnectFourGUI;

import processing.core.PApplet;

// going to probably used to create Discs to put "into" the board
public class Disc {
    private final PApplet pApplet;
    private final int discSize = 50;
    private final int puffer = discSize + 10;
    private final String color;
    private final int posX;
    private final int posY;

    protected Disc(PApplet pApplet, int posX, int posY, String color) {
        this.pApplet = pApplet;
        this.posX = posX;
        this.posY = posY;
        this.color = color;
    }

    protected void draw() {
        switch (color) {
            case "red" -> {
                pApplet.fill(102, 0, 0);
                pApplet.circle(posX, posY, discSize);
            }
            case "yellow" -> {
                pApplet.fill(153, 153, 0);
                pApplet.circle(posX, posY, discSize);
            }
            case "black" -> {
                pApplet.fill(0, 0, 0);
                pApplet.circle(posX, posY, discSize);
            }
            default -> throw new IllegalArgumentException("color is not allowed");
        }
    }
}
