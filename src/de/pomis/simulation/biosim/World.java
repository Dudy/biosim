package de.pomis.simulation.biosim;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class World {

    private static final Logger LOG = Logger.getLogger(World.class.getName());

    public enum Type { BLOCK, EMPTY, ENTITY }
    public enum Direction { UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT }

    // this is just the node at coordinates (0,0) to always have an anchor point
    private final Node zeroNode = new Node(new Coordinates(0, 0));

    private final Configuration configuration;

    private List<Entity> entities = new ArrayList<>();

    public World(Configuration configuration) {
        //LOG.getParent().getHandlers()[0].setLevel(Level.ALL);
        //LOG.setLevel(Level.ALL);

        this.configuration = configuration;

        createNodes();
        createEntities();
    }

    private void createNodes() {
        Node leftNode = zeroNode;
        Node upNode = zeroNode;
        Node node;

        // first row of nodes
        for (int x = 1; x < configuration.getMaxWorldWidth(); x++) {
            node = new Node(new Coordinates(x, 0));
            node.setLeftNeighbor(leftNode);
            leftNode.setRightNeighbor(node);
            leftNode = node;
        }
        leftNode.setRightNeighbor(zeroNode);
        zeroNode.setLeftNeighbor(leftNode);

        // second to last row of nodes
        for (int y = 1; y < configuration.getMaxWorldHeight(); y++) {
            leftNode = new Node(new Coordinates(0, y));
            leftNode.setUpNeighbor(upNode);
            upNode.setDownNeighbor(leftNode);
            upNode = upNode.getRightNeighbor();

            for (int x = 1; x < configuration.getMaxWorldWidth(); x++) {
                // new node
                node = new Node(new Coordinates(x, y));

                // set two-way connection on the left
                node.setLeftNeighbor(leftNode);
                leftNode.setRightNeighbor(node);

                // set two-way connection above
                node.setUpNeighbor(upNode);
                upNode.setDownNeighbor(node);

                // proceed one step to the right
                leftNode = leftNode.getRightNeighbor();
                upNode = upNode.getRightNeighbor();
            }
            leftNode.setRightNeighbor(upNode.getDownNeighbor());
            upNode.getDownNeighbor().setLeftNeighbor(leftNode);
            upNode = upNode.getDownNeighbor();
        }

        // connect first and last rows
        node = zeroNode;
        for (int x = 0; x < configuration.getMaxWorldWidth(); x++) {
            // set two-way connection above
            node.setUpNeighbor(upNode);
            upNode.setDownNeighbor(node);

            // proceed one step to the right
            node = node.getRightNeighbor();
            upNode = upNode.getRightNeighbor();
        }
    }

    private void createEntities() {
        // create walls
        for (int x = 0; x < configuration.getMaxWorldWidth(); x++) {
            Node node = getNode(x, 0);
            Entity entity = new Entity(true, node, configuration);
            node.setEntity(entity);

            node = getNode(x, configuration.getMaxWorldHeight() - 1);
            entity = new Entity(true, node, configuration);
            node.setEntity(entity);
        }
        for (int y = 1; y < configuration.getMaxWorldWidth() - 1; y++) {
            Node node = getNode(0, y);
            Entity entity = new Entity(true, node, configuration);
            node.setEntity(entity);

            node = getNode(configuration.getMaxWorldWidth() - 1, y);
            entity = new Entity(true, node, configuration);
            node.setEntity(entity);
        }

        // create real entities
        for (int i = 0; i < configuration.getMaxNumberOfEntities(); i++) {
            Node node = getNode(getFreeRandomCoordinates(configuration));
            Entity entity = new Entity(false, node, configuration);
            node.setEntity(entity);
            entities.add(entity);
        }
    }

    private Coordinates getFreeRandomCoordinates(Configuration configuration) {
        Coordinates coordinates = Coordinates.getRandom(configuration);
        Node node = getNode(coordinates);

        int threshold = configuration.getMaxWorldWidth() * configuration.getMaxWorldHeight();
        while (!node.isEmpty()) {
            LOG.log(Level.FINE, coordinates + " is not empty, entity is " + node.getEntity());
            coordinates = Coordinates.getRandom(configuration);
            node = getNode(coordinates);
            threshold--;
            if (threshold < 0) {
                // coordinates = null;
                // break;
                throw new RuntimeException("could not find free random coordinates ");
            }
        }

        return coordinates;
    }

    public Node getNode(Coordinates coordinates) {
        return getNode(coordinates.getX(), coordinates.getY());
    }

    public Node getNode(int x, int y) {
        assert x >= 0;
        assert x < configuration.getMaxWorldWidth();
        assert y >= 0;
        assert y < configuration.getMaxWorldHeight();

        Node node = zeroNode;
        for (int i = 0; i < x; i++) {
            node = node.getRightNeighbor();
        }
        for (int i = 0; i < y; i++) {
            node = node.getDownNeighbor();
        }

        return node;
    }

    public Node getZeroNode() {
        return zeroNode;
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
