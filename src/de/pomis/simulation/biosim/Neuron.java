package de.pomis.simulation.biosim;

public abstract class Neuron implements INeuron {

    protected Configuration configuration;
    protected float threshold;
    protected float value;

    public Neuron(Configuration configuration) {
        this.configuration = configuration;
        this.threshold = Configuration.RANDOM.nextFloat(configuration.getMaxNumberOfConnections() * configuration.getMaxWeight());
    }

    public float getThreshold() {
        return threshold;
    }

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public boolean isActive() {
        return value >= threshold;
    }

    @Override
    public float getActiveValue() {
        return isActive() ? value : 0;
    }
}
