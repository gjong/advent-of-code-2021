import java.util.LinkedList;
import java.util.Queue;

class SlidingWindow {
    private final int windowSize;
    private Queue<Integer> stack = new LinkedList<>();

    SlidingWindow(int windowSize) {
        this.windowSize = windowSize;
    }

    public void push(int value) {
        stack.add(value);
        if (stack.size() > windowSize) {
            stack.remove();
        }
    }

    public int sum() {
        return stack.stream().reduce(0, Integer::sum);
    }

    public boolean fullStack() {
        return stack.size() == windowSize;
    }
}
