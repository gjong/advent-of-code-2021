class SnailNumberPrimitive implements SnailNumber {

    private SnailNumberPair parent;
    private int value;

    public SnailNumberPrimitive(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    @Override
    public SnailNumberPair getParent() {
        return parent;
    }

    @Override
    public void setParent(SnailNumberPair parent) {
        this.parent = parent;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public boolean split() {
        if (value > 9) {
            var newPair = new SnailNumberPair();
            newPair.setParent(parent);
            newPair.setPair(
                    SnailNumber.of(newPair, Math.floor(value / 2D)),
                    SnailNumber.of(newPair, Math.ceil(value / 2D)));

            if (parent.left() == this) {
                parent.setPair(newPair, parent.right());
            } else if (parent.right() == this){
                parent.setPair(parent.left(), newPair);
            } else {
                throw new IllegalStateException("Neither left nor right of my parent is me.");
            }

            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public void increment(int value) {
        this.value += value;
    }
}
