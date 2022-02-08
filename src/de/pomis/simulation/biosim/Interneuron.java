package de.pomis.simulation.biosim;

public class Interneuron extends Neuron {

    private static final INeuron.Type NEURON_TYPE = INeuron.Type.INTERNEURON;

    public Interneuron(float threshold) {
        super(threshold);
    }

    @Override
    public INeuron.Type getNeuronType() {
        return NEURON_TYPE;
    }
}
