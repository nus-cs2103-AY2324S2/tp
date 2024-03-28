package seedu.address.storage;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Meeting;

/**
 * Jackson-friendly version of {@link Meeting}.
 */
class JsonAdaptedMeeting {

    private final LocalDate meetingDate;
    private final LocalTime meetingTime;

    private final LocalDateTime startDateTime;
    private final Duration duration;
    private final String agenda;
    private final String notes;

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given meeting details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("meetingDate") LocalDate meetingDate,
                              @JsonProperty("meetingTime") LocalTime meetingTime,
                              @JsonProperty("duration") Duration duration,
                              @JsonProperty("agenda") String agenda,
                              @JsonProperty("notes") String notes) {
        this.meetingDate = meetingDate;
        this.meetingTime = meetingTime;
        this.startDateTime = LocalDateTime.of(meetingDate, meetingTime); // Initialize new field
        this.duration = duration;
        this.agenda = agenda;
        this.notes = notes;
    }

    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        meetingDate = source.getMeetingDate();
        meetingTime = source.getMeetingTime();
        startDateTime = source.getStartDateTime(); // Get the new field
        duration = source.getDuration();
        agenda = source.getAgenda();
        notes = source.getNotes();
    }

    @JsonProperty("meetingDate")
    public LocalDate getMeetingDate() {
        return meetingDate;
    }

    @JsonProperty("meetingTime")
    public LocalTime getMeetingTime() {
        return meetingTime;
    }

    @JsonProperty("duration")
    public Duration getDuration() {
        return duration;
    }

    @JsonProperty("agenda")
    public String getAgenda() {
        return agenda;
    }

    @JsonProperty("notes")
    public String getNotes() {
        return notes;
    }

    @JsonProperty("startDateTime")
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Converts this Jackson-friendly adapted meeting object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meeting.
     */
    public Meeting toModelType() throws IllegalValueException {
        // Additional validation can be performed here
        return new Meeting(meetingDate, meetingTime, duration, agenda, notes);
    }
}
