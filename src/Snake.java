import java.awt.*;
import java.util.ArrayList;

public class Snake {

    private ArrayList <Point> snake;
    public Snake() {
        //constructors
        snake =  new ArrayList<>();
        snake.add(new Point(10,10));
    }

    public ArrayList <Point> getSnake() {
        return snake;
    }

    public int getLength() {
        return snake.size();
    }
}
