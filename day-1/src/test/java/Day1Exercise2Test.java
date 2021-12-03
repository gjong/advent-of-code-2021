import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day1Exercise2Test {

    @Test
    void execute() {
        var exercise = new Day1Exercise2();

        exercise.runOnData("""
                199
                200
                208
                210
                200
                207
                240
                269
                260
                263""");

        assertEquals("5", exercise.execute());
    }

}
