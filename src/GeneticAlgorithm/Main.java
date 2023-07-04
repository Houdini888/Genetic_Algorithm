package GeneticAlgorithm;

import Problems.KnapsackProblem;
import Problems.OneMaxProblem;

public class Main {

    public static void main(String[] args){
        KnapsackProblem knapsackProblem = new KnapsackProblem(
                "E:\\Projects\\School projects\\Genetic Algorithm Knapsack Problem\\src\\GeneticAlgorithm\\kp.txt",
                10);

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(knapsackProblem,
                200,
                0.1f,
                0.5f
        );

        int[] KPsolution = geneticAlgorithm.findSolution(5000);

        for(int gene : KPsolution){
            System.out.print(gene);
        }
        System.out.println();

        OneMaxProblem oneMaxProblem = new OneMaxProblem("E:\\Projects\\School projects\\Genetic Algorithm Knapsack Problem\\src\\GeneticAlgorithm\\om.txt");
        geneticAlgorithm = new GeneticAlgorithm(oneMaxProblem,
                200,
                0.1f,
                0.5f
        );

        int[] OMsolution = geneticAlgorithm.findSolution(1000);

        for(int gene : OMsolution){
            System.out.print(gene);
        }







    }
}
