import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day16Exercise1Test {

    @Test
    void execute_small() {
        var exercise = new Day16Exercise1();

        exercise.runOnData("D2FE28");

        assertEquals("6", exercise.execute());
    }

    @Test
    void execute_sample() {
        var exercise = new Day16Exercise1();

        exercise.runOnData("8A004A801A8002F478");

        assertEquals("16", exercise.execute());
    }

    @Test
    void execute_sample2() {
        var exercise = new Day16Exercise1();

        exercise.runOnData("620080001611562C8802118E34");

        assertEquals("12", exercise.execute());
    }

    @Test
    void execute_sample3() {
        var exercise = new Day16Exercise1();

        exercise.runOnData("C0015000016115A2E0802F182340");

        assertEquals("23", exercise.execute());
    }

    @Test
    void execute_sample4() {
        var exercise = new Day16Exercise1();

        exercise.runOnData("A0016C880162017C3686B18A3D4780");

        assertEquals("31", exercise.execute());
    }
}
