class BitMask {
    private int zero;
    private int one;

    public void push(char bit) {
        if (bit == '1') {
            one++;
        } else {
            zero++;
        }
    }

    public boolean bit(boolean mostCommon) {
        if (mostCommon) {
            return zero < one;
        }

        return zero > one;
    }
}
