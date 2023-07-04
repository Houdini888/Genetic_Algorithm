package Individuals;

import Problems.KnapsackProblem;
import Problems.Problem;

import java.security.InvalidParameterException;
import java.util.Random;

public class Individual {
    //genotype of an individual represented as arraylist of 1s and 0s
    private final int[] genotype;
    //fitness of individual, calculated by sum value of genes when weight <= limit, otherwise 0
    private double fitness;

    //constructor creating a random genotype of given size
    public Individual(int genotypeSize){

        if(genotypeSize < 0){
            throw new ArithmeticException("Negative genotype size");
        }

        fitness = 0;
        genotype = new int[genotypeSize];
        Random generator = new Random();

        for(int i = 0; i < genotypeSize; i++){
            genotype[i] = generator.nextInt(2);
        }
    }

    //constructor receiving genotype
    public Individual(int[] genotype){
        fitness = 0;
        this.genotype = genotype;
    }

    public int[] getGenotype() {
        return genotype;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    //function for mutating genes in individual's genome based on given probabiliy
    public void mutation(float mutProb){

        if(mutProb < 0 || mutProb > 1){
            throw new ArithmeticException("Mutation probability out of range");
        }

        Random generator = new Random();
        for(int i = 0; i < genotype.length; i++){

            if(generator.nextFloat() < mutProb){

                if(genotype[i] == 0){
                    genotype[i] = 1;
                }else{
                    genotype[i] = 0;
                }
            }
        }
    }

    //function for crossing 2 individuals by splitting their genomes on a randomized point and
    //switching genome parts to create 2 child Individuals
    public Individual[] cross(Individual other){

        if(genotype.length != other.getGenotype().length){
            throw new InvalidParameterException("The genomes have different length");
        }

        int[] child1Genotype = new int[genotype.length];
        int[] child2Genotype = new int[genotype.length];

        Random generator = new Random();

        //bound of first split part (from 0 to genotype.length-2)
        int crossingPoint = generator.nextInt(0, genotype.length-1);
        //lenght of second part: from crossingPoint+1 to end of genotype
        int secondPartLength = (genotype.length-1) - crossingPoint;

        //copy the first part of this. individual into child 1
        System.arraycopy(genotype, 0, child1Genotype, 0, crossingPoint+1);
        //copy the second part of other individual into child 1
        System.arraycopy(other.getGenotype(), crossingPoint+1, child1Genotype, crossingPoint+1, secondPartLength);

        //copy the first part of other individual into child 2
        System.arraycopy(other.getGenotype(), 0, child2Genotype, 0, crossingPoint+1);
        //copy the second part of this. individual into child 2
        System.arraycopy(genotype, crossingPoint+1, child2Genotype, crossingPoint+1, secondPartLength);

        Individual child1 = new Individual(child1Genotype);
        Individual child2 = new Individual(child2Genotype);

        return new Individual[]{child1, child2};

    }

    public void evaluate(Problem problem){
        fitness = problem.evaluateGenotype(genotype);
    }

    public void printGenotype(int[] genotype){
        for(int gene : genotype){
            System.out.print(gene);
        }
    }

    public void printGenotype(){
        for(int gene : genotype){
            System.out.print(gene);
        }
    }

    public void printGenotypeAndFitness(){
        printGenotype();
        System.out.print(" " + fitness);
        System.out.println();
    }

}
