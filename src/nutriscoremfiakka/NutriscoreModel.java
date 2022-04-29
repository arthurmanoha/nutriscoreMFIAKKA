package nutriscoremfiakka;

/**
 *
 * @author arthu
 */
public interface NutriscoreModel {

    public int getValue();

    public void setValue(int newVal);

    public void addModelListener(NutriscoreModelListener nml);
}
