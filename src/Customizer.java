import javax.swing.*;
import java.awt.*;

public class Customizer {
    private Customizer(){}

    public static void greyBackground(JPanel panel){
        panel.setBackground(Color.darkGray);
        panel.setBorder(BorderFactory.createSoftBevelBorder(0,Color.black, Color.black));
        panel.setLayout(new GridBagLayout());
    }

    public static void blackComponent(Component component){
        component.setBackground(Color.black);
        component.setForeground(Color.white);
    }
}
