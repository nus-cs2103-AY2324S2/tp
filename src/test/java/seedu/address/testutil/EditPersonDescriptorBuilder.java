package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.BirthDate;
import seedu.address.model.person.DrugAllergy;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.illness.Illness;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setGender(person.getGender());
        descriptor.setBirthDate(person.getBirthDate());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setDrugAllergy(person.getDrugAllergy());
        descriptor.setIllnesses(person.getIllnesses());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withGender(String gender) {
        descriptor.setGender(new Gender(gender));
        return this;
    }

    /**
     * Sets the {@code BirthDate} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withBirthDate(String birthDate) {
        descriptor.setBirthDate(new BirthDate(birthDate));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code DrugAllergy} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withDrugAllergy(String drugAllergy) {
        descriptor.setDrugAllergy(new DrugAllergy(drugAllergy));
        return this;
    }

    /**
     * Parses the {@code illnesses} into a {@code Set<Illness>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withIllnesses(String... illnesses) {
        Set<Illness> illnessSet = Stream.of(illnesses).map(Illness::new).collect(Collectors.toSet());
        descriptor.setIllnesses(illnessSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
