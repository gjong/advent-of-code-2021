import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Exercise2Test {

    @Test
    void execute() {
        var exercise = new Day12Exercise2();

        exercise.runOnData("""
                start-A
                start-b
                A-c
                A-b
                b-d
                A-end
                b-end""");

        assertEquals("36", exercise.execute());
    }

    @Test
    void execute_larger() {
        var exercise = new Day12Exercise2();

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

        assertEquals("103", exercise.execute());
    }

    @Test
    void execute_largest() {
        var exercise = new Day12Exercise2();

        exercise.runOnData("""
                fs-end
                he-DX
                fs-he
                start-DX
                pj-DX
                end-zg
                zg-sl
                zg-pj
                pj-he
                RW-he
                fs-DX
                pj-RW
                zg-RW
                start-pj
                he-WI
                zg-he
                pj-fs
                start-RW""");

        assertEquals("3509", exercise.execute());
    }
}
