package grid;

public record Point(int x, int y) {

    public Point translate(Point with) {
        return new Point(x + with.x, y + with.y);
    }
}
