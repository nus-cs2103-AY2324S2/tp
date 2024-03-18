package seedu.address.model.patient;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Stores the details to edit the patient with. Each non-empty field value will replace the
 * corresponding field value of the patient.
 */
public class EditPatientDescriptor {
    private PatientHospitalId patientHospitalId;
    private Name name;
    private PreferredName preferredName;
    private FoodPreference foodPreference;
    private FamilyCondition familyCondition;
    private Hobby hobby;
    private Set<Tag> tags;
    private Set<ImportantDate> importantDate;

    public EditPatientDescriptor() {
    }

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditPatientDescriptor(EditPatientDescriptor toCopy) {
        setPatientHospitalId(toCopy.patientHospitalId);
        setName(toCopy.name);
        setPreferredName(toCopy.preferredName);
        setFoodPreference(toCopy.foodPreference);
        setFamilyCondition(toCopy.familyCondition);
        setHobby(toCopy.hobby);
        setTags(toCopy.tags);
        setImportantDate(toCopy.importantDate);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(patientHospitalId, name, preferredName, foodPreference, familyCondition,
            hobby, tags, importantDate);
    }

    public void setPatientHospitalId(PatientHospitalId id) {
        this.patientHospitalId = id;
    }

    public Optional<PatientHospitalId> getPatientHospitalId() {
        return Optional.ofNullable(patientHospitalId);
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    public void setPreferredName(PreferredName preferredName) {
        this.preferredName = preferredName;
    }

    public Optional<PreferredName> getPreferredName() {
        return Optional.ofNullable(preferredName);
    }

    public void setFoodPreference(FoodPreference foodPreference) {
        this.foodPreference = foodPreference;
    }

    public Optional<FoodPreference> getFoodPreference() {
        return Optional.ofNullable(foodPreference);
    }

    public void setFamilyCondition(FamilyCondition familyCondition) {
        this.familyCondition = familyCondition;
    }

    public Optional<FamilyCondition> getFamilyCondition() {
        return Optional.ofNullable(familyCondition);
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }

    public Optional<Hobby> getHobby() {
        return Optional.ofNullable(hobby);
    }

    /**
     * Sets {@code tags} to this object's {@code tags}.
     * A defensive copy of {@code tags} is used internally.
     */
    public void setTags(Set<Tag> tags) {
        this.tags = (tags != null) ? new HashSet<>(tags) : null;
    }

    /**
     * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code tags} is null.
     */
    public Optional<Set<Tag>> getTags() {
        return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
    }

    public Optional<Set<ImportantDate>> getImportantDates() {
        return importantDate != null ? Optional.of(Collections.unmodifiableSet(importantDate)) : Optional.empty();
    }

    public void setImportantDate(Set<ImportantDate> importantDate) {
        this.importantDate = importantDate != null ? new HashSet<>(importantDate) : null;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPatientDescriptor)) {
            return false;
        }

        EditPatientDescriptor otherEditPatientDescriptor = (EditPatientDescriptor) other;
        return Objects.equals(patientHospitalId, otherEditPatientDescriptor.patientHospitalId)
            && Objects.equals(name, otherEditPatientDescriptor.name)
            && Objects.equals(preferredName, otherEditPatientDescriptor.preferredName)
            && Objects.equals(foodPreference, otherEditPatientDescriptor.foodPreference)
            && Objects.equals(familyCondition, otherEditPatientDescriptor.familyCondition)
            && Objects.equals(hobby, otherEditPatientDescriptor.hobby)
            && Objects.equals(tags, otherEditPatientDescriptor.tags)
            && Objects.equals(importantDate, otherEditPatientDescriptor.importantDate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("patientHospitalId", patientHospitalId)
            .add("name", name)
            .add("preferredName", preferredName)
            .add("foodPreference", foodPreference)
            .add("familyCondition", familyCondition)
            .add("hobby", hobby)
            .add("tags", tags)
            .add("importantDate", importantDate)
            .toString();
    }
}
