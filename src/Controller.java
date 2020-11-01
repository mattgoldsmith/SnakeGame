import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Controller {

    public Gui gui;
    public Snake snake;

    public Controller() {
        //constructor
        gui = new Gui();
        snake = new Snake();
        setSnake();
    }

    private void setSnake() {
        HashMap<Integer, Point> body = snake.getSnake();
        HashMap<Point, JLabel> labels = gui.getCoords();

        // Loop over the grid
        for (HashMap.Entry<Point, JLabel> labelsEntry : labels.entrySet()) {
            Point labelsKey = labelsEntry.getKey();
            JLabel labelsValue = labelsEntry.getValue();

            // Loop over the snakes body
            for (HashMap.Entry<Integer, Point> bodyEntry : body.entrySet()) {
                Integer bodyKey = bodyEntry.getKey();
                Point bodyValue = bodyEntry.getValue();

                // Add the snake to the grid
                if (labelsKey.equals(bodyValue)) {
                    labelsValue.setBackground(Color.BLACK);
                    labelsValue.setText("this");
                }
            }
        }
    }

    private void setFood() {
        HashMap<Integer, Point> body = snake.getSnake();
        HashMap<Point, JLabel> labels = gui.getCoords();

        boolean overlap = true;
        Point food;

        while (overlap) {
            // Get random coordinate
            food = getRandomPoint();
            // Loop over the snakes body
            for (HashMap.Entry<Integer, Point> bodyEntry : body.entrySet()) {
                Integer bodyKey = bodyEntry.getKey();
                Point bodyValue = bodyEntry.getValue();

                // Check if the random point overlaps the snake
                if (bodyValue.equals(food)){
                    overlap = true;
                    break;
                }
            }
        }

        //TODO: loop over the gui to add the food
    }

    private Point getRandomPoint() {
        int min = 1;
        int max = 20;
        int random_int_x = (int) (Math.random() * (max - min + 1) + min);
        int random_int_y = (int) (Math.random() * (max - min + 1) + min);

        Point random = new Point(random_int_x, random_int_y);

        return random;
    }
}
