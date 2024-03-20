package seedu.address.testutil;

import seedu.address.logic.commands.NoteCommand.NoteStartupDescriptor;
import seedu.address.model.startup.Note;

/**
 * A utility class to help with building NoteStartupDescriptor objects, focusing exclusively on the note attribute.
 */
public class NoteStartupDescriptorBuilder {

    private final NoteStartupDescriptor descriptor;

    public NoteStartupDescriptorBuilder() {
        descriptor = new NoteStartupDescriptor();
    }

    /**
     * Initializes the builder with the note of a given startup. This is particularly useful for tests that require
     * a startup's existing note to be modified.
     *
     * @param startupNote The note of the startup to be used as a starting point.
     * @return A NoteStartupDescriptorBuilder instance for chaining method calls.
     */
    public NoteStartupDescriptorBuilder withNote(String startupNote) {
        descriptor.setNote(new Note(startupNote));
        return this;
    }

    /**
     * Builds and returns a NoteStartupDescriptor object.
     *
     * @return The constructed NoteStartupDescriptor with set note attribute.
     */
    public NoteStartupDescriptor build() {
        return descriptor;
    }
}
