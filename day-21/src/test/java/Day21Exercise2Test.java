import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day21Exercise2Test {

    @Test
    void execute() {
        var exercise = new Day21Exercise2();

        exercise.runOnData("""
                Player 1 starting position: 4
                Player 2 starting position: 8""");

        assertEquals("444356092776315", exercise.execute());
    }
}
