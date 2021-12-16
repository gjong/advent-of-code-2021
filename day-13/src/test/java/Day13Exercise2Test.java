import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13Exercise2Test {

    @Test
    void execute() {
        var exercise = new Day13Exercise2();

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

        assertEquals(System.lineSeparator() + "#####" + System.lineSeparator() +
                "#   #" + System.lineSeparator() +
                "#   #" + System.lineSeparator() +
                "#   #" + System.lineSeparator() +
                "#####" + System.lineSeparator() +
                "     " + System.lineSeparator() +
                "     " + System.lineSeparator(), exercise.execute());
    }
}
