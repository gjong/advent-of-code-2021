import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day10Exercise1Test {

    @Test
    void execute() {
        var exercise = new Day10Exercise1();

        exercise.runOnData("""
                [({(<(())[]>[[{[]{<()<>>
                [(()[<>])]({[<{<<[]>>(
                {([(<{}[<>[]}>{[]{[(<()>
                (((({<>}<{<{<>}{[]{[]{}
                [[<[([]))<([[{}[[()]]]
                [{[{({}]{}}([{[{{{}}([]
                {<[[]]>}<{[{[{[]{()[[[]
                [<(<(<(<{}))><([]([]()
                <{([([[(<>()){}]>(<<{{
                <{([{{}}[<[[[<>{}]]]>[]]""");

        assertEquals("26397", exercise.execute());
    }
}
