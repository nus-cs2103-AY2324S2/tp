package seedu.address.model.person;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Meeting {
    private LocalDate meetingDate;
    private LocalTime meetingTime;
    private Duration duration;
    private String agenda;
    private String notes;

    public Meeting(LocalDate meetingDate, LocalTime meetingTime, Duration duration, String agenda, String notes) {
        this.meetingDate = meetingDate;
        this.meetingTime = meetingTime;
        this.duration = duration;
        this.agenda = agenda;
        this.notes = notes;
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

    @Override
    public int hashCode() {
        return Objects.hash(meetingDate, meetingTime, duration, agenda, notes);
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "meetingDate=" + meetingDate +
                ", meetingTime=" + meetingTime +
                ", duration=" + duration +
                ", agenda='" + agenda + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

    // In Meeting class
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
        return Objects.equals(meetingDate, meeting.meetingDate) &&
                Objects.equals(meetingTime, meeting.meetingTime) &&
                Objects.equals(duration, meeting.duration) &&
                Objects.equals(agenda, meeting.agenda) &&
                Objects.equals(notes, meeting.notes);
    }
}

