import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day17Exercise1Test {

    @Test
    void execute() {
        var exercise = new Day17Exercise1();

        exercise.runOnData("target area: x=20..30, y=-10..-5");

        assertEquals("45", exercise.execute());
    }
}
