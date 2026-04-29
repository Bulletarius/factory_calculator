import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RecipeCreator extends JDialog {
    private final JPanel top;
    private final JPanel middle;

    private final ArrayList<JSpinner> ingredientSpinnersPS = new ArrayList<>();
    private final ArrayList<JComboBox<String>> ingredientComboBoxesPS = new ArrayList<>();

    private final ArrayList<JSpinner> productSpinnersPS = new ArrayList<>();
    private final ArrayList<JComboBox<String>> productComboBoxesPS = new ArrayList<>();

    public static final ArrayList<String> items = new ArrayList<>();

    public RecipeCreator(JFrame main){
        super(main,"Recipe Creator", true);
        super.setSize(1000,800);
        super.setLocationRelativeTo(main);
        super.setResizable(true);
        super.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        super.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        GridBagConstraints tempConstraints = new GridBagConstraints();
        tempConstraints.weighty = 2;
        tempConstraints.insets = new Insets(5,5,5,5);
        tempConstraints.anchor = GridBagConstraints.NORTHWEST;

        top = new JPanel();
        Customizer.greyBackground(top);
        JLabel topLabel = new JLabel("In items per second");
        topLabel.setForeground(Color.white);
        top.add(topLabel,tempConstraints);
        super.add(top);

        middle = new JPanel();
        Customizer.greyBackground(middle);
        JLabel bottomLabel = new JLabel("In item count and time taken");
        bottomLabel.setForeground(Color.white);
        middle.add(bottomLabel, tempConstraints);
        super.add(middle);

        JPanel bottom = new JPanel();
        Customizer.greyBackground(bottom);
        bottom.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        super.add(bottom);


        JButton addIngredient = new JButton("Add ingredient");
        addIngredient.addActionListener(e -> addIngredient());
        bottom.add(addIngredient);

        JTextField addItem = new JTextField(15);
        addItem.setToolTipText("Name of item to add");
        addItem.addActionListener(e -> {
            addItem(addItem.getText());
            addItem.setText("");
        });
        bottom.add(addItem);
        JButton addItemButton = new JButton("Add item");
        addItemButton.addActionListener(e -> {
            addItem(addItem.getText());
            addItem.setText("");
        });
        bottom.add(addItemButton);

        tempConstraints = new GridBagConstraints();
        tempConstraints.insets = new Insets(10,10,10,10);
        tempConstraints.gridy = 1;
        JLabel tempLabel = new JLabel("Inputs per second");
        Customizer.blackComponent(tempLabel);
        top.add(tempLabel, tempConstraints);
        tempConstraints.gridx = 1;
        tempLabel = new JLabel("Input item");
        Customizer.blackComponent(tempLabel);
        top.add(tempLabel,tempConstraints);

        JButton addProduct = new JButton("Add Product");
        addProduct.addActionListener(e -> addProduct());
        bottom.add(addProduct);

        tempConstraints.gridx = 2;
        tempConstraints.gridy = 1;
        tempLabel = new JLabel("Products per second");
        Customizer.blackComponent(tempLabel);
        top.add(tempLabel,tempConstraints);
        tempConstraints.gridx = 3;
        tempLabel = new JLabel("Product item");
        Customizer.blackComponent(tempLabel);
        top.add(tempLabel,tempConstraints);

        addIngredient(); addIngredient(); addProduct();

        super.setVisible(true);
    }

    private void addIngredient(){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = ingredientSpinnersPS.size() +2;
        constraints.gridx = 0;
        constraints.insets = new Insets(10,10,10,10);
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(0,0,Integer.MAX_VALUE,1));
        top.add(spinner,constraints);
        ingredientSpinnersPS.add(spinner);

        constraints.gridx = 1;
        JComboBox<String> comboBox = new JComboBox<>(items.toArray(new String[0]));
        top.add(comboBox, constraints);
        ingredientComboBoxesPS.add(comboBox);
        top.validate();
    }

    public void addItem(String item){
        items.add(item);
        for (JComboBox<String> box : ingredientComboBoxesPS){
            box.addItem(item);
        }
        for (JComboBox<String> box : productComboBoxesPS){
            box.addItem(item);
        }
    }

    public void addProduct(){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = productSpinnersPS.size() + 2;
        constraints.gridx = 2;
        constraints.insets = new Insets(10,10,10,10);
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(0,0,Integer.MAX_VALUE,1));
        top.add(spinner,constraints);
        productSpinnersPS.add(spinner);

        constraints.gridx = 3;
        JComboBox<String> comboBox = new JComboBox<>(items.toArray(new String[0]));
        top.add(comboBox, constraints);
        productComboBoxesPS.add(comboBox);
        top.validate();
    }
}
