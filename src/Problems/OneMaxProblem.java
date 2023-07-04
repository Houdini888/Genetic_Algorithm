package Problems;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OneMaxProblem extends Problem {

    public static class OneMaxItem extends Problem.Item{

    }

    private ArrayList<OneMaxItem> itemList;

    public OneMaxProblem(String filename) {
        itemList = new ArrayList<>();
        loadFromFile(filename);
    }

    @Override
    public ArrayList<? extends Item> getItemList() {
        return itemList;
    }

    @Override
    public void loadFromFile(String filename) {
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line = reader.readLine();
            String[] parts = line.split(" ");
            if(parts.length == 2){

                int genesNr = Integer.parseInt(parts[1]);

                for(int i = 0; i < genesNr; i++){
                    itemList.add(new OneMaxItem());
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public double evaluateGenotype(int[] genotype) {

        int ones = 0;
        for(int gene : genotype){
            if(gene == 1){
                ones++;
            }
        }

        return ones;
    }



}
