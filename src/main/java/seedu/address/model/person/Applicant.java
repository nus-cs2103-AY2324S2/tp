package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.Model;
import seedu.address.model.person.enums.ApplicantState;
import seedu.address.model.person.enums.Type;
import seedu.address.model.tag.Tag;

/**
 * Represents an Applicant in the Tether.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Applicant extends Person {

    private final Type type = Type.APPLICANT;
    private ApplicantStatus previousStatus;
    private ApplicantStatus currentStatus;

    /**
     * Every field must be present and not null.
     */
    public Applicant(Name name, Phone phone, Email email, Remark remark, ApplicantStatus status, Set<Tag> tags) {
        super(name, phone, email, remark, tags);
        this.tags.add(new Tag("Applicant"));
        this.currentStatus = status;
    }

    @Override
    public boolean isSamePerson(Person otherPerson) {
        return super.isSamePerson(otherPerson);
    }

    @Override
    public String getPersonType() {
        return type.toString();
    }

    /**
     * Changes the status of this applicant to pending interview.
     *
     * @param model the location of the applicant to be edited
     */
    @Override
    public void updateCurrentStatusToReflectInterview(Model model) {
        previousStatus = currentStatus;
        currentStatus = new ApplicantStatus(ApplicantState.STAGE_TWO.toString());
        /*
            Need to find this applicant by reference equality and replace them for the change in status to reflect in
            the gui immediately
         */
        model.setPerson(this, this);
    }

    /**
     * Rolls back the status of this applicant.
     *
     * @param model the location of the applicant to be edited
     */
    @Override
    public void revertCurrentStatus(Model model) {
        currentStatus = previousStatus;
        /*
            Need to find this applicant by reference equality and replace them for the change in status to reflect in
            the gui immediately
         */
        model.setPerson(this, this);
    }

    @Override
    public String getCurrentStatus() {
        return currentStatus.toString().toLowerCase();
    }

    @Override
    public String toString() {
        return super.toString() + "\nStatus: " + currentStatus.toString();
    }
}
