/*
Samir Kabaou
1/29/26
Make a game that uses your mouse and keyboard to control a mspacman using an MVC.
*/
import java.util.ArrayList;
import java.util.Iterator;

public class Model
{
    private final int mspacmanSpeed;
    private int mspacmanX;
    private int mspacmanY;
    private int mspacmanDestX;
    private int mspacmanDestY;
    private MsPacman msPacman;
    private ArrayList<Tile> tiles; // member varible
    Tile tile;


    //Call for mspacman in model
    public Model()
    {
        mspacmanSpeed = 4; //speed
        tiles = new ArrayList<Tile>(); // Model constructor
        /*
        // Arraylist
        Tile t = new Tile(400, 300, 50, 50); // added values for the width and height
        tiles.add(t);
         */
        msPacman = new MsPacman();
        msPacman.intializeVars();
    }

    public MsPacman getMsPacman(){
        return msPacman;
    }

    public void update()
    {
        msPacman.setPreviousPosition(); // Always save before moving

        // Mspacman can move :)
        if(msPacman.getX() < this.mspacmanDestX) { //right
            msPacman.moveRight();
        } else if(msPacman.getX() > this.mspacmanDestX) { //left
            msPacman.moveLeft();
        }

        if(msPacman.getY() > this.mspacmanDestY) { //up
            msPacman.moveUp();
        } else if(msPacman.getY() < this.mspacmanDestY) { //down
            msPacman.moveDown();
        }

        msPacman.update(); // screen wrapping

        // Collision Detection
        for(Tile t : tiles) {
            if(detectCollisions(msPacman, t)) {
                // System.out.println("There is a collisons!" + ++ numCollisions); // debug
                msPacman.getOutOfTile(t); // Pushes her back 1 pixel
            }
        }
    }

    public int getMspacmanX() { return msPacman.getX(); }
    public int getMspacmanY() { return msPacman.getY(); }

// Detecting if the object is colliding anything
private boolean detectCollisions(MsPacman p, Tile t){
    //If her right side is to the right of the tiel left
    if(p.getRight() < t.getLeft())
        return false;
    //if her left side is tot he right of the tile right
    if(p.getLeft() > t.getRight())
        return false;
    //if her toes are above the tile
    if(p.getBottom() < t.getTop())
        return false;
    //if her head is below the tile
    if(p.getTop() > t.getBottom())
        return false;
    return true;
}

public void setMspacmanDestination(int x, int y)
{
    this.mspacmanDestX = x;
    this.mspacmanDestY = y;
}

// getter for view->tiles
public ArrayList<Tile> getTiles()
{
    return tiles;
}

public void addTile(int x, int y) // Adding a tile
{
    for (int i = 0; i < tiles.size(); i++) {
        Tile t = tiles.get(i);
        if (t.getTileX() == x && t.getTileY() == y) {
            return;
        }
    }
    tiles.add(new Tile(x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT)); // add the actual tile
}

//Clearing the map
public void clearMap()
{
    tiles = new ArrayList<Tile>();
}

public void removeTile(int x, int y) {
        Iterator<Tile> it = tiles.iterator();
    // Find and remove tile at the specified grid position
    while(it.hasNext()) {
        Tile t = it.next();
        if (t.getTileX() == x && t.getTileY() == y) {
            it.remove(); // This is the safest way to remove while looping
            break; // Remove only one tile per click
        }
    }
}


//JSON stuff
public Json marshal(){
    Json ob = Json.newObject();
    Json tmpList = Json.newList();
    ob.add("tiles", tmpList);
    // Go though the entire Array list
    for(int i = 0; i < tiles.size(); i++){
        tmpList.add(tiles.get(i).marshal());
    }
    return ob;
}

public void unmarshal(Json ob){
    tiles.clear(); // claer array
    Json tmpList = ob.get("tiles");
    //rebuild tiles
    for(int i = 0; i < tmpList.size(); i++){
        tiles.add(new Tile(tmpList.get(i)));
    }
}

}