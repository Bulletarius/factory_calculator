import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;

/**
 * An <code>JPanel</code> that is constructed based on the parameters of the constructor and is ready to use
 * @author Patrik Novotný
 */
public class FactoryNode extends JPanel {
    private final int type;

    /**
     * Constructs a new JPanel ready to use
     * @param type 0 for recipe, 1 for input, 2 for output
     * @param name name of the node
     * @param ingredients map of the ingredients
     * @param products map of the products
     * @param multiplier amount of buildings with this recipe
     * @throws IllegalArgumentException if type is more than 2
     */
    public FactoryNode(int type, String name, LinkedHashMap<String,Double> ingredients,LinkedHashMap<String,Double> products,double multiplier){
        super(new GridBagLayout());
        if (type > 2) throw new IllegalArgumentException("Type can not be more than two");
        this.type = type;
        switch (type){
            case 0 : super.setBackground(Color.gray);
            break;
            case 1 : super.setBackground(Color.cyan);
            break;
            case 2 : super.setBackground(Color.orange);
            break;
        }

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5,5,5,5);
        constraints.weighty = 1;
        constraints.weightx = 1;
        products.forEach((s,d)->{
            JLabel label = new JLabel(s + ": " + d*multiplier + " per second total, " + d + " each");
            super.add(label,constraints);
            constraints.gridx++;
        });
        constraints.gridy = 1;
        constraints.gridx = 0;
        super.add(new JLabel(name),constraints);

        constraints.gridy = 2;
        ingredients.forEach((s,d)->{
            JLabel label = new JLabel(s + ": " + d*multiplier + " per second total, " + d + " each");
            super.add(label,constraints);
            constraints.gridx++;
        });
    }

}
