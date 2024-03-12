package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.patient.FamilyCondition;
import seedu.address.model.patient.FoodPreference;
import seedu.address.model.patient.Hobby;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.PatientHospitalId;
import seedu.address.model.patient.PreferredName;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Patient}.
 */
class JsonAdaptedPatient {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Patient's %s field is missing!";

    private final Integer patientHospitalId;
    private final String name;
    private final String preferredName;
    private final String foodPreference;
    private final String familyCondition;
    private final String hobby;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPatient} with the given patient details.
     */
    @JsonCreator
    public JsonAdaptedPatient(@JsonProperty("patientHospitalId") Integer patientHospitalId,
                              @JsonProperty("name") String name, @JsonProperty("preferredName") String preferredName,
                              @JsonProperty("foodPreference") String foodPreference,
                              @JsonProperty("familyCondition") String familyCondition,
                              @JsonProperty("hobby") String hobby,
                              @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.patientHospitalId = patientHospitalId;
        this.name = name;
        this.preferredName = preferredName;
        this.foodPreference = foodPreference;
        this.familyCondition = familyCondition;
        this.hobby = hobby;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Patient} into this class for Alex use.
     */
    public JsonAdaptedPatient(Patient source) {
        patientHospitalId = source.getPatientHospitalId().patientHospitalId;
        name = source.getName().fullName;
        preferredName = source.getPreferredName().preferredName;
        foodPreference = source.getFoodPreference().foodPreference;
        familyCondition = source.getFamilyCondition().familyCondition;
        hobby = source.getHobby().hobby;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted patient object into the model's {@code Patient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted patient.
     */
    public Patient toModelType() throws IllegalValueException {
        final List<Tag> patientTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            patientTags.add(tag.toModelType());
        }

        if (patientHospitalId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                PatientHospitalId.class.getSimpleName()));
        }

        if (!PatientHospitalId.isValidPatientHospitalId(String.valueOf(patientHospitalId))) {
            throw new IllegalValueException(PatientHospitalId.MESSAGE_CONSTRAINTS);
        }
        final PatientHospitalId modelPatientHospitalId = new PatientHospitalId(patientHospitalId);


        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (preferredName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                PreferredName.class.getSimpleName()));
        }
        if (!PreferredName.isValidPreferredName(preferredName)) {
            throw new IllegalValueException(PreferredName.MESSAGE_CONSTRAINTS);
        }
        final PreferredName modelPreferredName = new PreferredName(preferredName);

        if (foodPreference == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                FoodPreference.class.getSimpleName()));
        }
        if (!FoodPreference.isValidFood(foodPreference)) {
            throw new IllegalValueException(FoodPreference.MESSAGE_CONSTRAINTS);
        }
        final FoodPreference modelFoodPreference = new FoodPreference(foodPreference);

        if (familyCondition == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                FamilyCondition.class.getSimpleName()));
        }
        if (!FamilyCondition.isValidFamilyCondition(familyCondition)) {
            throw new IllegalValueException(FamilyCondition.MESSAGE_CONSTRAINTS);
        }
        final FamilyCondition modelFamilyCondition = new FamilyCondition(familyCondition);

        if (hobby == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Hobby.class.getSimpleName()));
        }
        if (!Hobby.isValidHobby(hobby)) {
            throw new IllegalValueException(Hobby.MESSAGE_CONSTRAINTS);
        }
        final Hobby modelHobby = new Hobby(hobby);

        final Set<Tag> modelTags = new HashSet<>(patientTags);
        return new Patient(modelPatientHospitalId, modelName, modelPreferredName, modelFoodPreference,
            modelFamilyCondition, modelHobby, modelTags);
    }

}
