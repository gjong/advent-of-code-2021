import java.util.ArrayList;
import java.util.List;

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

    public void setLiteralValue(long literalValue) {
        this.literalValue = literalValue;
    }

    public long getLiteralValue() {
        var subProcessor = getSubOperations()
                .stream()
                .map(EncodedOperation::getLiteralValue);

        return switch (getType()) {
            case 0 -> subProcessor.reduce(0L, Long::sum);
            case 1 -> subProcessor.reduce(1L, (value, acc) -> value * acc);
            case 2 -> subProcessor.min(Long::compareTo).orElse(0L);
            case 3 -> subProcessor.max(Long::compareTo).orElse(0L);
            case 4 -> literalValue;
            case 5 -> getSubOperations().get(0).getLiteralValue() > getSubOperations().get(1).getLiteralValue()
                    ? 1 : 0;
            case 6 -> getSubOperations().get(0).getLiteralValue() < getSubOperations().get(1).getLiteralValue()
                    ? 1 : 0;
            case 7 -> getSubOperations().get(0).getLiteralValue() == getSubOperations().get(1).getLiteralValue()
                    ? 1 : 0;
            default -> throw new IllegalStateException("Unsupported type " + getType());
        };
    }

    public int getType() {
        return type;
    }

    public int getVersion() {
        return version;
    }

    public List<EncodedOperation> getSubOperations() {
        return subOperations;
    }


}
