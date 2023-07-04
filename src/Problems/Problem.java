package Problems;

import java.util.ArrayList;

public abstract class Problem {

    public abstract static class Item{

    }

    private ArrayList<? extends Item> itemList;

    public abstract ArrayList<? extends Item> getItemList();

    public abstract void loadFromFile(String filename);

    public abstract double evaluateGenotype(int[] genotype);

}
