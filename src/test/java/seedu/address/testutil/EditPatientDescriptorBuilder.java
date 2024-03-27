package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.model.patient.EditPatientDescriptor;
import seedu.address.model.patient.Event;
import seedu.address.model.patient.FamilyCondition;
import seedu.address.model.patient.FoodPreference;
import seedu.address.model.patient.Hobby;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.PatientHospitalId;
import seedu.address.model.patient.PreferredName;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPatientDescriptor objects.
 */
public class EditPatientDescriptorBuilder {

    private EditPatientDescriptor descriptor;

    public EditPatientDescriptorBuilder() {
        descriptor = new EditPatientDescriptor();
    }

    public EditPatientDescriptorBuilder(EditPatientDescriptor descriptor) {
        this.descriptor = new EditPatientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPatientDescriptor} with fields containing {@code patient}'s details
     */
    public EditPatientDescriptorBuilder(Patient patient) {
        descriptor = new EditPatientDescriptor();
        descriptor.setPatientHospitalId(patient.getPatientHospitalId());
        descriptor.setName(patient.getName());
        descriptor.setPreferredName(patient.getPreferredName());
        descriptor.setFoodPreference(patient.getFoodPreference());
        descriptor.setFamilyCondition(patient.getFamilyCondition());
        descriptor.setHobby(patient.getHobby());
        descriptor.setTags(patient.getTags());
    }

    /**
     * Sets the {@code PatientHospitalId} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withPatientHospitalId(String id) {
        descriptor.setPatientHospitalId(new PatientHospitalId(id));
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code PreferredName} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withPreferredName(String preferredName) {
        descriptor.setPreferredName(new PreferredName(preferredName));
        return this;
    }

    /**
     * Sets the {@code FoodPreference} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withFoodPreference(String food) {
        descriptor.setFoodPreference(new FoodPreference(food));
        return this;
    }

    /**
     * Sets the {@code FamilyCondition} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withFamilyCondition(String familyCondition) {
        descriptor.setFamilyCondition(new FamilyCondition(familyCondition));
        return this;
    }

    /**
     * Sets the {@code Hobby} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withHobby(String hobby) {
        descriptor.setHobby(new Hobby(hobby));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPatientDescriptor}
     * that we are building.
     */
    public EditPatientDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Parses the name and dateTimeStr into a {@code Set<Event>} and set it to the
     * {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withEvent(String name, String dateTimeStr) {
        Set<Event> events = new HashSet<>();
        events.add(new Event(name, dateTimeStr));
        descriptor.setEvents(events);
        return this;
    }


    public EditPatientDescriptor build() {
        return descriptor;
    }
}
