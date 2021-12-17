import grid.Point;

record TargetArea(Point leftTop, Point rightBottom) {
    public boolean isHit(Point position) {
        return isBetweenXAxis(position.x())
                && isBetweenYAxis(position.y());
    }

    private boolean isBetweenXAxis(int x) {
        return x >= leftTop.x() && x <= rightBottom.x();
    }

    private boolean isBetweenYAxis(int y) {
        return leftTop.y() <= y && rightBottom.y() >= y;
    }
}
