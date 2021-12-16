import grid.Grid;
import grid.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class PathFinder {
    private static final Logger LOGGER = LoggerFactory.getLogger("Exercise");
    public static class Node {
        private final int weight;
        private long distance = Long.MAX_VALUE;
        private final Node[] connectedNodes;

        public Node(int weight) {
            this.weight = weight;
            this.connectedNodes = new Node[4];
        }

        public int getWeight() {
            return weight;
        }

        public long getDistance() {
            return distance;
        }
    }

    private final Grid<Node> costGrid;

    public PathFinder(Grid<Node> costGrid) {
        for (var y = 0; y < costGrid.height(); y++) {
            for (var x = 0; x < costGrid.width(); x++) {
                var node = costGrid.at(x, y);

                if (costGrid.inBounds(x + 1, y)) {
                    node.connectedNodes[0] = costGrid.at(x + 1, y);
                }
                if (costGrid.inBounds(x - 1, y)) {
                    node.connectedNodes[1] = costGrid.at(x - 1, y);
                }
                if (costGrid.inBounds(x, y + 1)) {
                    node.connectedNodes[2] = costGrid.at(x, y + 1);
                }
                if (costGrid.inBounds(x, y - 1)) {
                    node.connectedNodes[1] = costGrid.at(x, y - 1);
                }
            }
        }
        this.costGrid = costGrid;
    }

    public long solve() {
        Set<Node> settledNodes = new HashSet<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(1000, Comparator.comparingLong(Node::getDistance));

        var startNode = this.costGrid.at(0, 0);
        startNode.distance = 0;
        pq.add(startNode);

        while (!pq.isEmpty()) {
            var evaluate = pq.poll();
            settledNodes.add(evaluate);

            for (var node : evaluate.connectedNodes) {
                if (node != null && !settledNodes.contains(node)) {
                    var computedDistance = evaluate.distance + node.weight;
                    if (computedDistance < node.distance) {
                        node.distance = computedDistance;
                        pq.add(node);
                    }
                }
            }
        }

        return costGrid.at(costGrid.width() - 1, costGrid.height() - 1).distance;
    }
}
