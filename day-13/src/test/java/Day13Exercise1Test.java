import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day13Exercise1Test {

    @Test
    void execute() {
        var exercise = new Day13Exercise1();

        exercise.runOnData("""
                6,10
                0,14
                9,10
                0,3
                10,4
                4,11
                6,0
                6,12
                4,1
                0,13
                10,12
                3,4
                3,0
                8,4
                1,10
                2,14
                8,10
                9,0
                
                fold along y=7
                fold along x=5""");

        assertEquals("17", exercise.execute());
    }
}
