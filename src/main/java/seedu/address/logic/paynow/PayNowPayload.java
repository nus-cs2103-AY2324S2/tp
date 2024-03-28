package seedu.address.logic.paynow;

/**
 * This class represents the string information to be encoded into a QR code
 * such that users can scan with their banking applications and transfer money via PayNow.
 */
public abstract class PayNowPayload {
    private final PayNowField[] fields;

    protected PayNowPayload(PayNowField... fields) {
        this.fields = new PayNowField[fields.length];
        System.arraycopy(fields, 0, this.fields, 0, fields.length);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (PayNowField field : fields) {
            stringBuilder.append(field.toString());
        }
        return stringBuilder.toString();
    }
}
