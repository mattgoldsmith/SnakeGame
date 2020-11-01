import java.awt.*;
import java.util.HashMap;

public class Snake {

    private HashMap <Integer, Point> snake;
    public Snake() {
        //constructor
        snake =  new HashMap<Integer, Point>();
        snake.put(new Integer(1), new Point(10,10));
    }

    public HashMap<Integer, Point> getSnake() {
        return snake;
    }
}
