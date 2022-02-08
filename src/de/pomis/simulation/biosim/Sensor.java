package de.pomis.simulation.biosim;

//import java.util.EnumMap;

public class Sensor extends Neuron {

    public enum Type { DISTANCE_TO_LEFT, DISTANCE_TO_BOTTOM }

    private static final INeuron.Type NEURON_TYPE = INeuron.Type.SENSOR;

    private final Type type;
    private final Entity entity;

    public Sensor(Configuration configuration, Type type, Entity entity) {
        super(configuration);

        this.type = type;
        this.entity = entity;
    }

    @Override
    public INeuron.Type getNeuronType() {
        return NEURON_TYPE;
    }

    public Type getType() {
        return type;
    }

    public void evaluate() {
//        return switch (type) {
//            case DISTANCE_TO_LEFT -> entity.getNode().getCoordinates().getX();
//            case DISTANCE_TO_BOTTOM -> entity.getNode().getCoordinates().getY();
//        };

        value = switch (type) {
            case DISTANCE_TO_LEFT -> (float)entity.getNode().getCoordinates().getX() / configuration.getMaxWorldWidth();
            case DISTANCE_TO_BOTTOM -> (float)entity.getNode().getCoordinates().getY() / configuration.getMaxWorldHeight();
        };
    }

//    public static EnumMap<Type, Sensor> createSensorMap() {
//        EnumMap<Type, Sensor> sensorMap = new EnumMap<>(Type.class);
//        for (Sensor.Type sensorType : Sensor.Type.values()) {
//            sensorMap.put(sensorType, new Sensor(sensorType));
//        }
//        return sensorMap;
//    }
}
