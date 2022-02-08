package de.pomis.simulation.biosim;

public class Simulation {

    public static void main(String[] args) {
        Simulation main = new Simulation();
        main.run();
    }

    public void run() {
        Configuration configuration = new Configuration();
        World world = new World(configuration);

        for (int generation = 0; generation < configuration.getGenerations(); generation++) {
            System.out.println("generation " + generation);
            for (int step = 0; step < configuration.getStepsPerGeneration(); step++) {
                System.out.println("step " + step);
                world.getEntities().forEach(Entity::simulate);
            }
        }
    }

}
