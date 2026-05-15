import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    private final JMenu recipesMenu;
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

        main = new JPanel(new GridBagLayout());
        JScrollPane scroll = new JScrollPane(main);
        Customizer.greyBackground(main);
        super.add(scroll, BorderLayout.CENTER);

        IOMenu = new JMenu("Input/Output");
        Customizer.blackComponent(IOMenu);
        menus.add(IOMenu);
        JMenuItem addIO = new JMenuItem("Add Input or Output");
        IOMenu.add(addIO);
        addIO.addActionListener(_ -> new IOCreator(this));

        super.setVisible(true);
    }
}
