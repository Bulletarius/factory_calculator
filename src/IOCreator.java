import javax.swing.*;
import java.awt.*;

public class IOCreator extends JDialog {

    public IOCreator(App main){
        super(main,"Create output or input",true);
        super.setSize(800, 600);
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        super.setLocationRelativeTo(main);
        JPanel panel = new JPanel(new GridBagLayout());
        Customizer.greyBackground(panel);
        super.add(panel, BorderLayout.CENTER);

        GridBagConstraints tempConstraints = new GridBagConstraints();

        super.setVisible(true);
    }
}
