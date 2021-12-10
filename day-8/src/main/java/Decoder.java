import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class Decoder {
    private final List<CodedEntry> codedEntries;
    private final CodedEntry[] decoded;

    public Decoder(List<CodedEntry> codedEntries) {
        this.codedEntries = codedEntries;
        this.decoded = new CodedEntry[10];
        alignment();
    }

    public final void alignment() {
        // 2 and 7 are the only having unique counts
        decoded[1] = findMatchingSegment(2).get(0);
        decoded[4] = findMatchingSegment(4).get(0);
        decoded[7] = findMatchingSegment(3).get(0);
        decoded[8] = findMatchingSegment(7).get(0);

        resolve0and6and9();
        resolve2and3and5();
    }

    public int countMatch1or4or7or8(CodedEntry codedEntry) {
        var countedMatches = 0;
        var idxOfInterest = new int[] {1, 4, 7, 8};
        for (var idx : idxOfInterest) {
            if (codedEntry.matches(decoded[idx])) {
                countedMatches++;
            }
        }

        return countedMatches;
    }

    public int decode(List<CodedEntry> codedEntries) {
        var stringRepresentation = codedEntries.stream()
                .map(codedEntry -> {
                    for (var idx = 0; idx < decoded.length; idx++) {
                        if (decoded[idx].matches(codedEntry)) {
                            return idx;
                        }
                    }

                    throw new IllegalStateException("Cannot locate codedEntry " + codedEntries);
                })
                .map(String::valueOf)
                .collect(Collectors.joining());

        return parseInt(stringRepresentation);
    }

    private void resolve0and6and9() {
        for (var alignOn : findMatchingSegment(6)) {
            if (alignOn.fullOverlap(decoded[4])) {
                decoded[9] = alignOn;
            } else if (alignOn.fullOverlap(decoded[1])) {
                decoded[0] = alignOn;
            } else {
                decoded[6] = alignOn;
            }
        }
    }

    private void resolve2and3and5() {
        for (var alignOn : findMatchingSegment(5)) {
            if (alignOn.fullOverlap(decoded[7])) {
                decoded[3] = alignOn;
            } else if (alignOn.partialOverlap(decoded[6]) == 1) {
                decoded[5] = alignOn;
            } else {
                // the overlap between 2 and 6 differs by 2 segments not 1
                decoded[2] = alignOn;
            }
        }
    }

    private List<CodedEntry> findMatchingSegment(int count) {
        return codedEntries.stream()
                .filter(entry -> entry.matchesSegments(count))
                .toList();
    }

}
