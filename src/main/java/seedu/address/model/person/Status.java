package seedu.address.model.person;

import seedu.address.model.person.enums.ApplicantState;
import seedu.address.model.person.enums.InterviewerState;

/**
 * Represents a Person's status in the Tether.
 */
public abstract class Status {
    public static final String MESSAGE_USAGE = "For applicants, status can only be one of "
            + ApplicantState.STAGE_ONE + ", "
            + ApplicantState.STAGE_TWO + ", "
            + ApplicantState.STAGE_THREE + ", "
            + ApplicantState.OUTCOME_ONE + ", "
            + ApplicantState.OUTCOME_TWO + ", or "
            + ApplicantState.OUTCOME_THREE + ".\nFor interviewers, status can only be one of "
            + InterviewerState.FREE + ", or "
            + InterviewerState.OCCUPIED + " [applicant name].";
}
