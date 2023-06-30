package GeneticAlgorithm;

import Problems.KnapsackProblem;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithm {
    private KnapsackProblem knapsackProblem;

    private int popSize;
    private float mutProb;
    private float crossProb;

    private ArrayList<Individual> individuals;


    public GeneticAlgorithm(KnapsackProblem knapsackProblem, int popSize, float mutProb, float crossProb){
        if(popSize < 0 || mutProb < 0 || crossProb < 0){
            throw new InvalidParameterException("Invalid algorithm parameters");
        }

        this.knapsackProblem = knapsackProblem;
        this.popSize = popSize;
        this.mutProb = mutProb;
        this.crossProb = crossProb;
        this.individuals = new ArrayList<Individual>();

        int genesNr = knapsackProblem.getItemList().size();
        for(int i = 0; i < popSize; i++){
            individuals.add(new Individual(genesNr));
        }
    }

    public KnapsackProblem getKnapsackProblem() {
        return knapsackProblem;
    }

    public int getPopSize() {
        return popSize;
    }

    public float getMutProb() {
        return mutProb;
    }

    public float getCrossProb() {
        return crossProb;
    }

    public ArrayList<Individual> getIndividuals() {
        return individuals;
    }

    public int[] findSolution(int repeats){
        if(repeats <= 0){
            throw new InvalidParameterException("Invalid number of iterations");
        }

        for(int i = 0; i < repeats; i++){
            evaluateIndividuals();
            crossIndividuals();
            mutateIndividuals();
        }
        evaluateIndividuals();

        Individual bestIndividual = findHighestFitnessIndividual();

        return bestIndividual.getGenotype();
    }

    public Individual findHighestFitnessIndividual(){

        Individual bestIndividual = individuals.get(0);
        for(Individual individual : individuals){
            if(individual.getFitness() > bestIndividual.getFitness()){
                bestIndividual = individual;
            }
        }
        return bestIndividual;
    }

    public void evaluateIndividuals(){
        for(int i = 0; i < individuals.size(); i++){
            individuals.get(i).evaluate(knapsackProblem);
        }
    }

    public Individual chooseParent(){
        Random generator = new Random();

        Individual candidate1 = individuals.get(generator.nextInt(individuals.size()));
        Individual candidate2 = individuals.get(generator.nextInt(individuals.size()));

        if(candidate1.getFitness() >= candidate1.getFitness()){
            return candidate1;
        }else{
            return candidate2;
        }
    }

    public void crossIndividuals(){
        Random generator = new Random();

        Individual parent1 = chooseParent();
        Individual parent2 = chooseParent();

        if(generator.nextFloat() < crossProb){
            // at [0] child 1; at [1] child 2
            Individual[] children = parent1.cross(parent2);

            int parent1Index = individuals.indexOf(parent1);
            int parent2Index = individuals.indexOf(parent2);

            individuals.set(parent1Index, children[0]);
            individuals.set(parent2Index, children[1]);
        }
    }

    public void mutateIndividuals(){
        for(Individual individual: individuals){
            individual.mutation(mutProb);
        }
    }




    //for testing purposes

    public void printGenomes(){
        for(Individual individual : individuals){
            individual.printGenotype();
            System.out.print(" ");
        }
        System.out.println();
    }

    public void printFitnesses(){
        for(Individual individual : individuals){
            System.out.print(individual.getFitness() + " ");
        }
        System.out.println();
    }

    public void printGenomesAndFitness(){
        for(Individual individual : individuals){
            individual.printGenotypeAndFitness();
        }
        System.out.println();
    }
}
