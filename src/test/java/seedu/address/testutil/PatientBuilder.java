package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.patient.FamilyCondition;
import seedu.address.model.patient.FoodPreference;
import seedu.address.model.patient.Hobby;
import seedu.address.model.patient.ImportantDate;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.PatientHospitalId;
import seedu.address.model.patient.PreferredName;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Patient objects.
 */
public class PatientBuilder {

    public static final String DEFAULT_ID = "12334";
    public static final String DEFAULT_NAME = "Amy Bee Kian Ling";
    public static final String DEFAULT_PREFERRED_NAME = "Amy";
    public static final String DEFAULT_FOOD_PREFERENCE = "Hor Fun";
    public static final String DEFAULT_FAMILY_CONDITION = "Financially unstable";
    public static final String DEFAULT_HOBBY = "Singing karaoke";

    private PatientHospitalId patientHospitalId;
    private Name name;
    private PreferredName preferredName;
    private FoodPreference foodPreference;
    private FamilyCondition familyCondition;
    private Hobby hobby;
    private Set<Tag> tags;
    private Set<ImportantDate> importantDates;

    /**
     * Creates a {@code PatientBuilder} with the default details.
     */
    public PatientBuilder() {
        patientHospitalId = new PatientHospitalId(DEFAULT_ID);
        name = new Name(DEFAULT_NAME);
        preferredName = new PreferredName(DEFAULT_PREFERRED_NAME);
        foodPreference = new FoodPreference(DEFAULT_FOOD_PREFERENCE);
        familyCondition = new FamilyCondition(DEFAULT_FAMILY_CONDITION);
        hobby = new Hobby(DEFAULT_HOBBY);
        tags = new HashSet<>();
        importantDates = new HashSet<>();
    }

    /**
     * Initializes the PatientBuilder with the data of {@code patientToCopy}.
     */
    public PatientBuilder(Patient patientToCopy) {
        patientHospitalId = patientToCopy.getPatientHospitalId();
        name = patientToCopy.getName();
        preferredName = patientToCopy.getPreferredName();
        foodPreference = patientToCopy.getFoodPreference();
        familyCondition = patientToCopy.getFamilyCondition();
        hobby = patientToCopy.getHobby();
        tags = new HashSet<>(patientToCopy.getTags());
        importantDates = new HashSet<>(patientToCopy.getImportantDates());
    }

    /**
     * Sets the {@code PatientHospitalId} of the {@code Patient} that we are building.
     */
    public PatientBuilder withPatientHospitalId(String id) {
        this.patientHospitalId = new PatientHospitalId(id);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Patient} that we are building.
     */
    public PatientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code PreferredName} of the {@code Patient} that we are building.
     */
    public PatientBuilder withPreferredName(String preferredName) {
        this.preferredName = new PreferredName(preferredName);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Patient} that we are building.
     */
    public PatientBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code FoodPreference} of the {@code Patient} that we are building.
     */
    public PatientBuilder withFoodPreference(String foodPreference) {
        this.foodPreference = new FoodPreference(foodPreference);
        return this;
    }

    /**
     * Sets the {@code FamilyCondition} of the {@code Patient} that we are building.
     */
    public PatientBuilder withFamilyCondition(String familyCondition) {
        this.familyCondition = new FamilyCondition(familyCondition);
        return this;
    }

    /**
     * Sets the {@code Hobby} of the {@code Patient} that we are building.
     */
    public PatientBuilder withHobby(String hobby) {
        this.hobby = new Hobby(hobby);
        return this;
    }

    /**
     * Sets the ImportantDate of the {@code Patient} that we are building,
     * with the name and date/datetime of the event
     *
     * @param names description of important date
     * @param importantDates array of string of dates
     * @return return PatientBuilder withImportantDates
     */
    public PatientBuilder withImportantDates(String[] names, String[] importantDates) {
        this.importantDates = SampleDataUtil.getImportantDateSet(names, importantDates);
        return this;
    }

    /**
     * Builds {@code Patient} with new Patient.
     */
    public Patient build() {
        return new Patient(patientHospitalId, name, preferredName, foodPreference, familyCondition, hobby, tags,
            importantDates);
    }

}
