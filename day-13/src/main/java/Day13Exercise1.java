import com.jongsoft.lang.Collections;
import grid.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.lang.Integer.parseInt;

public class Day13Exercise1 implements Exercise {

    private Matrix matrix;
    private List<Function<Matrix, Matrix>> foldInstructions;

    @Override
    public void runOnData(String dataString) {
        var dataInstructionSplit = dataString.split("((\r)?\n){2}");
        var points = Collections.List(dataInstructionSplit[0].split(NEW_LINE_MATCHER))
                .map(coordinate -> coordinate.split(","))
                .map(point -> new Point(parseInt(point[0]), parseInt(point[1])));
        foldInstructions = new ArrayList<>();

        var maxX = points.map(Point::x).max().get() + 1;
        var maxY = points.map(Point::y).max().get() + 1;
        matrix = new Matrix(maxX, maxY);

        points.forEach(p -> matrix.activateBit(p.x(), p.y()));

        for (var line : dataInstructionSplit[1].split(NEW_LINE_MATCHER)){
            if (line.startsWith("fold along y=")) {
                foldInstructions.add(source -> source.foldOnY(parseInt(line.replace("fold along y=", ""))));
            } else if (line.startsWith("fold along x=")) {
                foldInstructions.add(source -> source.foldOnX(parseInt(line.replace("fold along x=", ""))));
            }
        }
    }

    @Override
    public String execute() {
        var folded = foldInstructions.get(0).apply(matrix);

        return String.valueOf(folded.countActive());
    }

    @Override
    public String day() {
        return "day-13";
    }
}
