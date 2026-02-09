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

public class View extends JPanel {
    private final JButton b1;
    private final Model model;
    private BufferedImage turtle_image;

    public View(Controller c, Model m) {
        b1 = new JButton("something else. (I do not care what you change it to.)"); // button title
        b1.addActionListener(c);
        this.add(b1);
        c.setView(this);
        model = m;

        try {
            this.turtle_image = ImageIO.read(new File("images/turtle.png"));
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }

    public void removeButton() {
        this.remove(b1);
    }

    public void paintComponent(Graphics g) {
        g.setColor(new Color(254, 189, 149)); // Only works if put on the top
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(this.turtle_image, model.getTurtleX(), model.getTurtleY(), null);
    }
}
