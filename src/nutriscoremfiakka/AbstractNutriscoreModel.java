package nutriscoremfiakka;

import java.util.ArrayList;

/**
 *
 * @author arthu
 */
public abstract class AbstractNutriscoreModel implements NutriscoreModel {

    protected ArrayList<NutriscoreModelListener> listeners;

    public AbstractNutriscoreModel() {
        listeners = new ArrayList<>();
    }

    public void addNutriscoreModelListener(NutriscoreModelListener newListener) {
    }

    public void removeNutriscoreModelListener() {

    }

    public void addModelListener(NutriscoreModelListener nml) {
        if (!listeners.contains(nml)) {
            listeners.add(nml);
        }
    }

}
