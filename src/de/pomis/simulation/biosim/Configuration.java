package de.pomis.simulation.biosim;

import java.util.Objects;
import java.util.Random;

public class Configuration {

    public static final Random RANDOM = new Random();

//    public static final int MAX_NUMBER_OF_INTERNEURONS = 2;
//    public static final int MAX_NUMBER_OF_CONNECTIONS = 16;
//
//    public static final int MAX_WORLD_WIDTH = 128;
//    public static final int MAX_WORLD_HEIGHT = 128;
//
////    public static final int MAX_WORLD_WIDTH = 4;
////    public static final int MAX_WORLD_HEIGHT = 4;
//
//    public static final World WORLD = new World();

    private int maxNumberOfInterneurons = 2;
    private int maxNumberOfConnections = 16;
    private int maxWorldWidth = 128;
    private int maxWorldHeight = 128;
    private int maxNumberOfEntities = 1024;
    private int maxWeight = 16;

    private int stepsPerGeneration = 300;
    private int generations = 100;

    public Configuration() {
    }

    public int getMaxNumberOfInterneurons() {
        return maxNumberOfInterneurons;
    }

    public void setMaxNumberOfInterneurons(int maxNumberOfInterneurons) {
        this.maxNumberOfInterneurons = maxNumberOfInterneurons;
    }

    public int getMaxNumberOfConnections() {
        return maxNumberOfConnections;
    }

    public void setMaxNumberOfConnections(int maxNumberOfConnections) {
        this.maxNumberOfConnections = maxNumberOfConnections;
    }

    public int getMaxWorldWidth() {
        return maxWorldWidth;
    }

    public void setMaxWorldWidth(int maxWorldWidth) {
        this.maxWorldWidth = maxWorldWidth;
    }

    public int getMaxWorldHeight() {
        return maxWorldHeight;
    }

    public void setMaxWorldHeight(int maxWorldHeight) {
        this.maxWorldHeight = maxWorldHeight;
    }

    public int getMaxNumberOfEntities() {
        return maxNumberOfEntities;
    }

    public void setMaxNumberOfEntities(int maxNumberOfEntities) {
        this.maxNumberOfEntities = maxNumberOfEntities;
    }

    public int getStepsPerGeneration() {
        return stepsPerGeneration;
    }

    public void setStepsPerGeneration(int stepsPerGeneration) {
        this.stepsPerGeneration = stepsPerGeneration;
    }

    public int getGenerations() {
        return generations;
    }

    public void setGenerations(int generations) {
        this.generations = generations;
    }

    public int getMaxWeight() {
        return maxWeight;
    }
}
