package de.pomis.simulation.biosim;

/*
  A general type of neuron.
*/
public interface INeuron {

    enum Type { SENSOR, INTERNEURON, ACTOR }

    INeuron.Type getNeuronType();
    boolean isActive();
    float getActiveValue();
    float getValue();
    void setValue(float value);

}
