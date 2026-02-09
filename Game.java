/*
Samir Kabaou
1/29/26
Make a game that uses your mouse and keyboard to control a turtle using an MVC.
*/
import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame {
    private boolean keepGoing;
    private Model model;
    private Controller controller;
    private View view;

    public Game() {
        model = new Model();
        controller = new Controller(model);
        view = new View(controller, model);
        keepGoing = true;
        view.addMouseListener(controller);
        this.setTitle("Turtle wars!");  // Application name
        this.setSize(500, 500);
        this.setFocusable(true);
        this.getContentPane().add(view);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addKeyListener(controller);
    }

    static void main(String[] args) {
        Game g = new Game();
        g.run();
    }

    public void run()
    {
        do
        {
            keepGoing = controller.update();
            model.update();
            view.repaint(); // This will indirectly call View.paintComponent
            Toolkit.getDefaultToolkit().sync(); // Updates screen

            // Go to sleep for 50 milliseconds
            try
            {
                Thread.sleep(50);
            } catch(Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        while(keepGoing);

        // change the following line to a goodbye message of some kind
        System.out.println("Goodbye turtles. Im gonna miss y'all ;-;");
        System.exit(0);
    }
}
