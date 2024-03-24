package seedu.address.model.person;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Represents a meeting with a specific date, time, duration, agenda, and optional notes.
 */
public class Meeting {
    private LocalDate meetingDate;
    private LocalTime meetingTime;

    private LocalDateTime startDateTime; // New field
    private Duration duration;
    private String agenda;
    private String notes; //make it optional

    /**
     * Constructs a new Meeting object with the specified details.
     *
     * @param meetingDate   The date of the meeting.
     * @param meetingTime   The time of the meeting.
     * @param duration      The duration of the meeting.
     * @param agenda        The agenda of the meeting.
     * @param notes         Optional notes for the meeting.
     */
    public Meeting(LocalDate meetingDate, LocalTime meetingTime, Duration duration, String agenda, String notes) {
        this.meetingDate = meetingDate;
        this.meetingTime = meetingTime;
        this.duration = duration;
        this.agenda = agenda;
        this.notes = notes;
        this.startDateTime = LocalDateTime.of(meetingDate, meetingTime); // Initialize new field
    }


    public LocalDate getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(LocalDate meetingDate) {
        this.meetingDate = meetingDate;
    }

    public LocalTime getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(LocalTime meetingTime) {
        this.meetingTime = meetingTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingDate, meetingTime, duration, agenda, notes);
    }

    @Override
    public String toString() {
        return "Meeting{"
                + "meetingDate=" + meetingDate
                + ", meetingTime=" + meetingTime
                + ", duration=" + duration
                + ", agenda='" + agenda + '\''
                + ", notes='" + notes + '\''
                + '}';
    }

    // In Meeting class
    /**
     * Checks if this meeting overlaps with another meeting.
     *
     * @param other The other meeting to check for overlap.
     * @return True if there is an overlap, false otherwise.
     */
    public boolean overlapsWith(Meeting other) {
        LocalDateTime start = LocalDateTime.of(this.getMeetingDate(), this.getMeetingTime());
        LocalDateTime end = start.plus(this.getDuration());
        LocalDateTime otherStart = LocalDateTime.of(other.getMeetingDate(), other.getMeetingTime());
        LocalDateTime otherEnd = otherStart.plus(other.getDuration());

        return start.isBefore(otherEnd) && otherStart.isBefore(end);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Meeting meeting = (Meeting) obj;
        return Objects.equals(meetingDate, meeting.meetingDate)
                && Objects.equals(meetingTime, meeting.meetingTime)
                && Objects.equals(duration, meeting.duration)
                && Objects.equals(agenda, meeting.agenda)
                && Objects.equals(notes, meeting.notes);
    }
}
