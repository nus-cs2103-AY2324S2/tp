package seedu.address.testutil;

import java.time.LocalDateTime;

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
        descriptor.setDateTime(note.getDateTime());
        descriptor.setDescription(note.getDescription());
    }

    /**
     * Sets the {@code date time} of the {@code EditNoteDescriptor} that we are building.
     */
    public EditNoteDescriptorBuilder withDateTime(LocalDateTime dateTime) {
        descriptor.setDateTime(dateTime);
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
