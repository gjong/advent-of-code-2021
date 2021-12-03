import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day2Exercise2Test {

    @Test
    void execute() {
        var exercise = new Day2Exercise2();

        exercise.runOnData("""
                forward 5
                down 5
                forward 8
                up 3
                down 8
                forward 2""");

        assertEquals("900", exercise.execute());
    }
}
