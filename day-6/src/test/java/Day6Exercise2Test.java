import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day6Exercise2Test {

    @Test
    void execute() {
        var exercise = new Day6Exercise2();

        exercise.runOnData("3,4,3,1,2");

        assertEquals("26984457539", exercise.execute());
    }
}
