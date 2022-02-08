package de.pomis.simulation.biosim;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class WorldTest {
    @Test
    void test() throws Exception {
        Configuration configuration = new Configuration();
        long start = System.currentTimeMillis();
        World world = new World(configuration);
        long end = System.currentTimeMillis();
        System.out.println("generating the world took " + (end - start) + "ms");
        Node node = world.getZeroNode();

        for (int y = 0; y < configuration.getMaxWorldHeight(); y++) {
            for (int x = 0; x < configuration.getMaxWorldWidth(); x++) {
                assertEquals(x, node.getCoordinates().getX());
                assertEquals(y, node.getCoordinates().getY());

                assertEquals(leftOf(node.getCoordinates().getX(), configuration), node.getLeftNeighbor().getCoordinates().getX());
                assertEquals(rightOf(node.getCoordinates().getX(), configuration), node.getRightNeighbor().getCoordinates().getX());
                assertEquals(above(node.getCoordinates().getY(), configuration), node.getUpNeighbor().getCoordinates().getY());
                assertEquals(below(node.getCoordinates().getY(), configuration), node.getDownNeighbor().getCoordinates().getY());

                node = node.getNeighbor(World.Direction.RIGHT);
            }
            node = node.getNeighbor(World.Direction.DOWN);
        }
    }

    @Test
    void test_getNode() {
        Configuration configuration = new Configuration();
        World world = new World(configuration);
        Coordinates coordinates = Coordinates.getRandom(configuration);
        Node node = world.getNode(coordinates);

        assertEquals(coordinates.getX(), node.getCoordinates().getX());
        assertEquals(coordinates.getY(), node.getCoordinates().getY());
    }

    private int leftOf(int x, Configuration configuration) throws Exception {
        if (x < 0) {
            throw new Exception("illegal x (less than 0: " + x + ")");
        } else if (x == 0) {
            return configuration.getMaxWorldWidth() - 1;
        } else if (x < configuration.getMaxWorldWidth()) {
            return x - 1;
        } else {
            throw new Exception("illegal x (greater than " + (configuration.getMaxWorldWidth() - 1) + ": " + x + ")");
        }
    }

    private int rightOf(int x, Configuration configuration) throws Exception {
        if (x >= configuration.getMaxWorldWidth()) {
            throw new Exception("illegal x (greater than " + (configuration.getMaxWorldWidth() - 1) + ": " + x + ")");
        } else if (x == configuration.getMaxWorldWidth() - 1) {
            return 0;
        } else if (x >= 0) {
            return x + 1;
        } else {
            throw new Exception("illegal x (less than 0: " + x + ")");
        }
    }

    private int above(int y, Configuration configuration) throws Exception {
        if (y < 0) {
            throw new Exception("illegal y (less than 0: " + y + ")");
        } else if (y == 0) {
            return configuration.getMaxWorldHeight() - 1;
        } else if (y < configuration.getMaxWorldHeight()) {
            return y - 1;
        } else {
            throw new Exception("illegal y (greater than " + (configuration.getMaxWorldHeight() - 1) + ": " + y + ")");
        }
    }

    private int below(int y, Configuration configuration) throws Exception {
        if (y >= configuration.getMaxWorldHeight()) {
            throw new Exception("illegal y (greater than " + (configuration.getMaxWorldHeight() - 1) + ": " + y + ")");
        } else if (y == configuration.getMaxWorldHeight() - 1) {
            return 0;
        } else if (y >= 0) {
            return y + 1;
        } else {
            throw new Exception("illegal y (less than 0: " + y + ")");
        }
    }
}
