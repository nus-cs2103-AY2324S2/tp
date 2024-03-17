package seedu.address.model.interview;

import seedu.address.model.person.Person;

import java.time.LocalDate;
import java.time.LocalTime;

public class Interview {
    private Person applicant;
    private Person interviewer;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String description;

    public Interview(Person applicant, Person interviewer, LocalDate date, LocalTime startTime, LocalTime endTime, String description) {
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
