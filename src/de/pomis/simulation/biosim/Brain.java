package de.pomis.simulation.biosim;

import java.util.*;

/*
  A brain is a connectome. It is the description of connections between sensors and interneurons, sensors and actors,
  interneurons with interneurons or interneurons with actors.
 */
public class Brain {

    private final Entity entity;

    private final List<Sensor> sensors;
    private final List<Interneuron> interneurons;
    private final List<Actor> actors;

    private final List<Connection> connections = new ArrayList<>();

    private Configuration configuration;

    public Brain(Entity entity, Configuration configuration) {
        this.configuration = configuration;
        this.entity = entity;

        sensors = entity.getSensorList();
        interneurons = new ArrayList<>(configuration.getMaxNumberOfInterneurons());
        for (int i = 0; i < configuration.getMaxNumberOfInterneurons(); i++) {
            interneurons.add(new Interneuron());
        }
        actors = entity.getActorList();

        initRandomly();
    }

    private void initRandomly() {
        // each neuron has only one output, here we store all neurons whose output has already been used as a source
        // all sensors and interneurons are possible sources
        List<INeuron> possibleSources = new ArrayList<>();
        possibleSources.addAll(entity.getSensorList());
        possibleSources.addAll(interneurons);

        // all interneurons and actors are possible targets
        List<INeuron> possibleTargets = new ArrayList<>();
        possibleTargets.addAll(interneurons);
        possibleTargets.addAll(entity.getActorList());

        for (int i = 0; i < configuration.getMaxNumberOfConnections(); i++) {
            // find a source neuron that has not yet been used as a source
            INeuron source = possibleSources.get(Configuration.RANDOM.nextInt(possibleSources.size()));
            possibleSources.remove(source);

            // neurons can have multiple inputs, though; just choose a random one
            INeuron target = possibleTargets.get(Configuration.RANDOM.nextInt(possibleTargets.size()));

            Connection connection = new Connection(
                    source.getNeuronType(), source,
                    target.getNeuronType(), target,
                    Configuration.RANDOM.nextInt(configuration.getMaxWeight())
            );

            connections.add(connection);
        }
    }

    public void simulate() {
        // calculate sensor values
        entity.getSensorList().forEach(Sensor::evaluate);

        // calculate interneuron values
//        connections.forEach(connection -> {
//            if (connection.getTargetType() == Neuron.Type.INTERNEURON) {
//                int sum = connection.getSource().getValue();
//            }
//        });

        interneurons.forEach(interneuron -> {
            double sum = connections.stream()
                    .filter(connection -> connection.getTarget() == interneuron)
                    .mapToDouble(connection -> connection.getSource().getActiveValue() * connection.getWeight())
                    .sum();
            interneuron.setValue((float)sum);
        });

        // calculate actor values

    }
}
