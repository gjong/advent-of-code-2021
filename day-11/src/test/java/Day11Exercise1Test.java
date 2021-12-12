import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day11Exercise1Test {

    @Test
    void execute() {
        var exercise = new Day11Exercise1();

        exercise.runOnData("""
                5483143223
                2745854711
                5264556173
                6141336146
                6357385478
                4167524645
                2176841721
                6882881134
                4846848554
                5283751526""");

        assertEquals("1656", exercise.execute());
    }
}
