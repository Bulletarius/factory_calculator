import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class FactoryNode extends JPanel {
    /**
     * 0 for recipe, 1 for input, 2 for output.
     */
    private final int type;

    public FactoryNode(int type, String name, LinkedHashMap<String,Double> ingredients,LinkedHashMap<String,Double> products ){
        super(new GridBagLayout());
        if (type > 2) throw new IllegalArgumentException("Type can not be more than two");
        this.type = type;
        switch (type){
            case 0 : super.setBackground(Color.gray);
            case 1 : super.setBackground(Color.orange);
            case 2 : super.setBackground(Color.blue);
        }

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5,5,5,5);
        constraints.weighty = 1;
        constraints.weightx = 1;
        products.forEach((s,d)->{
            JLabel label = new JLabel(s + ":" + d+ "per second");
            super.add(label,constraints);
            constraints.gridx++;
        });
        constraints.gridy = 1;
        constraints.gridx = 0;
        super.add(new JLabel(name),constraints);

        constraints.gridy = 2;
        ingredients.forEach((s,d)->{
            JLabel label = new JLabel(s + ": " + d + "per second");
            super.add(label,constraints);
            constraints.gridx++;
        });
    }

}
