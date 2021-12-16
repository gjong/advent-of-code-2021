import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

class EncodedOperation {
    private final int version;
    private final int type;

    private long literalValue;
    private List<EncodedOperation> subOperations;

    EncodedOperation(int version, int type) {
        this.version = version;
        this.type = type;
        this.subOperations = new ArrayList<>();
    }

    public long getLiteralValue() {
        return literalValue;
    }

    public int getType() {
        return type;
    }

    public int getVersion() {
        return version;
    }

    public List<EncodedOperation> getSubOperations() {
        return List.copyOf(subOperations);
    }

    public static EncodedOperation parseOperations(String hexOperation) {
        var bitRepresentation = new StringBuilder();
        for (var hex : hexOperation.toCharArray()) {
            var bits = new BigInteger(String.valueOf(hex), 16).toString(2);
            bitRepresentation.append("%4s".formatted(bits).replace(' ', '0'));
        }

        List<EncodedOperation> operations = new ArrayList<>();
        processBitString(bitRepresentation.toString(), 0, operations);
        return operations.get(0);
    }

    private static int processBitString(String bitRepresentation, int startPosition, List<EncodedOperation> operations) {
        var index = startPosition;
        var version = parseInt(bitRepresentation.substring(index, index + 3), 2);
        var type = parseInt(bitRepresentation.substring(index + 3, index + 6), 2);

        var operation = new EncodedOperation(version, type);
        operations.add(operation);

        index += 6;
        if (type == 4) {
            // literal value
            var notAtEnd = true;
            var bitBuilder = new StringBuilder();

            while (notAtEnd) {
                notAtEnd = bitRepresentation.charAt(index) == '1';
                bitBuilder.append(bitRepresentation, index + 1, index + 5);
                index += 5;
            }

            operation.literalValue = parseLong(bitBuilder.toString(), 2);
        } else {
            // sub operation
            var lengthTypeId = bitRepresentation.charAt(index);

            index++;
            if (lengthTypeId == '0') {
                var bitLength = parseInt(bitRepresentation.substring(index, index + 15), 2);
                index += 15;

                var endPosition = index + bitLength;
                while (index < endPosition) {
                    index = processBitString(bitRepresentation, index, operation.subOperations);
                }
            } else {
                var subOperationCount = parseInt(bitRepresentation.substring(index, index + 11), 2);
                index += 11;
                for (var currentSub = 0; currentSub < subOperationCount; currentSub++) {
                    index = processBitString(bitRepresentation, index, operation.subOperations);
                }
            }
        }

        return index;
    }
}
