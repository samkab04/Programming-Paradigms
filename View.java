/*
Samir Kabaou
1/29/26
Make a game that uses your mouse and keyboard to control a turtle using an MVC.
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

public class View extends JPanel {
    //    private final JButton b1;
    private final Model model;
    private BufferedImage turtle_image;
    private BufferedImage tile1_image;
    private BufferedImage tile2_image;
    public boolean editMode = false;
    public boolean addMapItem = false;
    private int scroll = 0;


    //draw mspacman in view
    public View(Controller c, Model m) {
//        b1 = new JButton("something else. (I do not care what you change it to.)"); // button title
//        b1.addActionListener(c);
//        this.add(b1);
        c.setView(this);
        model = m;

        this.tile1_image = loadImage("images/tile1.png");
        this.tile2_image = loadImage("images/tile2.png");
    }
    /*
        public void removeButton() {
            this.remove(b1);
        }
    */

    public static BufferedImage loadImage (String filename){
        BufferedImage image = null;
        try
        {
            image = ImageIO.read(new File(filename));
            System.out.println(filename + " has been loaded.");
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
            System.exit(1);
        }
        return image;
    }

    public void paintComponent(Graphics g) {
        g.setColor(new Color(14, 128, 0)); // Only works if put on the top
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        ArrayList<Tile> tiles = model.getTiles(); // Scrolling Method
//        g.drawImage(this.turtle_image, model.getTurtleX(), model.getTurtleY(), null);
        // Get to try to draw tiles
        for (int i = 0; i < tiles.size(); i++) {
            Tile t = tiles.get(i);
            t.draw(g, scroll);
        }
        model.getMsPacman().drawYourself(g, this.scroll);
        if (editMode) {
            if (addMapItem) {
                g.setColor(new Color(0, 255, 0, 100));
                g.fillRect(0, 0, 100, 100); // 100x100 box in top left corner
            } else {
                g.setColor(new Color(255, 0, 0, 100));
                g.fillRect(0, 0, 100, 100); // 100x100 box in top left corner
            }
            g.drawImage(this.tile1_image, 0, 0, null);
            }
    }

    // Change scroll position
    public void setScroll(int s){
        this.scroll = s;
    }

    public int getScroll(){
        return this.scroll;
    }

    public void setEditMode(){
        editMode = !editMode;
    }

    public void setAddMapItem(boolean b) { //I looked everywhere and the only way I could find that made the remove function worek was using booleans
        this.addMapItem = b;
    }
}