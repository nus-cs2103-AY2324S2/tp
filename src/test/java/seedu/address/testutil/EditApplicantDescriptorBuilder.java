package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditApplicantCommand;
import seedu.address.logic.commands.EditApplicantCommand.EditApplicantDescriptor;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Role;
import seedu.address.model.applicant.Stage;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditApplicantDescriptor objects.
 */
public class EditApplicantDescriptorBuilder {

    private EditApplicantCommand.EditApplicantDescriptor descriptor;

    public EditApplicantDescriptorBuilder() {
        descriptor = new EditApplicantDescriptor();
    }

    public EditApplicantDescriptorBuilder(EditApplicantCommand.EditApplicantDescriptor descriptor) {
        this.descriptor = new EditApplicantCommand.EditApplicantDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditApplicantDescriptorBuilder(Applicant applicant) {
        descriptor = new EditApplicantCommand.EditApplicantDescriptor();
        descriptor.setName(applicant.getName());
        descriptor.setPhone(applicant.getPhone());
        descriptor.setEmail(applicant.getEmail());
        descriptor.setAddress(applicant.getAddress());
        descriptor.setRole(applicant.getRole());
        descriptor.setNote(applicant.getNote());
        descriptor.setStage(applicant.getStage());
        descriptor.setTags(applicant.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withRole(String role) {
        descriptor.setRole(new Role(role));
        return this;
    }

    /**
     * Sets the {@code Stage} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withStage(String stage) {
        descriptor.setStage(new Stage(stage));
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withNote(String note) {
        descriptor.setNote(new Note(note));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditApplicantDescriptor}
     * that we are building.
     */
    public EditApplicantDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditApplicantCommand.EditApplicantDescriptor build() {
        return descriptor;
    }
}
