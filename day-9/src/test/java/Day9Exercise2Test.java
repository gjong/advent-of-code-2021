import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Exercise2Test {

    @Test
    void execute() {
        var exercise = new Day9Exercise2();

        exercise.runOnData("""
                2199943210
                3987894921
                9856789892
                8767896789
                9899965678""");

        assertEquals("1134", exercise.execute());
    }
}
