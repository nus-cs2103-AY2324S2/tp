package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;


/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditMeetingDescriptorBuilder {

    private EditMeetingCommand.EditMeetingDescriptor descriptor;

    public EditMeetingDescriptorBuilder() {
        descriptor = new EditMeetingCommand.EditMeetingDescriptor();
    }

    public EditMeetingDescriptorBuilder(EditMeetingCommand.EditMeetingDescriptor descriptor) {
        this.descriptor = new EditMeetingCommand.EditMeetingDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditMeetingDescriptor} with fields containing {@code person}'s details
     */
    public EditMeetingDescriptorBuilder(Meeting meeting) {
        descriptor = new EditMeetingCommand.EditMeetingDescriptor();
        descriptor.setClient(meeting.getClient());
        descriptor.setDateTime(meeting.getDateTime());
        descriptor.setDescription(meeting.getDescription());
    }

    /**
     * Sets the {@code Name} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withDescription(String name) {
        descriptor.setDescription(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withDateTime(String dateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime parsedDateTime = LocalDateTime.parse(dateTime, formatter);

            descriptor.setDateTime(parsedDateTime);
        } catch (DateTimeParseException e) {
            descriptor.setDateTime(currentDateTime);
        }
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withClient(Person client) {
        descriptor.setClient(client);
        return this;
    }

    public EditMeetingCommand.EditMeetingDescriptor build() {
        return descriptor;
    }
}
