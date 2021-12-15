import com.jongsoft.lang.API;
import com.jongsoft.lang.collection.tuple.Tuple;
import util.InputProcessing;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day14Exercise1 implements Exercise {

    private List<String> polymer;
    private Map<String, String> polymerGrowMap;

    public Day14Exercise1() {
    }

    @Override
    public void runOnData(String dataString) {
        var lines = InputProcessing.convertToLines(dataString);

        polymer = Arrays.stream(lines.get(0).split("")).collect(Collectors.toList());

        polymerGrowMap = new HashMap<>(lines.size() - 2);
        for (var index = 2; index < lines.size(); index++) {
            var mapping = API.Tuple(lines.get(index).split(" -> "));
            polymerGrowMap.put(mapping.getFirst(), mapping.getSecond());
        }
    }

    @Override
    public String execute() {
        var mostCommonCount = 0L;
        var leastCommonCount = 0L;

        for (var growOperation = 0; growOperation < 10; growOperation++) {
            growPolymer();
        }

        var countedMap = polymer.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .collect(Collectors.toList());

        leastCommonCount = countedMap.get(0).getValue();
        mostCommonCount = countedMap.get(countedMap.size() - 1).getValue();

        return String.valueOf(mostCommonCount - leastCommonCount);
    }

    private void growPolymer() {
        var pairIndex = 0;
        while ((pairIndex + 1) < polymer.size()) {
            var lookup = polymer.get(pairIndex) + polymer.get(pairIndex + 1);
            var addition = polymerGrowMap.get(lookup);
            polymer.add(pairIndex + 1, addition);
            pairIndex += 2;
        }
    }

    @Override
    public String day() {
        return "day-14";
    }
}
