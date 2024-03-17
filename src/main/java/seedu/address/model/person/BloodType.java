package seedu.address.model.person;

public class BloodType {
    private enum Type { A, B, AB, O };
    private enum Rh { POSITIVE, NEGATIVE };
    private final Type type;
    private final Rh rh;

    public BloodType(String type, String rh) {
        this.type = Type.valueOf(type);
        this.rh = Rh.valueOf(rh);
    }
}
