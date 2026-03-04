/*
Samir Kabaou
2/10/26
Tile class for the map editor that stores position and dimensions.
*/
public class Tile {
   // instance varibles
    private int tileX; // X
    private int tileY; // Y
    private int tileW; // Width
    private int tileH; // Height
    public static final int TILE_HEIGHT = 40, TILE_WIDTH = 40;

    // Constructor
    public Tile(int x, int y, int w, int h){
        this.tileX = x;
        this.tileY = y;
        this.tileW = w;
        this.tileH = h;
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
    }
}
