import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Controller {

    public Gui gui;
    public Snake snake;
    private String direction;
    private String newDirection;
    private String oldDirection;
    private Point food;

    public Controller() {
        //constructor
        gui = new Gui();
        snake = new Snake();
        direction = "";
        newDirection = "";
        oldDirection = "";

        setKeyListeners();
        setSnake();
        food = setFood();
        move();
    }

    private void setSnake() {
        ArrayList<Point> body = snake.getSnake();
        HashMap<Point, JLabel> labels = gui.getCoords();

        // Loop over the grid
        for (HashMap.Entry<Point, JLabel> labelsEntry : labels.entrySet()) {
            Point labelsKey = labelsEntry.getKey();
            JLabel labelsValue = labelsEntry.getValue();

            // Loop over the snakes body
            for (Point bodyValue : body) {
                // Add the snake to the grid
                if (labelsKey.equals(bodyValue)) {
                    labelsValue.setBackground(Color.BLACK);
                    labelsValue.setText("this");
                }
            }
        }
    }

    private Point setFood() {
        ArrayList<Point> body = snake.getSnake();
        HashMap<Point, JLabel> labels = gui.getCoords();

        boolean overlap = true;
        Point food = new Point();

        while (overlap) {
            // Get random coordinate
            food = getRandomPoint();
            // Loop over the snakes body
            for (Point bodyValue : body) {
                // Check if the random point overlaps the snake
                if (bodyValue.equals(food)) {
                    overlap = true;
                    break;
                }
                else {
                    overlap = false;
                }
            }
        }

        // Loop over the gui to add the food
        for(HashMap.Entry<Point, JLabel> labelsEntry : labels.entrySet()) {
            Point labelsKey = labelsEntry.getKey();
            JLabel labelsValue = labelsEntry.getValue();

            // Check if the label is in the correct position to add the food
            if(labelsKey.equals(food)){
                labelsValue.setOpaque(true);
                labelsValue.setBackground(Color.GREEN);
                labelsValue.repaint();
                //gui.getFrame().pack();
                break;
            }
        }
        return food;
    }

    private Point getRandomPoint() {
        int min = 1;
        int max = 20;
        int random_int_x = (int) (Math.random() * (max - min + 1) + min);
        int random_int_y = (int) (Math.random() * (max - min + 1) + min);

        return new Point(random_int_x, random_int_y);
    }

    private void setKeyListeners() {
        gui.getFrame().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
                if(e.getKeyCode() == 37) {
                    newDirection = "left";
                }
                if(e.getKeyCode() == 38) {
                    newDirection = "up";
                }
                if(e.getKeyCode() == 39) {
                    newDirection = "right";
                }
                if(e.getKeyCode() == 40) {
                    newDirection = "down";
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    private void move() {
        while(snake.getLength() < gui.getSize()) {
            ArrayList <Point> newSnake = snake.getSnake();
            switch (direction) {
                case "down":
                    newSnake = moveDown();
                    break;
                case "up":
                    newSnake = moveUp();
                    break;
                case "left":
                    newSnake = moveLeft();
                    break;
                case "right":
                    newSnake = moveRight();
                    break;
            }

            checkEaten();

            for(HashMap.Entry<Point, JLabel> labelsEntry : gui.getCoords().entrySet()) {
                Point labelsKey = labelsEntry.getKey();
                JLabel labelsValue = labelsEntry.getValue();

                 if(!labelsKey.equals(food)) {
                     labelsValue.setOpaque(false);
                 }
                 if(newSnake.contains(labelsKey)){
                     labelsValue.setOpaque(true);
                     labelsValue.setBackground(Color.BLUE);
                 }
                 labelsValue.repaint();

            }
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            }
            catch(InterruptedException e){
                //continue
            }

            changeDirection();

        }
    }

    private ArrayList <Point> moveDown(){
        if(!oldDirection.equals("up")) {
            ArrayList <Point> body = snake.getSnake();
            ArrayList <Point> newSnake = snake.getSnake();

            Point end = body.get(body.size()-1);
            Point start = body.get(0);
            int xCoord;
            int yCoord = end.y;
            if(end.x + 1 > 20){
                xCoord = 1;
            }
            else {
                xCoord = end.x + 1;
            }
            newSnake.add( new Point(xCoord, yCoord) );
            newSnake.remove(start);

            return newSnake;
        }
        else {
            return moveUp();
        }
    }

    private ArrayList <Point> moveUp(){
        if(!oldDirection.equals("down")) {
            ArrayList <Point> body = snake.getSnake();
            ArrayList <Point> newSnake = snake.getSnake();

            Point end = body.get(body.size()-1);
            Point start = body.get(0);
            int xCoord;
            int yCoord = end.y;
            if(end.x - 1 < 1){
                xCoord = 20;
            }
            else {
                xCoord = end.x - 1;
            }
            newSnake.add( new Point(xCoord, yCoord) );
            newSnake.remove(start);

            return newSnake;
        }
        else {
            return moveDown();
        }
    }

    private ArrayList <Point> moveRight(){
        if(!oldDirection.equals("left")) {
            ArrayList <Point> body = snake.getSnake();
            ArrayList <Point> newSnake = snake.getSnake();

            Point end = body.get(body.size()-1);
            Point start = body.get(0);
            int xCoord = end.x;
            int yCoord;
            if(end.y + 1 > 20){
                yCoord = 1;
            }
            else {
                yCoord = end.y + 1;
            }
            newSnake.add( new Point(xCoord, yCoord) );
            newSnake.remove(start);

            return newSnake;
        }
        else {
            return moveLeft();
        }
    }

    private ArrayList <Point> moveLeft(){
        if(!oldDirection.equals("right")) {
            ArrayList <Point> body = snake.getSnake();
            ArrayList <Point> newSnake = snake.getSnake();

            Point end = body.get(body.size()-1);
            Point start = body.get(0);
            int xCoord = end.x;
            int yCoord;
            if(end.y - 1 < 1){
                yCoord = 20;
            }
            else {
                yCoord = end.y - 1;
            }
            newSnake.add( new Point(xCoord, yCoord) );
            newSnake.remove(start);

            return newSnake;
        }
        else {
            return moveRight();
        }
    }

    private void changeDirection() {
        if(direction.equals("") || (direction.equals("left") && !newDirection.equals("right")) || (direction.equals("right") && !newDirection.equals("left")) || (direction.equals("up") && !newDirection.equals("down")) || (direction.equals("down") && !newDirection.equals("up")) ) {
            // Make a record of the previous direction so the snake cannot move in opposite directions
            oldDirection = direction;
            // Change the direction once per move
            direction = newDirection;
        }
    }

    private void checkEaten() {
        if(snake.getSnake().contains(food)) {
            snake.getSnake().add(food);
            food = setFood();
        }
    }

}
