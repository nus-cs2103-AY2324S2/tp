package seedu.address.logic.paynow;

/**
 * Represents a field within a {@code PaynowCode}.
 */
public final class PayNowField {
    private final int id;
    private final Object value;

    /**
     * Returns a {@code PaynowField} with the id and the valye passed into the constructor.
     */
    public PayNowField(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String toString() {
        String valueString = value.toString();
        if (value instanceof Float) {
            valueString = String.format("%.2f", value);
        } else if (value instanceof Double) {
            valueString = String.format("%.2f", value);
        }
        return String.format("%02d", id)
                + String.format("%02d", valueString.length())
                + valueString;
    }
}
