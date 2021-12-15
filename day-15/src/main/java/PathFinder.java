import grid.Grid;
import grid.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class PathFinder {
    private static final Logger LOGGER = LoggerFactory.getLogger("Exercise");
    public static class Node {
        private Point position;
        private final int weight;
        private long distance = Long.MAX_VALUE;
        private final List<Node> connectedNodes;

        public Node(Point position, int weight) {
            this.position = position;
            this.weight = weight;
            this.connectedNodes = new ArrayList<>();
        }

        public int getWeight() {
            return weight;
        }

        public long getDistance() {
            return distance;
        }
    }

    private int width;
    private List<Node> nodes;

    public PathFinder(Grid<Node> costGrid) {
        this.nodes = new ArrayList<>();
        for (var y = 0; y < costGrid.height(); y++) {
            for (var x = 0; x < costGrid.width(); x++) {
                var node = costGrid.at(x, y);

                if (costGrid.inBounds(x + 1, y)) {
                    node.connectedNodes.add(costGrid.at(x + 1, y));
                }
                if (costGrid.inBounds(x - 1, y)) {
                    node.connectedNodes.add(costGrid.at(x - 1, y));
                }
                if (costGrid.inBounds(x, y + 1)) {
                    node.connectedNodes.add(costGrid.at(x, y + 1));
                }
                if (costGrid.inBounds(x, y - 1)) {
                    node.connectedNodes.add(costGrid.at(x, y - 1));
                }
                
                nodes.add(node);
            }
        }
        this.width = costGrid.width();
    }

    public long solve() {
        Set<Node> settledNodes = new HashSet<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(1000, Comparator.comparingLong(Node::getDistance));

        var startNode = this.nodes.get(0);
        startNode.distance = 0;
        pq.add(startNode);

        while (!pq.isEmpty()) {
            var evaluate = pq.poll();
            settledNodes.add(evaluate);

            evaluate.connectedNodes
                    .forEach(node -> {
                        if (!settledNodes.contains(node)) {
                            var computedDistance = evaluate.distance + node.weight;
                            if (computedDistance < node.distance) {
                                node.distance = computedDistance;
                                pq.add(node);
                            }
                        }
                    });
        }

        return nodes.get(nodes.size() - 1).distance;
    }

    public void logDistances() {
        StringBuilder line = new StringBuilder();
        for (var y = 0; y < nodes.size(); y++) {
            if ((y % width) == 0) {
                LOGGER.debug(line.toString());
                line = new StringBuilder();
            }
            line.append(nodes.get(y).distance + " ");
        }
    }
}
