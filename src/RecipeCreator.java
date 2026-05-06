import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RecipeCreator extends JDialog {
    private final JPanel top;
    private final JPanel middle;
    private final JSpinner timeTaken = new JSpinner(new SpinnerNumberModel(1,0.01,Integer.MAX_VALUE,0.1));

    private final ArrayList<JSpinner> ingredientSpinnersPS = new ArrayList<>();
    private final ArrayList<JComboBox<String>> ingredientComboBoxesPS = new ArrayList<>();
    private final ArrayList<JComboBox<String>> ingredientComboBoxesTime = new ArrayList<>();
    private final ArrayList<JSpinner> ingredientSpinnersTime = new ArrayList<>();

    private final ArrayList<JSpinner> productSpinnersPS = new ArrayList<>();
    private final ArrayList<JComboBox<String>> productComboBoxesPS = new ArrayList<>();
    private final ArrayList<JSpinner> productSpinnersTime = new ArrayList<>();
    private final ArrayList<JComboBox<String>> productComboBoxesTime = new ArrayList<>();

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
        tempLabel = new JLabel("Item count");
        Customizer.blackComponent(tempLabel);
        middle.add(tempLabel, tempConstraints);
        tempConstraints.gridx = 1;
        tempLabel = new JLabel("Input item");
        Customizer.blackComponent(tempLabel);
        top.add(tempLabel,tempConstraints);
        tempLabel = new JLabel("Input item");
        Customizer.blackComponent(tempLabel);
        middle.add(tempLabel, tempConstraints);

        JButton addProduct = new JButton("Add Product");
        addProduct.addActionListener(e -> addProduct());
        bottom.add(addProduct);

        tempConstraints.gridx = 2;
        tempLabel = new JLabel("Products per second");
        Customizer.blackComponent(tempLabel);
        top.add(tempLabel,tempConstraints);
        tempConstraints.gridx = 3;
        tempLabel = new JLabel("Product item");
        Customizer.blackComponent(tempLabel);
        top.add(tempLabel,tempConstraints);

        tempLabel = new JLabel("Time taken");
        Customizer.blackComponent(tempLabel);
        middle.add(tempLabel, tempConstraints);

        tempConstraints.gridx = 4;
        tempLabel = new JLabel("Product count");
        Customizer.blackComponent(tempLabel);
        middle.add(tempLabel, tempConstraints);
        tempConstraints.gridx = 5;
        tempLabel = new JLabel("Product item");
        Customizer.blackComponent(tempLabel);
        middle.add(tempLabel, tempConstraints);

        tempConstraints.gridy = 2;
        tempConstraints.gridx = 3;
        middle.add(timeTaken, tempConstraints);
        timeTaken.addChangeListener(e -> {
            double length = (double) timeTaken.getValue();
            for (int i = 0; i < productSpinnersTime.size(); i++) {
                productSpinnersPS.get(i).setValue((double)productSpinnersTime.get(i).getValue() / length);
            }
            for (int i = 0; i < ingredientSpinnersTime.size(); i++) {
                ingredientSpinnersPS.get(i).setValue((double)ingredientSpinnersTime.get(i).getValue() / length);
            }
        });

        JButton delete = new JButton("Delete");
        delete.addActionListener(e -> super.dispose());
        bottom.add(delete);

        JButton save = new JButton("Save");
        save.addActionListener(e -> {
            //TODO finish saving recipes
            super.dispose();
        });
        bottom.add(save);

        addIngredient(); addProduct();

        super.setVisible(true);
    }

    private void addIngredient(){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = ingredientSpinnersPS.size() +2;
        constraints.gridx = 0;
        constraints.insets = new Insets(10,10,10,10);
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(0,0,Integer.MAX_VALUE,0.1));
        JSpinner spinner1 = new JSpinner(new SpinnerNumberModel(0,0,Integer.MAX_VALUE,0.1));
        top.add(spinner,constraints);
        middle.add(spinner1, constraints);
        ingredientSpinnersPS.add(spinner);
        ingredientSpinnersTime.add(spinner1);
        spinner.addChangeListener(e -> spinner1.setValue((Double)spinner.getValue() * (Double)timeTaken.getValue()));
        spinner1.addChangeListener(e -> spinner.setValue((Double)spinner1.getValue() / (Double)timeTaken.getValue()));

        constraints.gridx = 1;
        JComboBox<String> comboBox = new JComboBox<>(items.toArray(new String[0]));
        JComboBox<String> comboBox1 = new JComboBox<>(items.toArray(new String[0]));
        top.add(comboBox, constraints);
        middle.add(comboBox1, constraints);
        ingredientComboBoxesPS.add(comboBox);
        ingredientComboBoxesTime.add(comboBox1);
        comboBox1.addActionListener(e -> comboBox.setSelectedIndex(comboBox1.getSelectedIndex()));
        comboBox.addActionListener(e -> {if(comboBox1.getItemCount() > 0) comboBox1.setSelectedIndex(comboBox.getSelectedIndex());});

        super.validate();
    }

    public void addItem(String in){
        String item = in.trim();
        items.add(item);
        for (JComboBox<String> box : ingredientComboBoxesPS){
            box.addItem(item);
        }
        for (JComboBox<String> box : productComboBoxesPS){
            box.addItem(item);
        }
        for (JComboBox<String> box : ingredientComboBoxesTime){
            box.addItem(item);
        }
        for (JComboBox<String> box : productComboBoxesTime){
            box.addItem(item);
        }
    }

    public void addProduct(){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = productSpinnersPS.size() + 2;
        constraints.gridx = 2;
        constraints.insets = new Insets(10,10,10,10);
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(0,0,Integer.MAX_VALUE,0.1));
        top.add(spinner,constraints);
        productSpinnersPS.add(spinner);

        constraints.gridx = 3;
        JComboBox<String> comboBox = new JComboBox<>(items.toArray(new String[0]));
        top.add(comboBox, constraints);
        productComboBoxesPS.add(comboBox);

        constraints.gridx = 4;
        JSpinner spinner1 = new JSpinner(new SpinnerNumberModel(0,0,Integer.MAX_VALUE,0.1));
        middle.add(spinner1, constraints);
        productSpinnersTime.add(spinner1);

        constraints.gridx = 5;
        JComboBox<String> comboBox1 = new JComboBox<>(items.toArray(new String[0]));
        middle.add(comboBox1, constraints);
        productComboBoxesTime.add(comboBox1);

        comboBox1.addActionListener(e -> comboBox.setSelectedIndex(comboBox1.getSelectedIndex()));
        comboBox.addActionListener(e -> {if(comboBox1.getItemCount() > 0) comboBox1.setSelectedIndex(comboBox.getSelectedIndex());});
        spinner.addChangeListener(e -> spinner1.setValue((Double)spinner.getValue() * (Double)timeTaken.getValue()));
        spinner1.addChangeListener(e -> spinner.setValue((Double)spinner1.getValue() / (Double)timeTaken.getValue()));

        top.validate();
    }
}
