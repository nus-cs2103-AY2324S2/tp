package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.Model;
import seedu.address.model.person.enums.InterviewerState;
import seedu.address.model.person.enums.Type;
import seedu.address.model.tag.Tag;

/**
 * Represents an Interviewer in Tether.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Interviewer extends Person {

    private final Type type = Type.INTERVIEWER;

    private InterviewerStatus status;

    /**
     * Every field must be present and not null.
     */
    public Interviewer(Name name, Phone phone, Email email, Remark remark, InterviewerStatus status, Set<Tag> tags) {
        super(name, phone, email, remark, tags);
        this.tags.add(new Tag("Interviewer"));
        this.status = status;
    }

    @Override
    public String getPersonType() {
        return type.toString();
    }

    /**
     * Changes the status of this interviewer to free only if they had an interview before.
     *
     * @param model the location of the interviewer to be edited.
     */
    public void updateCurrentStatusToReflectInterview(Model model) {
        if (status.value.contains(InterviewerState.OCCUPIED.toString())) {
            status = new InterviewerStatus(InterviewerState.FREE.toString());
        }

        /*
            Need to find this interviewer by reference equality and replace them for the change in status to reflect
            in the gui immediately
         */
        model.setPerson(this, this);
    }


    /**
     * Changes the status of this interviewer to interview with [applicant name] only if the status is free currently.
     *
     * @param model the location of the interviewer to be edited.
     * @param applicantScheduled the applicant whom this interviewer is scheduled with.
     */
    public void updateCurrentStatusToReflectInterview(Model model, Person applicantScheduled) {
        if (status.value.equals(InterviewerState.FREE.toString())) {
            status = new InterviewerStatus(InterviewerState.OCCUPIED + " " + applicantScheduled.getName());
        }

        /*
            Need to find this interviewer by reference equality and replace them for the change in status to reflect
            in the gui immediately
         */
        model.setPerson(this, this);
    }

    @Override
    public String getCurrentStatus() {
        return status.toString();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
