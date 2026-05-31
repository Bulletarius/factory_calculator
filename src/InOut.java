import javax.swing.*;

public class InOut {
    private final boolean output;
    private final String item;
    private final double countPS;
    private final boolean goal;

    public InOut(boolean goal, boolean output, JComboBox<String> item, JSpinner countPS){
        this.goal = goal;
        this.output = output;
        this.countPS = (double) countPS.getValue();
        this.item = (String) item.getSelectedItem();
    }

    public String getItem() {return item;}

    public boolean isOutput() {return output;}

    public double getCountPS() {return countPS;}

    public boolean isGoal() {return goal;}
}
