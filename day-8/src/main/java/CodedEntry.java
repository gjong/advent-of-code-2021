public record CodedEntry(String codedEntry) {
    public boolean matchesSegments(int count) {
        return codedEntry.length() == count;
    }

    public boolean matches(CodedEntry other) {
        return fullOverlap(other) && other.fullOverlap(this);
    }

    public boolean fullOverlap(CodedEntry other) {
        return other.codedEntry
                .chars()
                .allMatch(n -> codedEntry.contains(Character.toString(n)));
    }

    public long partialOverlap(CodedEntry other) {
        return other.codedEntry
                .chars()
                .filter(n -> !codedEntry.contains(Character.toString(n)))
                .count();
    }
}
