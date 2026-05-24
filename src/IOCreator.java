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
        tempConstraints.gridy = 0;
        tempConstraints.gridx = 0;
        tempConstraints.weighty = 1;
        tempConstraints.weightx = 1;
        tempConstraints.insets = new Insets(10,10,10,30);

        JCheckBox outBox = new JCheckBox("Output?");
        outBox.setToolTipText("Leave unchecked to indicate an input");
        panel.add(outBox, tempConstraints);

        tempConstraints.gridx = 2;
        JComboBox<String> items = new JComboBox<>(RecipeCreator.items.toArray(new String[0]));
        panel.add(items, tempConstraints);

        tempConstraints.gridy = 1;
        tempConstraints.gridx = 0;
        JCheckBox goalBox = new JCheckBox("Goal?");
        goalBox.setToolTipText("Indicates if this is a main goal of the factory");
        //goalBox.addItemListener((e) -> items.setEnabled(e.getStateChange() == ItemEvent.SELECTED));
        panel.add(goalBox,tempConstraints);

        super.setVisible(true);
    }
}
