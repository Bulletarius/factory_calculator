import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

/**
 * A dialog window for the user to create a new input or output
 * @author Patrik Novotný
 */
public class IOCreator extends JDialog {
    private boolean output;
    private boolean goal;

    /**
     * Constructs a new IOCreator and displays it to the user
     * @param main owner of the dialog
     */
    public IOCreator(App main){
        super(main,"Create output or input",true);
        super.setSize(800, 600);
        super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        super.setLocationRelativeTo(main);
        JPanel panel = new JPanel();
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
        outBox.addItemListener(e -> output = e.getStateChange() == ItemEvent.SELECTED);
        panel.add(outBox, tempConstraints);

        tempConstraints.gridx = 1;
        JComboBox<String> items = new JComboBox<>(RecipeCreator.items.toArray(new String[0]));
        panel.add(items, tempConstraints);

        tempConstraints.gridy = 1;
        JSpinner itemPS = new JSpinner(new SpinnerNumberModel(0,0,Integer.MAX_VALUE,0.1));
        itemPS.setEnabled(false);
        panel.add(itemPS, tempConstraints);

        tempConstraints.gridx = 0;
        JCheckBox goalBox = new JCheckBox("Goal?");
        goalBox.setToolTipText("Indicates if this is a main goal of the factory");
        goalBox.addItemListener((e) -> {
            goal = e.getStateChange() == ItemEvent.SELECTED;
            itemPS.setEnabled(goal);});
        panel.add(goalBox,tempConstraints);

        tempConstraints.gridy = 2;
        JButton save = new JButton("Save");
        save.addActionListener(e -> {
            main.addIO(new InOut(goal, output, items, itemPS));
            dispose();
        });
        panel.add(save, tempConstraints);

        tempConstraints.gridx = 1;
        JButton delete = new JButton("Delete");
        delete.addActionListener(e -> dispose());
        panel.add(delete, tempConstraints);

        super.setVisible(true);
    }
}
