package seedu.address.model.person.note;

import seedu.address.commons.util.ToStringBuilder;

import java.time.LocalDateTime;
import java.util.Objects;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Note {
    private final LocalDateTime dateTime;
    private final Description description;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (!Objects.equals(dateTime, note.dateTime)) return false;
        return Objects.equals(description, note.description);
    }

    @Override
    public int hashCode() {
        int result = dateTime != null ? dateTime.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("dateTime", dateTime)
                .add("description", description)
                .toString();
    }
}
