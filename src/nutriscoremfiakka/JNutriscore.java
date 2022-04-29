package nutriscoremfiakka;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author arthu
 */
public class JNutriscore extends JComponent {

    private NutriscoreModel model;

    private NutriscoreBasicUI ui;

    public JNutriscore() {
        int valueA = 0;
        model = new DefaultNutriscoreModel(valueA);
        ui = new NutriscoreBasicUI(model);
    }

    public NutriscoreModel getModel() {
        return model;
    }

    public void setModel(NutriscoreModel newModel) {
        model = newModel;
    }

    @Override
    public void paint(Graphics g) {
        ui.paint(g, this);
    }

    @Override
    public Dimension getPreferredSize() {
        return ui.getPreferredSize();
    }

    public int getValue() {
        return model.getValue();
    }

    public void setValue(int newVal) {
        model.setValue(newVal);
        repaint();
    }

    public void addNutriscoreModelListener(NutriscoreModelListener nml) {

    }

    public void removeNutriscoreModelListener(NutriscoreModelListener nml) {

    }
}
