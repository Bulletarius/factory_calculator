import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Just a basic class to represent a recipe
 * @author Patrik Novotný
 */
public class Recipe {
    private final String name;
    private final LinkedHashMap<String,Double> ingredients = new LinkedHashMap<>();
    private final LinkedHashMap<String,Double> products = new LinkedHashMap<>();
    public boolean calculated;

    /**
     * Constructs a new recipe from the information in the components
     * @param ingredients Array List of JCombo boxes to take the ingredients from
     * @param products Array List of JCombo boxes to take the products from
     * @param ingredientAmount Array List of JSpinners to take the amount of ingredients from
     * @param productAmount Array List of JSpinners to take the amount of products from
     * @param name name of the recipe / building
     */
    public Recipe(ArrayList<JComboBox<String>> ingredients, ArrayList<JComboBox<String>> products,
                  ArrayList<JSpinner> ingredientAmount, ArrayList<JSpinner> productAmount, String name){
        this.name = name;
        for (int i = 0; i < ingredients.size(); i++) {
            this.ingredients.put((String) ingredients.get(i).getSelectedItem(),(double) ingredientAmount.get(i).getValue());
        }
        for (int i = 0; i < products.size(); i++) {
            this.products.put((String) products.get(i).getSelectedItem(),(double) productAmount.get(i).getValue());
        }
    }

    public String getName() {return name;}

    public LinkedHashMap<String, Double> getIngredients() {return ingredients;}

    public LinkedHashMap<String, Double> getProducts() {return products;}
}
