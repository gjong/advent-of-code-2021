import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day16Exercise2Test {

    @Test
    void execute_small() {
        var exercise = new Day16Exercise2();

        exercise.runOnData("C200B40A82");

        assertEquals("3", exercise.execute());
    }

    @Test
    void execute_sample() {
        var exercise = new Day16Exercise2();

        exercise.runOnData("04005AC33890");

        assertEquals("54", exercise.execute());
    }

    @Test
    void execute_sample2() {
        var exercise = new Day16Exercise2();

        exercise.runOnData("880086C3E88112");

        assertEquals("7", exercise.execute());
    }

    @Test
    void execute_sample3() {
        var exercise = new Day16Exercise2();

        exercise.runOnData("CE00C43D881120");

        assertEquals("9", exercise.execute());
    }

    @Test
    void execute_sample4() {
        var exercise = new Day16Exercise2();

        exercise.runOnData("D8005AC2A8F0");

        assertEquals("1", exercise.execute());
    }

    @Test
    void execute_sample5() {
        var exercise = new Day16Exercise2();

        exercise.runOnData("F600BC2D8F");

        assertEquals("0", exercise.execute());
    }

    @Test
    void execute_sample6() {
        var exercise = new Day16Exercise2();

        exercise.runOnData("9C005AC2F8F0");

        assertEquals("0", exercise.execute());
    }

    @Test
    void execute_sample7() {
        var exercise = new Day16Exercise2();

        exercise.runOnData("9C0141080250320F1802104A08");

        assertEquals("1", exercise.execute());
    }
}
