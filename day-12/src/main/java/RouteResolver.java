import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class RouteResolver<T> {

    private final Graph<T> graph;
    private final BiPredicate<GraphNode<T>, Route<T>> visitAllowed;

    public RouteResolver(Graph<T> graph, BiPredicate<GraphNode<T>, Route<T>> visitAllowed) {
        this.visitAllowed = visitAllowed;
        this.graph = graph;
    }

    public List<Route<T>> resolveAllRoutes(GraphNode<T> start, GraphNode<T> end) {
        return resolveNextStep(
                new Route<>(start, end),
                start);
    }

    public List<Route<T>> resolveNextStep(Route<T> existingRoute, GraphNode<T> fromNode) {
        var routes = new ArrayList<Route<T>>();
        for (var nextStep : graph.getConnectedNodes(fromNode)) {
            if (nextStep == existingRoute.getEnd()) {
                routes.add(existingRoute.addStep(nextStep));
            } else if (visitAllowed.test(nextStep, existingRoute)) {
                routes.addAll(
                        resolveNextStep(
                                existingRoute.addStep(nextStep),
                                nextStep));
            }
        }
        return routes;
    }
}
