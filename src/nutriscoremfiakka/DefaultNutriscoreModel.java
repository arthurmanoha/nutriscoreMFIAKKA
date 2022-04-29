package nutriscoremfiakka;

/**
 *
 * @author arthu
 */
public class DefaultNutriscoreModel extends AbstractNutriscoreModel {

    private int value; // 0 - 4, corresponding to A - E

    public DefaultNutriscoreModel(int newVal) {
        value = newVal;
    }

    public DefaultNutriscoreModel() {
        this(0);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int newVal) {
        if (newVal >= 0 && newVal <= 4) {
            int oldVal = value;
            value = newVal;
            // Tell the listeners
            firePropertyChanged(oldVal, newVal);
        }
    }

    private void firePropertyChanged(int oldVal, int newVal) {
        for (NutriscoreModelListener listener : listeners) {
            listener.notify();
        }
    }

}
