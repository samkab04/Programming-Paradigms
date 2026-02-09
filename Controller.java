/*
Samir Kabaou
1/29/26
Make a game that uses your mouse and keyboard to control a turtle using an MVC.
*/
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener {
    private final Model model;
    private boolean keepGoing;
    private View view;
    private boolean keyLeft;
    private boolean keyRight;
    private boolean keyUp;
    private boolean keyDown;

    public Controller(Model m) {
        model = m;
        keepGoing = true;
    }

    public void actionPerformed(ActionEvent e) {
        view.removeButton();
    }

    public boolean update() {
        if (keyRight)
            //call a method in model that sets the turtle's
            model.setTurtleDestination(model.getTurtleX() + 10, model.getTurtleY());
        //turtleDestination to turtleDestX++ or something
        //using a provided variable...
        if (keyLeft)
            //call a method in model that sets the turtle's
            model.setTurtleDestination(model.getTurtleX() - 10, model.getTurtleY());
        //turtleDestination to turtleDestX--
        if (keyDown)
            //do the same for turtleDestY++
            model.setTurtleDestination(model.getTurtleX(), model.getTurtleY() + 10);
        if (keyUp)
            //do the same for turtleDestY--
            model.setTurtleDestination(model.getTurtleX(), model.getTurtleY() - 10);

        //the Controller keeps track of whether or not we have
        //quit the program and returns this value to the Game
        //engine of whether or not to continue the game loop
        return keepGoing;
    }

    public void setView(View v) {
        view = v;

    }

    public void mousePressed(MouseEvent e) {
        model.setTurtleDestination(e.getX(), e.getY());
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                keyRight = true;
                break;
            case KeyEvent.VK_LEFT:
                keyLeft = true;
                break;
            case KeyEvent.VK_UP:
                keyUp = true;
                break;
            case KeyEvent.VK_DOWN:
                keyDown = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                keyRight = false;
                break;
            case KeyEvent.VK_LEFT:
                keyLeft = false;
                break;
            case KeyEvent.VK_UP:
                keyUp = false;
                break;
            case KeyEvent.VK_DOWN:
                keyDown = false;
                break;
            case KeyEvent.VK_ESCAPE:
                keepGoing = false;
        }
        char c = Character.toLowerCase(e.getKeyChar());
        if (c == 'q')
            keepGoing = false;
    }

    public void keyTyped(KeyEvent e) {
    }
}




