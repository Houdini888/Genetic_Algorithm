package Tests;

import GeneticAlgorithm.GeneticAlgorithm;
import Problems.KnapsackProblem;
import GeneticAlgorithm.Individual;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class GeneticAlgorithmTest {

    KnapsackProblem knapsackProblem = new KnapsackProblem("E:\\Projects\\School projects\\Genetic Algorithm Knapsack Problem\\src\\Tests\\test.txt", 10);


    @Test
    public void testConstructor(){
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(knapsackProblem, 100, 0.2f, 0.5f);

        for(Individual individual : geneticAlgorithm.getIndividuals()){
            individual.printGenotype();
            System.out.println();
        }

    }

    @Test
    public void testObjectReferences(){
        ArrayList<Individual> individuals = new ArrayList<Individual>();

        Individual individual1 = new Individual(5);
        Individual individual2 = new Individual(5);


        individual1.printGenotype();
        System.out.println();
        individual2.printGenotype();
        System.out.println();

        individuals.add(individual1);
        individuals.add(individual2);

        Individual individual3 = new Individual(10);

        int index = individuals.indexOf(individual1);
        individuals.set(index, individual3);

        for(Individual individual : individuals){
            individual.printGenotype();
            System.out.println();
        }
    }

    @Test
    public void testCrossIndividuals(){
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(knapsackProblem, 10, 0.2f, 0.99f);

        geneticAlgorithm.printGenomes();

        geneticAlgorithm.evaluateIndividuals();
        geneticAlgorithm.crossIndividuals();

        geneticAlgorithm.printGenomes();

    }

    @Test
    public void testMutation(){
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(knapsackProblem, 10, 1f, 0.5f);

        geneticAlgorithm.printGenomes();

        geneticAlgorithm.mutateIndividuals();

        geneticAlgorithm.printGenomes();
    }

    @Test
    public void testFindBestIndividual(){
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(knapsackProblem, 5, 1f, 0.5f);
        geneticAlgorithm.evaluateIndividuals();

        geneticAlgorithm.printGenomesAndFitness();

        Individual bestIndividual = geneticAlgorithm.findHighestFitnessIndividual();
        bestIndividual.printGenotypeAndFitness();
    }

}