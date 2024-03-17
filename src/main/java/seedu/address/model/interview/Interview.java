package seedu.address.model.interview;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.address.model.person.Person;

/**
 * Represents an Interview in the talent tracker.
 */
public class Interview {
    private Person applicant;
    private Person interviewer;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String description;

    /**
     * Represents a Interview in the address book.
     */
    public Interview(Person applicant, Person interviewer, LocalDate date, LocalTime startTime,
                     LocalTime endTime, String description) {
        this.applicant = applicant;
        this.interviewer = interviewer;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    public Person getApplicant() {
        return applicant;
    }

    public Person getInterviewer() {
        return interviewer;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Returns true if both interviews of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two interviews.
     */
    public boolean isSameInterview(Interview otherInterview) {
        boolean applicantMatch = otherInterview.applicant.equals(this.applicant);
        boolean interviewerMatch = otherInterview.interviewer.equals(this.interviewer);
        boolean dateMatch = otherInterview.date.equals(this.date);
        boolean timeMatch = otherInterview.startTime.equals(this.startTime)
                && otherInterview.endTime.equals(this.endTime);

        return applicantMatch && interviewerMatch && dateMatch && timeMatch;
    }

    /**
     * Returns true if both Interviews have the same fields.
     * This defines a stronger notion of equality between two Interviews.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Interview)) {
            return false;
        }

        Interview otherInterview = (Interview) other;

        boolean applicantMatch = otherInterview.applicant.equals(this.applicant);
        boolean interviewerMatch = otherInterview.interviewer.equals(this.interviewer);
        boolean dateMatch = otherInterview.date.equals(this.date);
        boolean timeMatch = otherInterview.startTime.equals(this.startTime)
                && otherInterview.endTime.equals(this.endTime);

        return applicantMatch && interviewerMatch && dateMatch && timeMatch;
    }

    @Override
    public String toString() {
        return " ------Interview------" + '\n' + "Applicant: " + applicant.getName() + "\nInterviewer: "
                + interviewer.getName() + "\nDate: " + date.toString() + "\nStart: " + startTime.toString() + " End: "
                 + endTime.toString() + "\nDescription: " + description;
    }
}
