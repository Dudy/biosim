package de.pomis.simulation.biosim;

public class Node {

    private Node upNeighbor;
    //private Node upRightNeighbor;
    private Node rightNeighbor;
    //private Node downrightNeighbor;
    private Node downNeighbor;
    //private Node downLeftNeighbor;
    private Node leftNeighbor;
    //private Node upLeftNeighbor;

    private Entity entity;
    private Coordinates coordinates;

    public Node(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Node getNeighbor(World.Direction direction) {
        return switch (direction) {
            case UP -> upNeighbor;
            // case UP_RIGHT -> upRightNeighbor;
            case UP_RIGHT -> upNeighbor.rightNeighbor;
            case RIGHT -> rightNeighbor;
            // case DOWN_RIGHT -> downrightNeighbor;
            case DOWN_RIGHT -> downNeighbor.rightNeighbor;
            case DOWN -> downNeighbor;
            // case DOWN_LEFT -> downLeftNeighbor;
            case DOWN_LEFT -> downNeighbor.leftNeighbor;
            case LEFT -> leftNeighbor;
            // case UP_LEFT -> upLeftNeighbor;
            case UP_LEFT -> upNeighbor.leftNeighbor;
        };
    }

    public Node getUpNeighbor() {
        return upNeighbor;
    }

    public void setUpNeighbor(Node upNeighbor) {
        this.upNeighbor = upNeighbor;
    }

    public Node getRightNeighbor() {
        return rightNeighbor;
    }

    public void setRightNeighbor(Node rightNeighbor) {
        this.rightNeighbor = rightNeighbor;
    }

    public Node getDownNeighbor() {
        return downNeighbor;
    }

    public void setDownNeighbor(Node downNeighbor) {
        this.downNeighbor = downNeighbor;
    }

    public Node getLeftNeighbor() {
        return leftNeighbor;
    }

    public void setLeftNeighbor(Node leftNeighbor) {
        this.leftNeighbor = leftNeighbor;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isEmpty() {
        return entity == null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "upNeighbor=" + upNeighbor.getCoordinates() +
                ", rightNeighbor=" + rightNeighbor.getCoordinates() +
                ", downNeighbor=" + downNeighbor.getCoordinates() +
                ", leftNeighbor=" + leftNeighbor.getCoordinates() +
                ", entity=" + entity +
                ", coordinates=" + coordinates +
                '}';
    }
}
