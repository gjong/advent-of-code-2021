import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day21Exercise1Test {

    @Test
    void execute() {
        var exercise = new Day21Exercise1();

        exercise.runOnData("""
                Player 1 starting position: 4
                Player 2 starting position: 8""");

        assertEquals("739785", exercise.execute());
    }
}
