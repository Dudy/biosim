package de.pomis.simulation.biosim;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;

public class Entity {

    // each entity has one sensor per type, we store them in a map and as list for easier access in different cases
    //protected final EnumMap<Sensor.Type, Sensor> sensorMap;
    private final List<Sensor> sensorList;

    // each entity has one actor per type, we store them an map and as list for easier access in different cases
    private final EnumMap<Actor.Type, Actor> actorMap;
    private final List<Actor> actorList;

    // contains interneurons and connections between all neurons (i.e. sensors, actors and interneurons)
    private final Brain brain;

    private Node node;

    public Entity(boolean isAWall, Node node, Configuration configuration) {
        if (isAWall) {
//            sensorMap = null;
            sensorList= null;
            actorMap = null;
            actorList = null;
            brain = null;
        } else {
//            sensorMap = new EnumMap<>(Sensor.Type.class);
//            for (Sensor.Type sensorType : Sensor.Type.values()) {
//                sensorMap.put(sensorType, new Sensor(sensorType));
//            }
//            sensorList = new ArrayList<>(sensorMap.values());
//
//            actorMap = new EnumMap<>(Actor.Type.class);
//            for (Actor.Type actorType : Actor.Type.values()) {
//                actorMap.put(actorType, new Actor(actorType));
//            }
//            actorList = new ArrayList<>(actorMap.values());
//
//            brain = new Brain(this, configuration);



            sensorList = new ArrayList<>(Sensor.Type.values().length);
            for (Sensor.Type sensorType : Sensor.Type.values()) {
                sensorList.add(new Sensor(configuration, sensorType, this));
            }




            actorMap = new EnumMap<>(Actor.Type.class);
            for (Actor.Type actorType : Actor.Type.values()) {
                actorMap.put(actorType, new Actor(actorType));
            }
            actorList = new ArrayList<>(actorMap.values());

            brain = new Brain(this, configuration);
        }

        this.node = node;
    }

//    public EnumMap<Sensor.Type, Sensor> getSensorMap() {
//        return sensorMap;
//    }

    public List<Sensor> getSensorList() {
        return sensorList;
    }

    public EnumMap<Actor.Type, Actor> getActorMap() {
        return actorMap;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public Brain getBrain() {
        return brain;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void simulate() {
        //System.out.println("simulate " + hashCode());

        brain.simulate();
    }
}
