import util.InputProcessing;

import java.util.function.Function;
import java.util.stream.Collectors;

public class Day12Exercise2 implements Exercise {

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

        if (isBigCave(node.value())) {
            return true;
        }

        if (route.containsNode(node)) {
            return route.getSteps()
                    .stream()
                    .filter(step -> !isBigCave(step.value()))
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .values()
                    .stream()
                    .noneMatch(count -> count > 1);
        }

        return true;
    }

    private boolean isBigCave(String caveName) {
        return caveName.equals(caveName.toUpperCase());
    }

    @Override
    public String day() {
        return "day-12";
    }
}
