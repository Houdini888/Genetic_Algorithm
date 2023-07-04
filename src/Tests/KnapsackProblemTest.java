package Tests;

import Individuals.Individual;
import Problems.KnapsackProblem;
import Problems.Problem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class KnapsackProblemTest {

//    @Test
//    public void testLoadFromFile(){
//        KnapsackProblem knapsackProblem = new KnapsackProblem("E:\\Projects\\School projects\\Genetic Algorithm Knapsack Problem\\src\\Tests\\test.txt", 10);
//
//        for(){
//
//            System.out.println(item.getValue() + "," + item.getWeight());
//        }
//
//        Assertions.assertNotNull(knapsackProblem.getItemList());
//        Assertions.assertEquals(knapsackProblem.getItemList().size(), 5);
//    }

    @Test
    public void testEvaluateGenotype(){
        KnapsackProblem knapsackProblem = new KnapsackProblem("E:\\Projects\\School projects\\Genetic Algorithm Knapsack Problem\\src\\Tests\\test.txt", 10);
        Individual individual = new Individual(5);

        individual.printGenotype();
        System.out.println();

        individual.setFitness(knapsackProblem.evaluateGenotype(individual.getGenotype()));
        System.out.println(individual.getFitness());
    }
}