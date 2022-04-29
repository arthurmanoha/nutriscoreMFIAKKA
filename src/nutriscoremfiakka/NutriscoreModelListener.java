package nutriscoremfiakka;

import java.util.EventListener;

/**
 *
 * @author arthu
 */
public interface NutriscoreModelListener extends EventListener {

    public void valueChanged(int oldValue, int newValue);
}
