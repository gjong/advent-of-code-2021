public enum Action {
    forward(1),
    down(1),
    up(-1);

    private final int direction;

    Action(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
}
