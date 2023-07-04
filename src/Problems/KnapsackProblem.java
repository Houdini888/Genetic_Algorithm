package Problems;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class KnapsackProblem extends Problem {

    public static class KnapsackItem extends Problem.Item{
        private final double value;
        private final double weight;

        public KnapsackItem(double value, double weight){
            this.value = value;
            this.weight = weight;
        }

        public double getValue() {
            return value;
        }

        public double getWeight() {
            return weight;
        }
    }

    private final ArrayList<KnapsackItem> itemList;
    double backpackSize;

    public KnapsackProblem(String filename, double backpackSize){
        if(backpackSize <= 0){
            throw new InvalidParameterException("Backpack size is negative or zero");
        }
        itemList = new ArrayList<>();
        loadFromFile(filename);
        this.backpackSize = backpackSize;
    }

    @Override
    public ArrayList<KnapsackItem> getItemList() {
        return itemList;
    }

    public double getBackpackSize() {
        return backpackSize;
    }

    @Override
    public void loadFromFile(String filename){
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while( (line = reader.readLine()) != null){

                String[] parts = line.split(",");
                if(parts.length == 2){

                    double value = Double.parseDouble(parts[0]);
                    double weight = Double.parseDouble(parts[1]);

                    itemList.add(new KnapsackItem(value, weight));
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public double evaluateGenotype(int[] genotype){
        if(genotype.length != getItemList().size()){
            throw new InvalidParameterException("The number of genes does not match list of items");
        }

        double totalValue = 0;
        double totalWeight = 0;
        for(int i = 0; i < genotype.length; i++){
            if(genotype[i] == 1){

                totalValue += itemList.get(i).value;
                totalWeight += itemList.get(i).weight;
            }
        }

        double fitness;
        if(totalWeight <= backpackSize){
            fitness = totalValue;
        }else{
            fitness = 0;
        }
        return fitness;
    }

}
