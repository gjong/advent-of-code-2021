package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public interface InputProcessing {
    String NEW_LINE_MATCHER = "(\r)?\n";

    static List<String> convertToLines(String data) {
        return Arrays.stream(data.split(NEW_LINE_MATCHER))
                .map(String::trim)
                .toList();
    }

    static List<Integer> convertToIntegers(char[] numbers) {
        var converted = new ArrayList<Integer>();
        for (char num : numbers) {
            converted.add(parseInt(String.valueOf(num)));
        }
        return converted;
    }

    static List<Integer> convertToIntegers(List<String> lines) {
        return lines.stream()
                .map(Integer::parseInt)
                .toList();
    }

    static int[][] gridToArray(List<List<Integer>> grid) {
        var array = new int[grid.size()][grid.get(0).size()];

        for (var row = 0; row < array.length; row++) {
            for (var column = 0; column < array[0].length; column++) {
                array[row][column] = grid.get(row).get(column);
            }
        }

        return array;
    }

}
