/*
Samir Kabaou
2/6/26
Making Mrs.Pacman collide with the boxes
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

public class MsPacman{
    private int x, y;
    private int px, py;
    private int w, h;
    private final int MAX_DIRECTIONS = 4, NUM_IMAGES_PER_DIRECTION = 3;
    private int numFrame = 0;
    private int direction;
    private double speed;
    private BufferedImage image;
    private BufferedImage[][] images;
    private final int MSPACMAN_WIDTH = 30;
    private final int MSPACMAN_HEIGHT = 30;

    public void intializeVars(){
        this.w = MSPACMAN_WIDTH;
        this.h = MSPACMAN_HEIGHT;

        //Starting postition
        this.x = ((Game.WORLD_WIDTH / 2) / Tile.TILE_WIDTH) * Tile.TILE_WIDTH + 5;
        this.y = ((Game.WORLD_HEIGHT / 2) / Tile.TILE_HEIGHT) * Tile.TILE_HEIGHT + 5;

        speed = 10.0;
        if(image == null)
            image = View.loadImage("images/mspacman1.png");
        images = new BufferedImage[MAX_DIRECTIONS][NUM_IMAGES_PER_DIRECTION];
        int imageNum = 1;
        for(int i = 0; i < MAX_DIRECTIONS; i++)
            for(int j = 0; j < NUM_IMAGES_PER_DIRECTION; j++)
                images[i][j] = View.loadImage("images/mspacman" + imageNum++ + ".png");
    }

    //getters
    public int getX() {return this.x;}
    public int getY() {return this.y;}
    public double getSpeed() {return this.speed;}

    public void update(){
        //Wrap left to right
        if (this.x < 0){
            this.x = Game.WORLD_WIDTH;
        }
        // Wrap right to left
        else if (this.x > Game.WORLD_WIDTH){
            this.x = 0;
        }
        //Wrap up to down
        else if (this.y < 0){
            this.y = Game.WORLD_WIDTH;
        }
        //Wrap down to up
        else if (this.y > Game.WORLD_WIDTH){
            this.y = 0;
        }
    }

    //Setter for previous postion
    public void setPreviousPosition(){
        px = x;
        py = y;
    }

    // The getters
    public int getLeft(){return x;}
    public int getRight(){return x + w;}
    public int getTop(){return y;}
    public int getBottom(){return y + h;}
    private int getPreviousRight(){return px + w;}
    private int getPreviousLeft(){return px;}
    private int getPreviousBottom(){return py + h;}
    private int getPreviousTop(){return py;}

    public void drawYourself(Graphics g, int scroll){
        numFrame = numFrame % NUM_IMAGES_PER_DIRECTION;
        g.drawImage(images[direction][numFrame], x, y - scroll,h, w, null);
    }

    // Movement animation
    public void moveLeft(){ //Left sprites =
        this.x -= speed;
        if(++numFrame >= NUM_IMAGES_PER_DIRECTION - 1)
            numFrame = 0;
        direction = 1;
    }

    public void getOutOfTile(Tile t){
        // Colliding from the left (moving right)
        if (getRight() >= t.getLeft() && getPreviousRight() <= t.getLeft())
            x = t.getLeft() - w - 1;
            // Colliding from the right (moving left)
        else if(getLeft() <= t.getRight() && getPreviousLeft() >= t.getRight())
            x = t.getRight() + 1;

        // Top collision (moving down)
        if(getBottom() >= t.getTop() && getPreviousBottom() <= t.getTop())
            y = t.getTop() - h - 1;
            // Bottom collision (moving up)
        else if(getTop() <= t.getBottom() && getPreviousTop() >= t.getBottom())
            y = t.getBottom() + 1;
    }

    public void moveRight(){ //Right sprites
        this.x += speed;
        numFrame = (1 + numFrame) % NUM_IMAGES_PER_DIRECTION;
        direction = 2;
    }

    public void moveUp(){ //Up sprites
        this.y -= speed;
        updateImageNum();
        direction = 3;
    }
    public void moveDown(){ //Down sprites
        this.y += speed;
        updateImageNum();
        direction = 0;
    }

    private void updateImageNum(){
        numFrame = (1 + numFrame) % NUM_IMAGES_PER_DIRECTION;
    }

    // Custom toString() methods to your objects
    @Override
    public String toString() {
        return "MsPacman (x,y) = (" + x + ", " + y + "), w = " + w + ", h = " + h + ", speed = " + speed;
    }
}