class SnailNumberPair implements SnailNumber {
    private SnailNumberPair parent;
    private SnailNumber[] values = new SnailNumber[2];

    @Override
    public SnailNumberPair getParent() {
        return parent;
    }

    public void setParent(SnailNumberPair parent) {
        this.parent = parent;
    }

    public void setPair(SnailNumber left, SnailNumber right) {
        values[0] = left;
        values[1] = right;
    }

    public SnailNumber left() {
        return values[0];
    }

    public SnailNumber right() {
        return values[1];
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public boolean split() {
        return values[0].split() || values[1].split();
    }

    void combineOnIndex(int index, int value) {
        var step = 0;
        var locateIndex = (index + 1) % 2;
        SnailNumber investigate = this;
        while (step < 2) {
            switch (step) {
                case 0 -> {
                    if (investigate.getParent() == null) {
                        return;
                    } else if (((SnailNumberPair)investigate.getParent()).values[index] == investigate) {
                        investigate = investigate.getParent();
                    } else {
                        step++;
                        investigate = ((SnailNumberPair)investigate.getParent()).values[index];
                    }
                }
                case 1 -> {
                    if (investigate.size() == 1) {
                        step++;
                        ((SnailNumberPrimitive) investigate).increment(value);
                    } else {
                        investigate = ((SnailNumberPair)investigate).values[locateIndex];
                    }
                }
            }
        }
    }

    void replace(SnailNumber old, SnailNumber with) {
        if (values[0] == old) {
            values[0] = with;
        } else if (values[1] == old) {
            values[1] = with;
        } else {
            throw new IllegalStateException("Replace request received for %s, but its not a child of %s.".formatted(old, this));
        }
    }

    @Override
    public boolean explode(int depth) {
        if (depth >= 4 && values[0].size() == 1 && values[1].size() == 1) {
            combineOnIndex(0, values[0].value());
            combineOnIndex(1, values[1].value());

            parent.replace(this, SnailNumber.zero(parent));
            return true;
        }

        return values[0].explode(depth + 1) || values[1].explode(depth + 1);
    }

    @Override
    public long magnitude() {
        return 3 * values[0].magnitude() + 2 * values[1].magnitude();
    }

    @Override
    public String toString() {
        return "[%s,%s]".formatted(values[0], values[1]);
    }
}
