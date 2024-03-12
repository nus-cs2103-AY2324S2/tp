package seedu.address.model.doctor;

import java.time.LocalDate;

import static java.util.Objects.requireNonNull;

public class DoB {
    private final LocalDate dob;

    public DoB(String dob) {
        requireNonNull(dob);
        this.dob = LocalDate.parse(dob);
    }

    @Override
    public String toString() {
        return dob.toString();
    }
}
