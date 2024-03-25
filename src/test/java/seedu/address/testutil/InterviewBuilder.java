package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.interview.Interview;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.ApplicantStatus;
import seedu.address.model.person.Email;
import seedu.address.model.person.Interviewer;
import seedu.address.model.person.InterviewerStatus;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.enums.ApplicantState;
import seedu.address.model.person.enums.InterviewerState;
import seedu.address.model.tag.Tag;

/**
 *
 */
public class InterviewBuilder {
    public static final String DEFAULT_DESCRIPTION = "technical interview";
    public static final LocalDate DEFAULT_DATE = LocalDate.of(2024, 01, 01);
    public static final LocalTime DEFAULT_START_TIME = LocalTime.of(10, 00);
    public static final LocalTime DEFAULT_END_TIME = LocalTime.of(12, 59);

    private Name applicantName = new Name("head");
    private Name interviewName = new Name("cube");
    private Phone applicantPhone = new Phone("12345678");
    private Phone interviewerPhone = new Phone("87654321");
    private Email applicantEmail = new Email("head@cube.com");
    private Email interviewerEmail = new Email("cube@head.com");
    private Remark emptyRemark = new Remark("");
    private Set<Tag> tags = new HashSet<>();

    private Applicant applicant = new Applicant(applicantName, applicantPhone, applicantEmail, emptyRemark,
            new ApplicantStatus(ApplicantState.STAGEONE.toString()), tags);
    private Interviewer interviewer = new Interviewer(interviewName,
            interviewerPhone, interviewerEmail, emptyRemark, new InterviewerStatus(InterviewerState.FREE.toString()),
            tags);

    /**
     *
     */
    public InterviewBuilder() {

    }

    /**
     *
     */
    public Interview buildInterview() {
        return new Interview(applicant, interviewer, DEFAULT_DATE, DEFAULT_START_TIME,
                DEFAULT_END_TIME, DEFAULT_DESCRIPTION);
    }

}
