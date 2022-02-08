package de.pomis.simulation.biosim;

/*
  This is the description of a connection between a sensor and an interneuron, a sensor and an actor, an
  interneuron with another interneuron or an interneuron with an actor. It also has a weight.
 */
public class Connection {

    private final INeuron.Type sourceType;
    private final INeuron source;
    private final INeuron.Type targetType;
    private final INeuron target;
    private final int weight;

    public Connection(INeuron.Type sourceType, INeuron source, INeuron.Type targetType, INeuron target, int weight) {
        this.sourceType = sourceType;
        this.source = source;
        this.targetType = targetType;
        this.target = target;
        this.weight = weight;
    }

    public INeuron.Type getSourceType() {
        return sourceType;
    }

    public INeuron getSource() {
        return source;
    }

    public INeuron.Type getTargetType() {
        return targetType;
    }

    public INeuron getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }
}
