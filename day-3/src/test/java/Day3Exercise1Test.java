import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day3Exercise1Test {

    @Test
    void execute() {
        var exercise = new Day3Exercise1();

        exercise.runOnData("""
                00100
                11110
                10110
                10111
                10101
                01111
                00111
                11100
                10000
                11001
                00010
                01010""");

        assertEquals("198", exercise.execute());
    }
}
