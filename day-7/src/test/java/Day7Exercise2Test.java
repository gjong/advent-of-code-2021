import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7Exercise2Test {

    @Test
    void execute() {
        var exercise = new Day7Exercise2();

        exercise.runOnData("16,1,2,0,4,2,7,1,2,14");

        assertEquals("168", exercise.execute());
    }
}
