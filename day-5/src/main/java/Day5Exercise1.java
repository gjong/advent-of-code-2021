import grid.Vector;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Day5Exercise1 implements Exercise {

    private List<Vector> vectors;

    @Override
    public void runOnData(String dataString) {
        vectors = Arrays.stream(dataString.split(NEW_LINE_MATCHER))
                .map(Day5Utils::toVector)
                .filter(Predicate.not(Vector::isDiagonal))
                .toList();
    }

    @Override
    public String execute() {
        var duplicateFields = vectors.stream()
                .map(Vector::toLine)
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream()
                .filter(count -> count > 1)
                .count();

        return String.valueOf(duplicateFields);
    }

    @Override
    public String day() {
        return "day-5";
    }
}
