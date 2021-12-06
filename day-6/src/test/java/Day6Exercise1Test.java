import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day6Exercise1Test {

    @Test
    void execute() {
        var exercise = new Day6Exercise1();

        exercise.runOnData("3,4,3,1,2");

        assertEquals("5934", exercise.execute());
    }
}
