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
    private boolean editMode;
    private boolean addMapItem;

    public Controller(Model m) {
        model = m;
        keepGoing = true;
        keyRight = false;
        keyLeft = false;
        keyUp = false;
        keyDown = false;
    }

    public void actionPerformed(ActionEvent e) {
//        view.removeButton();
    }

    public boolean update() {
        if(!editMode) {
            if (keyRight)
                //call a method in model that sets the turtle's
                model.setMspacmanDestination(model.getMspacmanX()+ 1000000000, model.getMspacmanY());
            //mspacmanDestination to mspacmanDestX++ or something
            //using a provided variable...
            if (keyLeft)
                //call a method in model that sets the turtle's
                model.setMspacmanDestination(model.getMspacmanX() - 1000000000, model.getMspacmanY());
            //turtleDestination to turtleDestX--
            if (keyDown)
                //do the same for turtleDestY++
                model.setMspacmanDestination(model.getMspacmanX(), model.getMspacmanY() + 1000000000);
            if (keyUp)
                //do the same for turtleDestY--
                model.setMspacmanDestination(model.getMspacmanX(), model.getMspacmanY() - 1000000000);

            //the Controller keeps track of whether or not we have
            //quit the program and returns this value to the Game
            //engine of whether or not to continue the game loop
        }
        return keepGoing;
    }

    public void setView(View v) {
        view = v;

    }

    public void loadMap() {
        Json loadmap = Json.load("map.json");
        model.unmarshal(loadmap);
        System.out.println("Map loaded");
    }

    public void mousePressed(MouseEvent e) {
//        model.setMspacmanDestination(e.getX(), e.getY());
        //Snap to grid logic
        if (editMode) {
            // Screen cords
            int mouseX = e.getX();
            int screenY = e.getY();
            // screen cords -> world cords
            int worldY = screenY + view.getScroll();

            //Snapping mouse to grid
            int snapX = Math.floorDiv(mouseX, Tile.TILE_WIDTH) * Tile.TILE_WIDTH;
            int snapY = Math.floorDiv(worldY, Tile.TILE_HEIGHT) * Tile.TILE_HEIGHT;


            if (addMapItem) {
                model.addTile(snapX, snapY); //Add tile
            } else {
                model.removeTile(snapX, snapY); //Remove tileaa
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getY() < 100) {
            System.out.println("break here");
        }
    }

    public void keyPressed(KeyEvent e) {
        keyRight = false;
        keyLeft = false;
        keyUp = false;
        keyDown = false;

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
                break;
            case KeyEvent.VK_Q: //Quit by pressing q
                keepGoing = false;
                break;
            case KeyEvent.VK_E:
                editMode = !editMode;
                view.setEditMode();
                break;
            case KeyEvent.VK_C: // clearing map
                if (editMode) {
                    model.clearMap();
                }
                break;
            case KeyEvent.VK_A:
                if (editMode) {
                    addMapItem = true; // Set mode to Add
                    view.setAddMapItem(true);
                }
                break;
            case KeyEvent.VK_R:
                if (editMode) {
                    addMapItem = false; // Set mode to Remove
                    view.setAddMapItem(false);
                }
                break;
            /*case KeyEvent.VK_8:
                //moveup
                view.setScroll(view.getScroll() - Tile.TILE_HEIGHT);
                break;
            case KeyEvent.VK_2:
                //move down
                view.setScroll(view.getScroll() + Tile.TILE_HEIGHT);
                break;*/
        }
        char c = Character.toLowerCase(e.getKeyChar());
        if (c == 'q')
            keepGoing = false;
        else if (c == 's') {
            Json savemap = model.marshal();
            savemap.save("map.json");
            System.out.println("Map saved to map.json");
        } else if (c == 'l') {
            loadMap();
        }
    }

    public void keyTyped(KeyEvent e) {
    }
}