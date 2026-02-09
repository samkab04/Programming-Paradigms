/*
Samir Kabaou
1/29/26
Make a game that uses your mouse and keyboard to control a turtle using an MVC.
*/
public class Model
{
    private final int turtleSpeed;
    private int turtleX;
    private int turtleY;
    private int turtleDestX;
    private int turtleDestY;

    public Model()
    {
        turtleSpeed = 4; //speed
    }

    public void update()
    {
        // Move the turtle
        if(this.turtleX < this.turtleDestX) //right
            turtleX += Math.min(turtleSpeed, turtleDestX - turtleX);
//            this.turtleX += turtleSpeed;
        else if(this.turtleX > this.turtleDestX) //left
            turtleX -= Math.min(turtleSpeed, turtleDestX + turtleX);
//            this.turtleX -= turtleSpeed;
        if(this.turtleY < this.turtleDestY) //up
            turtleY += Math.min(turtleSpeed, turtleDestY - turtleY);
//            this.turtleY += turtleSpeed;
        else if(this.turtleY > this.turtleDestY) //down
            turtleY -= Math.min(turtleSpeed, turtleDestY + turtleY);
//            this.turtleY -= turtleSpeed;
    }

    public void setTurtleDestination(int x, int y)
    {
        this.turtleDestX = x;
        this.turtleDestY = y;
    }

    public int getTurtleX()
    {
        return turtleX;
    }

    public int getTurtleY()
    {
        return turtleY;
    }
}
