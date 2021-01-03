import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Gui {

    private JFrame frame;
    private JPanel panel;
    private JPanel score;
    private JLabel scoreLabel;
    private HashMap<Point, JLabel> coords;
    public Gui() {
        //constructor
        frame = new JFrame("Snake");
        panel = new JPanel();
        coords = new HashMap<>();

        GridLayout gridLayout = new GridLayout(20,20);
        panel.setLayout(gridLayout);

        panel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));

        int x = 1;
        int y = 1;
        for (int i =1; i<=(20*20); i++){
            final JLabel label = new JLabel();
            //label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            Dimension dimension = new Dimension(30,30);
            label.setPreferredSize(dimension);
            label.setOpaque(true);
            panel.add(label);
            coords.put(new Point(x,y),label);
            //x++;
            y++;
            if (y % 21 == 0){
                y = 1;
                x++;
            }
        }
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        score = new JPanel();
        score.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        scoreLabel = new JLabel();
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 30));
        score.add(scoreLabel);

        frame.add(panel, BorderLayout.SOUTH);
        frame.add(score, BorderLayout.NORTH);
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getScore() {
        return scoreLabel;
    }

    public HashMap<Point, JLabel> getCoords() {
        return coords;
    }

    public int getSize() {
        return coords.size();
    }
}
