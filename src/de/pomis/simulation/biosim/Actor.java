package de.pomis.simulation.biosim;

import java.util.EnumMap;
import java.util.Objects;

public class Actor implements INeuron {

    private static final INeuron.Type NEURON_TYPE = INeuron.Type.ACTOR;

    @Override
    public INeuron.Type getNeuronType() {
        return NEURON_TYPE;
    }

    public enum Type {
        MOVE_UP,
        MOVE_UP_AND_RIGHT,
        MOVE_RIGHT,
        MOVE_DOWN_AND_RIGHT,
        MOVE_DOWN,
        MOVE_DOWN_AND_LEFT,
        MOVE_LEFT,
        MOVE_UP_AND_LEFT
    }

    private final Type type;

    public Actor(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void execute(Entity entity) {
//        return switch (type) {
//            case DISTANCE_TO_LEFT -> entity.getCoordinates().getX();
//            case DISTANCE_TO_BOTTOM -> entity.getCoordinates().getY();
//        };
    }

    public static EnumMap<Actor.Type, Actor> createActorMap() {
        EnumMap<Type, Actor> actorMap = new EnumMap<>(Type.class);
        for (Type actorType : Type.values()) {
            actorMap.put(actorType, new Actor(actorType));
        }
        return actorMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return type == actor.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "Actor{" +
                "type=" + type +
                '}';
    }
}
