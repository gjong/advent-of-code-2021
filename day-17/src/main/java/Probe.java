import grid.Point;

class Probe {
    private Point velocity;
    private Point position;
    private int highestY;

    public Probe(Point velocity) {
        this.velocity = velocity;
        this.position = new Point(0, 0);
        this.highestY = 0;
    }

    public void move() {
        position = position.translate(velocity);
        if (highestY < position.y()) {
            highestY = position.y();
        }

        var dx = velocity.x() > 0 ? velocity.x() - 1: 0;
        var dy = velocity.y() - 1;

        velocity = new Point(dx, dy);
    }

    public int getHighestY() {
        return highestY;
    }

    public Point getPosition() {
        return position;
    }
}
