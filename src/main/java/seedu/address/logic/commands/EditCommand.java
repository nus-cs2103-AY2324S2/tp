package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FAMILY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOBBY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRENAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.FamilyCondition;
import seedu.address.model.patient.FoodPreference;
import seedu.address.model.patient.Hobby;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.PatientHospitalId;
import seedu.address.model.patient.PreferredName;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing patient in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the patient identified "
            + "by the index number used in the displayed patient list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_PID + "PATIENT HOSPITAL ID] "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PRENAME + "PREFERRED NAME] "
            + "[" + PREFIX_FOOD + "FOOD PREFERENCE] "
            + "[" + PREFIX_FAMILY + "FAMILY CONDITION] "
            + "[" + PREFIX_HOBBY + "HOBBY] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_FOOD + "Laksa "
            + PREFIX_FAMILY + "Sister moved to Indonesia";

    public static final String MESSAGE_EDIT_PATIENT_SUCCESS = "Edited Patient: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in the address book.";

    private final Index index;
    private final EditPatientDescriptor editPatientDescriptor;

    /**
     * @param index of the patient in the filtered patient list to edit
     * @param editPatientDescriptor details to edit the patient with
     */
    public EditCommand(Index index, EditPatientDescriptor editPatientDescriptor) {
        requireNonNull(index);
        requireNonNull(editPatientDescriptor);

        this.index = index;
        this.editPatientDescriptor = new EditPatientDescriptor(editPatientDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(index.getZeroBased());
        Patient editedPatient = createEditedPatient(patientToEdit, editPatientDescriptor);

        if (!patientToEdit.isSamePatient(editedPatient) && model.hasPatient(editedPatient)) {
            throw new CommandException(MESSAGE_DUPLICATE_PATIENT);
        }

        model.setPatient(patientToEdit, editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_PATIENT_SUCCESS, Messages.format(editedPatient)));
    }

    /**
     * Creates and returns a {@code Patient} with the details of {@code patientToEdit}
     * edited with {@code editPatientDescriptor}.
     */
    private static Patient createEditedPatient(Patient patientToEdit, EditPatientDescriptor editPatientDescriptor) {
        assert patientToEdit != null;

        PatientHospitalId originalPatientHospitalId = editPatientDescriptor.getPatientHospitalId()
            .orElse(patientToEdit.getPatientHospitalId());
        Name updatedName = editPatientDescriptor.getName().orElse(patientToEdit.getName());
        PreferredName updatedPreferredName = editPatientDescriptor.getPreferredName()
            .orElse(patientToEdit.getPreferredName());
        FoodPreference updatedFoodPreference = editPatientDescriptor.getFoodPreference()
            .orElse(patientToEdit.getFoodPreference());
        FamilyCondition updatedFamilyCondition = editPatientDescriptor.getFamilyCondition()
            .orElse(patientToEdit.getFamilyCondition());
        Hobby updatedHobby = editPatientDescriptor.getHobby().orElse(patientToEdit.getHobby());
        Set<Tag> updatedTags = editPatientDescriptor.getTags().orElse(patientToEdit.getTags());

        return new Patient(originalPatientHospitalId, updatedName, updatedPreferredName, updatedFoodPreference,
            updatedFamilyCondition, updatedHobby, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && editPatientDescriptor.equals(otherEditCommand.editPatientDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editPatientDescriptor", editPatientDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the patient with. Each non-empty field value will replace the
     * corresponding field value of the patient.
     */
    public static class EditPatientDescriptor {

        private PatientHospitalId patientHospitalId;
        private Name name;
        private PreferredName preferredName;
        private FoodPreference foodPreference;
        private FamilyCondition familyCondition;
        private Hobby hobby;
        private Set<Tag> tags;

        public EditPatientDescriptor() {}

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
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(patientHospitalId, name, preferredName, foodPreference, familyCondition,
                hobby, tags);
        }

        /**
         * Returns true if patientHospitalId is edited.
         */
        public boolean isPatientHospitalIdEdited() {
            return patientHospitalId != null;
        }

        /**
         * Sets the flag indicating that patientHospitalId is edited.
         */
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

        public void setFoodPreference(FoodPreference food) {
            this.foodPreference = food;
        }

        public Optional<FoodPreference> getFoodPreference() {
            return Optional.ofNullable(foodPreference);
        }

        public void setFamilyCondition(FamilyCondition condition) {
            this.familyCondition = condition;
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
            return Objects.equals(name, otherEditPatientDescriptor.name)
                    && Objects.equals(preferredName, otherEditPatientDescriptor.preferredName)
                    && Objects.equals(foodPreference, otherEditPatientDescriptor.foodPreference)
                    && Objects.equals(familyCondition, otherEditPatientDescriptor.familyCondition)
                    && Objects.equals(hobby, otherEditPatientDescriptor.hobby)
                    && Objects.equals(tags, otherEditPatientDescriptor.tags);
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
                .toString();
        }
    }
}
