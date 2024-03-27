package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import seedu.address.logic.commands.EditNoteCommand.EditNoteDescriptor;
import seedu.address.model.person.note.Description;
import seedu.address.model.person.note.Note;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditNoteDescriptorBuilder {

    private EditNoteDescriptor descriptor;

    public EditNoteDescriptorBuilder() {
        descriptor = new EditNoteDescriptor();
    }

    /**
     * Returns an {@code EditNoteDescriptorBuilder} with fields containing {@code edit}'s details
     */
    public EditNoteDescriptorBuilder(Note note) {
        descriptor = new EditNoteDescriptor();
        descriptor.setDate(note.getDateTime().toLocalDate());
        descriptor.setTime(note.getDateTime().toLocalTime());
        descriptor.setDescription(note.getDescription());
    }

    /**
     * Sets the {@code date time} of the {@code EditNoteDescriptor} that we are building.
     */
    public EditNoteDescriptorBuilder withDateTime(LocalDateTime dateTime) {
        descriptor.setDate(dateTime.toLocalDate());
        descriptor.setTime(dateTime.toLocalTime());
        return this;
    }

    /**
     * Sets the {@code date} of the {@code EditNoteDescriptor} that we are building.
     */
    public EditNoteDescriptorBuilder withDate(LocalDate date) {
        descriptor.setDate(date);
        return this;
    }

    /**
     * Sets the {@code time} of the {@code EditNoteDescriptor} that we are building.
     */
    public EditNoteDescriptorBuilder withTime(LocalTime time) {
        descriptor.setTime(time);
        return this;
    }

    /**
     * Sets the {@code description} of the {@code EditNoteDescriptor} that we are building.
     */
    public EditNoteDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    public EditNoteDescriptor build() {
        return descriptor;
    }
}
