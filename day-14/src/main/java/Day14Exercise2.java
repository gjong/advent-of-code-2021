import com.jongsoft.lang.API;
import com.jongsoft.lang.collection.tuple.Pair;
import util.InputProcessing;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day14Exercise2 implements Exercise {

    private String polymer;
    private Map<String, Pair<String, String>> polymerGrowMap;

    public Day14Exercise2() {
    }

    @Override
    public void runOnData(String dataString) {
        var lines = InputProcessing.convertToLines(dataString);

        polymer = lines.get(0);

        polymerGrowMap = new HashMap<>(lines.size() - 2);
        for (var index = 2; index < lines.size(); index++) {
            var mapping = API.Tuple(lines.get(index).split(" -> "));
            polymerGrowMap.put(
                    mapping.getFirst(),
                    API.Tuple(
                            mapping.getFirst().charAt(0) + mapping.getSecond(),
                            mapping.getSecond() + mapping.getFirst().charAt(1)));
        }
    }

    @Override
    public String execute() {
        var mostCommonCount = BigInteger.ZERO;
        var leastCommonCount = BigInteger.ZERO;

        Map<String, BigInteger> polymerPairMap = new HashMap<>();
        for (var position = 0; position < (polymer.length() - 1); position++) {
            var pair = polymer.substring(position, position + 2);
            var counted = polymerPairMap.get(pair);
            polymerPairMap.put(pair, counted != null ? counted.add(BigInteger.ONE) : BigInteger.ONE);
        }

        for (var round = 0; round < 40; round++) {
            polymerPairMap = handleRound(polymerPairMap);
        }

        Map<Character, BigInteger> counted = new HashMap<>();
        for (var entry : polymerPairMap.entrySet()) {
            var letter = entry.getKey().charAt(0);
            var count = counted.get(letter);

            counted.put(letter, count != null ? count.add(entry.getValue()) : entry.getValue());
        }
        var lastLetter = polymer.charAt(polymer.length() - 1);
        counted.put(lastLetter, counted.computeIfAbsent(lastLetter, k -> BigInteger.ZERO).add(BigInteger.ONE));

        var counts = counted.values()
                .stream()
                .sorted().toList();

        leastCommonCount = counts.get(0);
        mostCommonCount = counts.get(counts.size() - 1);

        return String.valueOf(mostCommonCount.subtract(leastCommonCount));
    }

    private Map<String, BigInteger> handleRound(Map<String, BigInteger> polymerPairMap) {
        Map<String, BigInteger> updatedGrowMap = new HashMap<>();
        for (var growSet : polymerGrowMap.entrySet()) {
            var currentCount = polymerPairMap.get(growSet.getKey());
            if (currentCount == null) {
                continue;
            }

            updatedGrowMap.put(
                    growSet.getValue().getFirst(),
                    updatedGrowMap.computeIfAbsent(growSet.getValue().getFirst(), key -> BigInteger.ZERO).add(currentCount));
            updatedGrowMap.put(
                    growSet.getValue().getSecond(),
                    updatedGrowMap.computeIfAbsent(growSet.getValue().getSecond(), key -> BigInteger.ZERO).add(currentCount));
        }
        return updatedGrowMap;
    }

    @Override
    public String day() {
        return "day-14";
    }
}
