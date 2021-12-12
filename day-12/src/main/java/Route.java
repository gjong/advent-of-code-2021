import java.util.ArrayList;
import java.util.List;

public class Route<T> {
    private final GraphNode<T> start;
    private final GraphNode<T> end;

    private List<GraphNode<T>> steps;

    public Route(GraphNode<T> start, GraphNode<T> end) {
        this.start = start;
        this.end = end;
        this.steps = new ArrayList<>();
    }

    public GraphNode<T> getEnd() {
        return end;
    }

    public GraphNode<T> lastVisited() {
        return steps.get(steps.size() - 1);
    }

    public boolean containsNode(GraphNode<T> node) {
        return steps.contains(node);
    }

    public List<GraphNode<T>> getSteps() {
        return steps;
    }

    public Route<T> addStep(GraphNode<T> node) {
        var updatedRoute = new Route<>(start, end);
        updatedRoute.steps.addAll(steps);
        updatedRoute.steps.add(node);
        return updatedRoute;
    }

    @Override
    public String toString() {
        return start + steps.toString();
    }
}
