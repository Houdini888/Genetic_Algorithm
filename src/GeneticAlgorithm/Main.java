package GeneticAlgorithm;

import Problems.KnapsackProblem;

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

        int[] solution = geneticAlgorithm.findSolution(5000);

        for(int gene : solution){
            System.out.print(gene);
        }



    }
}
