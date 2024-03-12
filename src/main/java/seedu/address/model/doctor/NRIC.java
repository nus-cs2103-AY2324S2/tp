package seedu.address.model.doctor;

public class NRIC {
    public final String nric;

    public NRIC(String nric) {
        this.nric = nric;
    }

    @Override
    public String toString() {
        return nric;
    }
}
