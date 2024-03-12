package seedu.address.model.person.note;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents an Appointment Note.
 */
public class Note {
    private final LocalDateTime dateTime;
    private final Description description;

    /**
     * Constructs a {@code Note}.
     *
     * @param dateTime A date and time.
     * @param description A valid description.
     */
    public Note(LocalDateTime dateTime, Description description) {
        requireAllNonNull(dateTime, description);

        this.dateTime = dateTime;
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Description getDescription() {
        return description;
    }

    public String getDateTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }

    /**
     * Returns true if both notes have the same dateTime.
     * This defines a weaker notion of equality between two notes.
     */
    public boolean isSameNote(Note otherNote) {
        // TODO: To group by patient index to differentiate
        if (otherNote == this) {
            return true;
        }

        return otherNote != null
                && otherNote.getDateTime().equals(this.getDateTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Note note = (Note) o;

        if (!Objects.equals(dateTime, note.dateTime)) {
            return false;
        }

        return Objects.equals(description, note.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, description);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("dateTime", dateTime)
                .add("description", description)
                .toString();
    }
}
