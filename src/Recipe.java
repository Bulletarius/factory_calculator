import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Recipe {
    private final String name;
    private final LinkedHashMap<String,Double> ingredients = new LinkedHashMap<>();
    private final LinkedHashMap<String,Double> products = new LinkedHashMap<>();
    public boolean calculated;

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
