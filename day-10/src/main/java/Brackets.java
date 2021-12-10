import java.util.Optional;

public enum Brackets {
    curly('{', '}', 3,1197),
    rounded('(', ')', 1, 3),
    square('[', ']', 2, 57),
    angle('<', '>', 4, 25137);

    final char openChar;
    final char closingChar;
    final int matchValue;
    final int mismatchValue;
    Brackets(char openChar, char closingChar, int matchValue, int mismatchValue) {
        this.openChar = openChar;
        this.closingChar = closingChar;
        this.matchValue = matchValue;
        this.mismatchValue = mismatchValue;
    }

    static Optional<Brackets> forOpenChar(char openChar) {
        for (var bracket : Brackets.values()) {
            if (bracket.openChar == openChar) {
                return Optional.of(bracket);
            }
        }
        return Optional.empty();
    }

    static Optional<Brackets> forClosingChar(char closingChar) {
        for (var bracket : Brackets.values()) {
            if (bracket.closingChar == closingChar) {
                return Optional.of(bracket);
            }
        }
        return Optional.empty();
    }
}
