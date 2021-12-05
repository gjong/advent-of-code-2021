public record Point(int x, int y) {

    @Override
    public boolean equals(Object o) {
        if (o instanceof Point other) {
            return other.x == x && other.y == y;
        }
        return false;
    }
}
