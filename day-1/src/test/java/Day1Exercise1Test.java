import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day1Exercise1Test {

    @Test
    void execute() {
        var exercise = new Day1Exercise1();

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

        assertEquals("7", exercise.execute());
    }
}
