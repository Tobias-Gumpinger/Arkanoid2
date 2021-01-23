package at.ac.fhcampuswien;
import javax.swing.*;



public class App {
/**
 *
 ***Testing for JavaFX***
    @Override
    public void start(Stage primaryStage) throws Exception{

        //primaryStage.setTitle("Hello World - Test deux!");
        //Pane root = new Pane();
        //Scene scene = new Scene(root, 300,275);
        //primaryStage.setScene(new Scene(new Pane(), 300, 275));
        //Pane root = new Pane();
        //Rectangle rectangle = new Rectangle(50,50);
        //root.getChildren().add(rectangle);
        //primaryStage.show();
    }
    **/



    /***Java Swing***/

    public static void main(String[] args) {
        JFrame object = new JFrame(); // the window object, creates the window
        Arkanoid game = new Arkanoid(); //object Arkanoid handed over to JFrame
        object.setBounds(10,10,1280,800); //x and y define the position where the window opens, plus window size
        object.setTitle("Arkanoid"); //title of window (top left corner)
        object.setResizable(false); //fixed size of window
        object.setVisible(true); //needs to be set true to render the window
        object.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when window is closed the program stops
        object.add(game); //object is executed
    }
}
