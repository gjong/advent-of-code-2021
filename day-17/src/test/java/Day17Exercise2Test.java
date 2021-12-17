import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day17Exercise2Test {

    @Test
    void execute() {
        var exercise = new Day17Exercise2();

        exercise.runOnData("target area: x=20..30, y=-10..-5");

        assertEquals("112", exercise.execute());
    }
}
