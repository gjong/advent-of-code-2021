import util.InputProcessing;

public class Day12Exercise1 implements Exercise {

    private final Graph<String> graph = new Graph<>();

    @Override
    public void runOnData(String dataString) {
        InputProcessing.convertToLines(dataString)
                .forEach(line -> {
                    var path = line.trim().split("-");
                    graph.addPath(
                            graph.computeIfAbsent(path[0]),
                            graph.computeIfAbsent(path[1]));
                });
    }

    @Override
    public String execute() {
        var resolver = new RouteResolver<>(graph, this::canVisit);

        var routes = resolver.resolveAllRoutes(
                graph.computeIfAbsent("start"),
                graph.computeIfAbsent("end"));

        return String.valueOf(routes.size());
    }

    private boolean canVisit(GraphNode<String> node, Route<String> route) {
        if (node.value().equalsIgnoreCase("start")) {
            return false;
        }

        if (!node.value().equals(node.value().toUpperCase())) {
            return !route.containsNode(node);
        }

        return true;
    }

    @Override
    public String day() {
        return "day-12";
    }
}
