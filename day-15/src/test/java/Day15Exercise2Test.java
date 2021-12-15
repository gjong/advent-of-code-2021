import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day15Exercise2Test {

    @Test
    void execute() {
        var exercise = new Day15Exercise2();

        exercise.runOnData("""
                1163751742
                1381373672
                2136511328
                3694931569
                7463417111
                1319128137
                1359912421
                3125421639
                1293138521
                2311944581""");

        assertEquals("315", exercise.execute());
    }
}
