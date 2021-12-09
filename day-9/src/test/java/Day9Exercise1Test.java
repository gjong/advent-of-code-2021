import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day9Exercise1Test {

    @Test
    void execute() {
        var exercise = new Day9Exercise1();

        exercise.runOnData("""
                2199943210
                3987894921
                9856789892
                8767896789
                9899965678""");

        assertEquals("15", exercise.execute());
    }
}
