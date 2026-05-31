import javax.swing.*;
import java.awt.*;

public class FactoryNode extends JPanel {
    /**
     * 0 for recipe, 1 for input, 2 for output.
     */
    private final int type;

    public FactoryNode(int type){
        super(new GridBagLayout());
        if (type > 2) throw new IllegalArgumentException("Type can not be more than two");
        this.type = type;
    }

}
