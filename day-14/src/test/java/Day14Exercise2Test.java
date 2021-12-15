import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day14Exercise2Test {

    @Test
    void execute() {
        var exercise = new Day14Exercise2();

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

        assertEquals("2188189693529", exercise.execute());
    }
}
