/*
Samir Kabaou
2/10/26
Tile class for the map editor that stores position and dimensions.
*/
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;
import java.util.ArrayList;

public class Tile {
    // instance varibles
    private int tileX; // X
    private int tileY; // Y
    private int tileW; // Width
    private int tileH; // Height
    public static final int TILE_HEIGHT = 40, TILE_WIDTH = 40;
    private static BufferedImage tile1_image = null; // member variable shared between all tiles1's
    private static BufferedImage tile2_image = null; // member variable shared between all tiles2's

    // Constructor
    public Tile(int x, int y, int w, int h){
        this.tileX = x;
        this.tileY = y;
        this.tileW = w;
        this.tileH = h;
    }

    // Static method
    static void load_image(){
        try {
//            this.turtle_image = ImageIO.read(new File("images/turtle.png"));
            tile1_image = ImageIO.read(new File("images/tile1.png"));
            tile2_image = ImageIO.read(new File("images/tile2.png"));
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }

    // Drawing the actual tile
    public void draw(Graphics g, int scroll){
        g.drawImage(tile1_image, this.tileX, this.tileY - scroll, this.tileW, this.tileH, null);
    }

    // Getters
    public int getTileX(){
        return tileX;
    }
    public int getTileY(){
        return tileY;
    }
    public int getTileW(){
        return tileW;
    }
    public int getTileH(){
        return tileH;
    }
    public int getLeft() { return tileX; }
    public int getRight() { return tileX + tileW; }
    public int getTop() { return tileY; }
    public int getBottom() { return tileY + tileH; }

    // Setters
    public void setTile(int x, int y, int w, int h){
        this.tileX = x;
        this.tileY = y;
        this.tileW = w;
        this.tileH = h;
    }

    // JSON saving stuff
    public Json marshal() {
        Json ob = Json.newObject(); //
        ob.add("x", this.tileX); //
        ob.add("y", this.tileY); //
        return ob;
    }

    // Unmarshalling constructor
    public Tile(Json ob) {
        this.tileX = (int)ob.getLong("x"); //
        this.tileY = (int)ob.getLong("y"); //
        this.tileW = TILE_WIDTH;
        this.tileH = TILE_HEIGHT;

        if (tile1_image == null) {load_image();} // The lazy-loading thingy
    }

    // Custom toString() methods to your objects
    @Override
    public String toString() {
        return "Tile (x,y) = (" + tileX + ", " + tileY + "), w = " + tileW + ", h = " + tileH;
    }
}