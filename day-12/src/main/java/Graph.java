import java.util.*;

public class Graph<T> {
    private final Set<GraphNode<T>> nodes;
    private Map<GraphNode<T>, Set<GraphNode<T>>> paths;

    public Graph() {
        nodes = new HashSet<>();
        paths = new HashMap<>();
    }

    public GraphNode<T> computeIfAbsent(T value) {
        for (var node : nodes) {
            if (Objects.equals(node.value(), value)) {
                return node;
            }
        }

        var node = new GraphNode<>(value);
        nodes.add(node);
        return node;
    }

    public void addPath(GraphNode<T> start, GraphNode<T> end) {
        paths.computeIfAbsent(start, key -> new HashSet<>()).add(end);
        paths.computeIfAbsent(end, key -> new HashSet<>()).add(start);
    }

    public Set<GraphNode<T>> getConnectedNodes(GraphNode<T> node) {
        return Set.copyOf(paths.get(node));
    }
}
