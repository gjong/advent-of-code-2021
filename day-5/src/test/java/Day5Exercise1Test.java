import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day5Exercise1Test {

    @Test
    void execute() {
        var exercise = new Day5Exercise1();

        exercise.runOnData("""
                0,9 -> 5,9
                8,0 -> 0,8
                9,4 -> 3,4
                2,2 -> 2,1
                7,0 -> 7,4
                6,4 -> 2,0
                0,9 -> 2,9
                3,4 -> 1,4
                0,0 -> 8,8
                5,5 -> 8,2""");

        assertEquals("5", exercise.execute());
    }
}
