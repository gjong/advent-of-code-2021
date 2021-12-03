import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day2Exercise1Test {

    @Test
    void execute() {
        var exercise = new Day2Exercise1();

        exercise.runOnData("""
                forward 5
                down 5
                forward 8
                up 3
                down 8
                forward 2""");

        assertEquals("150", exercise.execute());
    }
}
