/*
Samir Kabaou
1/29/26
Make a game that uses your mouse and keyboard to control a turtle using an MVC.
*/
import java.util.ArrayList;

public class Model
{
//    private final int turtleSpeed;
//    private int turtleX;
//    private int turtleY;
//    private int turtleDestX;
//    private int turtleDestY;
private ArrayList<Tile> tiles; // member varible
Tile tile;



    public Model()
    {
//        turtleSpeed = 4; //speed
        tiles = new ArrayList<Tile>(); // Model constructor
        /*
        // Arraylist
        Tile t = new Tile(400, 300, 50, 50); // added values for the width and height
        tiles.add(t);
         */
    }

    public void update()
    {
        /*
        // Move the turtle
        if(this.turtleX < this.turtleDestX) //right
           turtleX += Math.min(turtleSpeed, turtleDestX - turtleX);
           this.turtleX += turtleSpeed;
       else if(this.turtleX > this.turtleDestX) //left
          turtleX -= Math.min(turtleSpeed, turtleDestX + turtleX);
//            this.turtleX -= turtleSpeed;
        if(this.turtleY < this.turtleDestY) //up
           turtleY += Math.min(turtleSpeed, turtleDestY - turtleY);
//            this.turtleY += turtleSpeed;
       else if(this.turtleY > this.turtleDestY) //down
           turtleY -= Math.min(turtleSpeed, turtleDestY + turtleY);
//            this.turtleY -= turtleSpeed;
         */
    }
    /*
    public void setTurtleDestination(int x, int y)
    {
        this.turtleDestX = x;
        this.turtleDestY = y;
    }
     */

/*
    public int getTurtleX()
    {
        return turtleX;
    }

    public int getTurtleY()
    {
        return turtleY;
    }
*/

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
        // Find and remove tile at the specified grid position
        for (int i = tiles.size() - 1; i >= 0; i--) {
            Tile tile = tiles.get(i);
            if (tile.getTileX() == x && tile.getTileY() == y) {
                tiles.remove(i);
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