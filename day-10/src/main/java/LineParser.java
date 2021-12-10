import com.jongsoft.lang.Collections;
import com.jongsoft.lang.collection.Sequence;

import java.util.Stack;

public class LineParser {

    private final String line;
    private Brackets invalidOn;
    private Stack<Brackets> parsingStack;

    public LineParser(String line) {
        this.line = line;
        parsingStack = new Stack<>();
        for (char instruction : line.toCharArray()) {
            var matchingOpen = Brackets.forOpenChar(instruction);
            if (matchingOpen.isPresent()) {
                parsingStack.add(matchingOpen.get());
            } else {
                var matchingClosing = Brackets.forClosingChar(instruction);
                if (matchingClosing.isPresent() && matchingClosing.get() != parsingStack.pop()) {
                    invalidOn = matchingClosing.get();
                    break;
                }
            }
        }
    }

    public boolean isValid() {
        return invalidOn == null;
    }

    public boolean isInValid() {
        return invalidOn != null;
    }

    public Brackets getInvalidOn() {
        return invalidOn;
    }

    public Sequence<Brackets> computeAutocomplete() {
        return Collections.List(parsingStack)
                .reverse();
    }

    @Override
    public String toString() {
        return String.format("%s -> autocomplete %s", line, parsingStack);
    }
}
