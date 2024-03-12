package seedu.address.model.doctor;

import seedu.address.model.person.*;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

import java.util.Date;
import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Doctor {

    // Identity fields
    private final NRIC nric;
    private final Name name;
    private final DoB dob;
    private final Phone phone;

    /**
     * Every field must be present and not null.
     */
    public Doctor(NRIC nric, Name name, DoB dob, Phone phone) {
        requireAllNonNull(nric, name, dob, phone);
        this.nric = nric;
        this.name = name;
        this.dob = dob;
        this.phone = phone;
    }

    public NRIC getNRIC() {
        return nric;
    }
    public Name getName() {
        return name;
    }
    public DoB getDoB() {
        return dob;
    }
    public Phone getPhone() {
        return phone;
    }

}
