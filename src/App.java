import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class App extends JFrame implements Runnable{
    private final JMenu recipesMenu;
    private final ArrayList<Recipe> recipes = new ArrayList<>();
    private final ArrayList<InOut> inputsOutputs = new ArrayList<>();
    private final JPanel main;
    private final JMenu IOMenu;

    public App(){
        super("Factory Calculator");
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setExtendedState(JFrame.MAXIMIZED_BOTH);
        super.setSize(800,800);

        JMenuBar menus = new JMenuBar();
        menus.setBackground(Color.black);
        super.setJMenuBar(menus);
        recipesMenu = new JMenu("Recipes");
        recipesMenu.setForeground(Color.white);
        menus.add(recipesMenu);
        JMenuItem addRecept = new JMenuItem("Add Recept");
        recipesMenu.add(addRecept);
        addRecept.addActionListener(e -> new RecipeCreator(this));
        recipesMenu.addSeparator();

        main = new JPanel(new GridBagLayout());
        JScrollPane scroll = new JScrollPane(main);
        main.setBackground(new Color(0x222222));
        super.add(scroll, BorderLayout.CENTER);

        IOMenu = new JMenu("Input/Output");
        Customizer.blackComponent(IOMenu);
        menus.add(IOMenu);
        JMenuItem addIO = new JMenuItem("Add Input or Output");
        IOMenu.add(addIO);
        addIO.addActionListener(e -> new IOCreator(this));
        IOMenu.addSeparator();

        JMenu itemMenu = new JMenu("Items");
        Customizer.blackComponent(itemMenu);
        menus.add(itemMenu);
        JMenuItem removeItem = new JMenuItem("Remove an item");
        removeItem.addActionListener(e -> {
            if (RecipeCreator.items.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No item to remove exists", "Remove item", JOptionPane.ERROR_MESSAGE);
                return;
            }
            RecipeCreator.items.remove(JOptionPane.showInputDialog(this, "Choose what item to remove",
                    "Remove item", JOptionPane.QUESTION_MESSAGE, null, RecipeCreator.items.toArray(), RecipeCreator.items.get(0)));
        });
        itemMenu.add(removeItem);

        super.setVisible(true);
    }

    public void addRecipe(Recipe recipe){
        recipes.add(recipe);
        JMenuItem menuItem = new JMenuItem(recipe.getName());
        menuItem.addActionListener(e -> {
                if (JOptionPane.showConfirmDialog(this,"Are you sure you want to remove " + recipe.getName() + "?"
                        ,"Remove recipe", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                    recipesMenu.remove(menuItem);
                    recipes.remove(recipe);
                    main.removeAll();
                    SwingUtilities.invokeLater(this);
                }
        });
        recipesMenu.add(menuItem);
        main.removeAll();
        SwingUtilities.invokeLater(this);
    }

    public void addIO(InOut inOut){
        inputsOutputs.add(inOut);
        JMenuItem menuItem = new JMenuItem(inOut.getItem());
        menuItem.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this,"Are you sure you want to remove "+inOut.getItem()+" I/O?",
                    "Remove I/O", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0){
                IOMenu.remove(menuItem);
                inputsOutputs.remove(inOut);
                main.removeAll();
                SwingUtilities.invokeLater(this);
            }
        });
        IOMenu.add(menuItem);
        main.removeAll();
        SwingUtilities.invokeLater(this);
    }

    public void run(){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(20,20,20,20);
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.gridy = 0;
        constraints.gridx = 0;
        for (InOut output : inputsOutputs){
            if (output.isOutput() && output.isGoal()) {
                LinkedHashMap<String, Double> outputItem = new LinkedHashMap<>();
                outputItem.put(output.getItem(), output.getCountPS());
                main.add(new FactoryNode(2,output.getItem(),outputItem,new LinkedHashMap<>(0),1),constraints);
                constraints.gridy++;

                LinkedHashMap<Object,Double> tree = findStep(output.getItem(), new LinkedHashMap<>(), output.getCountPS());
                tree.forEach((o,d)-> {
                    if (o instanceof Recipe r){
                        main.add(new FactoryNode(0,r.getName()+" x "+d,r.getIngredients(),r.getProducts(),d),constraints);
                        constraints.gridy++;
                    }else if (o instanceof InOut i){
                        LinkedHashMap<String,Double> inputItem = new LinkedHashMap<>();
                        inputItem.put(i.getItem(),d);
                        main.add(new FactoryNode(1, i.getItem(), new LinkedHashMap<>(0),inputItem,1),constraints);
                        constraints.gridx++;
                    }
                });
            }
        }
        main.validate();
    }

    private LinkedHashMap<Object, Double> findStep(String item, LinkedHashMap<Object, Double> tree, double requirement){
        for (InOut input : inputsOutputs){
            if (!input.isOutput() && input.getItem().equals(item)){
                if (tree.containsKey(input)){
                    tree.replace(input,requirement + tree.get(input));
                    return tree;
                }
                tree.put(input,requirement);
                return tree;
            }
        }
        for (Recipe recipe: recipes){
            recipe.getProducts().forEach((s,d)-> {
                if (s.equals(item)){
                    double multiplier = requirement / d;
                    tree.put(recipe,multiplier);
                    recipe.getIngredients().forEach((s1,d1)-> findStep(s1,tree,d1*multiplier));
                }
            });
        }
        return tree;
    }
}
