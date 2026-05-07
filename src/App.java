import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class App extends JFrame {
    private final JMenu recipesMenu;
    private final ArrayList<Recipe> recipes = new ArrayList<>();

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

        super.setVisible(true);
    }

    public void addRecipe(Recipe recipe){
        recipes.add(recipe);
        JMenuItem menuItem = new JMenuItem(recipe.getName());
        recipesMenu.add(menuItem);
    }
}
