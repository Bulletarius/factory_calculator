import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    private JMenu recipesMenu;

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


        super.setVisible(true);
    }
}
