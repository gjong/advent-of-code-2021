import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Day14Exercise1Test {

    @Test
    void execute() {
        var exercise = new Day14Exercise1();

        exercise.runOnData("""
                NNCB
                                
                CH -> B
                HH -> N
                CB -> H
                NH -> C
                HB -> C
                HC -> B
                HN -> C
                NN -> C
                BH -> H
                NC -> B
                NB -> B
                BN -> B
                BB -> N
                BC -> B
                CC -> N
                CN -> C""");

        assertEquals("1588", exercise.execute());
    }
}
