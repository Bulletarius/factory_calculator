import javax.swing.*;
import java.awt.*;

public class RecipeCreator extends JDialog {
    public RecipeCreator(JFrame main){
        super(main,"Recipe Creator", true);
        super.setSize(500,450);
        super.setLocationRelativeTo(main);
        super.setResizable(true);
        super.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        super.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel top = new JPanel();
        top.setBackground(Color.darkGray);
        top.setBorder(BorderFactory.createSoftBevelBorder(0,Color.black, Color.black));
        top.setLayout(new BorderLayout());
        JLabel topLabel = new JLabel("In items per second");
        topLabel.setForeground(Color.white);
        top.add(topLabel,BorderLayout.NORTH);
        super.add(top,BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setBackground(Color.darkGray);
        bottom.setForeground(Color.darkGray);
        bottom.setBorder(BorderFactory.createSoftBevelBorder(0,Color.black, Color.black));
        bottom.setLayout(new BorderLayout());
        JLabel bottomLabel = new JLabel("In item count and time taken");
        bottomLabel.setForeground(Color.white);
        bottom.add(bottomLabel, BorderLayout.NORTH);
        super.add(bottom,BorderLayout.CENTER);

        JTextField reqPS1 = new JTextField(3);
        top.add(reqPS1);

        super.setVisible(true);
    }
}
