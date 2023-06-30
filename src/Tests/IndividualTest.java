package Tests;

import Problems.KnapsackProblem;
import org.junit.jupiter.api.Test;

import GeneticAlgorithm.Individual;

import java.util.Random;

class IndividualTest {

    public void printGenotype(int[] genotype){
        for(int gene : genotype){
            System.out.print(gene);
        }
    }

    @Test
    public void testConstruction(){
        Individual individual = new Individual(5);
        int[] genotype = individual.getGenotype();

        for(int gene : genotype){
            System.out.print(gene);
        }
    }

    @Test
    public void testMutation(){
        Individual individual = new Individual(10);
        printGenotype(individual.getGenotype());

        System.out.println();

        individual.mutation(0.2f);
        printGenotype(individual.getGenotype());

    }

    @Test
    public void testRandomInt(){
        Individual individual = new Individual(4);

        Random generator = new Random();
        //int crossingPoint = generator.nextInt(0,individual.getGenotype().length-2);

        for(int i = 0; i < 100; i++){
            int crossingPoint = generator.nextInt(0,individual.getGenotype().length-1);
            System.out.print(crossingPoint + " ");
        }
    }

    @Test
    public void testCrossing(){
        Individual parent1 = new Individual(1);
        Individual parent2 = new Individual(1);

        printGenotype(parent1.getGenotype());
        System.out.println();
        printGenotype(parent2.getGenotype());
        System.out.println();
        System.out.println();

        Individual[] children = parent1.cross(parent2);

    }

    @Test
    public void testEvaluation(){
        Individual individual = new Individual(5);
        KnapsackProblem knapsackProblem = new KnapsackProblem("E:\\Projects\\School projects\\Genetic Algorithm Knapsack Problem\\src\\Tests\\test.txt", 10);

        individual.evaluate(knapsackProblem);

        individual.printGenotype();
        System.out.println();
        System.out.println(individual.getFitness());
    }

}