import javax.swing.*;
import java.awt.*;

/**
 * A basic class that conveniently sets the colors of components
 * @author Patrik Novotný
 */
public class Customizer {
    private Customizer(){}

    /**
     * Sets the panels color to dark gray adds a black border and sets the layout to a new <code>GridBagLayout</code>
     * @param panel the JPanel to edit
     */
    public static void greyBackground(JPanel panel){
        panel.setBackground(Color.darkGray);
        panel.setBorder(BorderFactory.createSoftBevelBorder(0,Color.black, Color.black));
        panel.setLayout(new GridBagLayout());
    }

    /**
     * Sets the components color to black and the text on it to white
     * @param component the component to edit
     */
    public static void blackComponent(Component component){
        component.setBackground(Color.black);
        component.setForeground(Color.white);
    }
}
