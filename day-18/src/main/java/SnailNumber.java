import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public interface SnailNumber {

    default int size() {
        return 0;
    }

    SnailNumber getParent();

    default void setParent(SnailNumberPair parent) {}

    default int value() {
        return 0;
    }

    default long magnitude() {
        return value();
    }

    default SnailNumber add(SnailNumber fishNumber) {
        var newPair = new SnailNumberPair();
        newPair.setPair(this, fishNumber);
        setParent(newPair);
        fishNumber.setParent(newPair);
        return newPair;
    }

    default boolean split() {
        return false;
    }

    default boolean explode(int depth) {
        return false;
    }

    default void reduce() {
        boolean isComplete = false;
        while (!isComplete) {
            if (explode(0)) {
                continue;
            }

            if (split()) {
                continue;
            }

            isComplete = true;
        }
    }

    static SnailNumber of(int value) {
        return new SnailNumberPrimitive(value);
    }

    static SnailNumber of(SnailNumberPair parent, double value) {
        var newValue = new SnailNumberPrimitive((int) value);
        newValue.setParent(parent);
        return newValue;
    }

    static SnailNumber zero(SnailNumberPair parent) {
        var newFishValue = new SnailNumberPrimitive(0);
        newFishValue.setParent(parent);
        return newFishValue;
    }

    static SnailNumber generateSnailNumber(String line) {
        List<Character> characters = new ArrayList<>(line.length());
        for (char character : line.toCharArray())
            characters.add(character);

        return generateSnailNumber(characters);
    }

    static SnailNumber generateSnailNumber(List<Character> line) {
        while (!line.isEmpty()) {
            char nextCharacter = line.remove(0);

            if (nextCharacter == '[') {
                var fishPair = new SnailNumberPair();
                var leftValue = generateSnailNumber(line);
                leftValue.setParent(fishPair);

                line.remove(0); // remove ,
                var rightValue = generateSnailNumber(line);
                rightValue.setParent(fishPair);
                line.remove(0); // remove ]

                fishPair.setPair(leftValue, rightValue);
                return fishPair;
            } else {
                return new SnailNumberPrimitive(parseInt(String.valueOf(nextCharacter)));
            }
        }

        return null;
    }
}
