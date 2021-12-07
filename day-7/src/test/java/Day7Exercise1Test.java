import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day7Exercise1Test {

    @Test
    void execute() {
        var exercise = new Day7Exercise1();

        exercise.runOnData("16,1,2,0,4,2,7,1,2,14");

        assertEquals("37", exercise.execute());
    }
}
