import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day12Exercise1Test {

    @Test
    void execute() {
        var exercise = new Day12Exercise1();

        exercise.runOnData("""
                start-A
                start-b
                A-c
                A-b
                b-d
                A-end
                b-end""");

        assertEquals("10", exercise.execute());
    }

    @Test
    void execute_larger() {
        var exercise = new Day12Exercise1();

        exercise.runOnData("""
                dc-end
                HN-start
                start-kj
                dc-start
                dc-HN
                LN-dc
                HN-end
                kj-sa
                kj-HN
                kj-dc""");

        assertEquals("19", exercise.execute());
    }
}
