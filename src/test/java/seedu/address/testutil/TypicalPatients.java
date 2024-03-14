package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_FAMILY_CONDITION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FAMILY_CONDITION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FOOD_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FOOD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOBBY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOBBY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERRED_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERRED_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DEPRESSION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DIABETES;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.patient.Patient;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalPatients {

    public static final Patient ALICE = new PatientBuilder().withPatientHospitalId("12234").withName("Alice Pauline")
        .withPreferredName("Alice").withFoodPreference("Duck rice")
        .withFamilyCondition("Have 2 daughters working overseas").withHobby("Watching Hong Kong drama")
        .withTags("friends").build();
    public static final Patient BENSON = new PatientBuilder().withPatientHospitalId("12235").withName("Benson Meier")
        .withPreferredName("Benson").withFoodPreference("Kampung Fried Rice").withFamilyCondition("Wife in ICU")
        .withHobby("Listen to Coldplay songs").withTags("owesMoney", "friends").build();
    public static final Patient CARL = new PatientBuilder().withPatientHospitalId("12236").withName("Carl Kurz")
        .withPreferredName("Ah Carl").withFoodPreference("Sambal fish").withFamilyCondition("Has no children")
        .withHobby("Likes to play mahjong").build();
    public static final Patient DANIEL = new PatientBuilder().withPatientHospitalId("12237").withName("Daniel Meier")
        .withPreferredName("Ah Da").withFoodPreference("Steak").withFamilyCondition("Nieces not around Singapore")
        .withHobby("Ride bicycle").withTags("friends").build();
    public static final Patient ELLE = new PatientBuilder().withPatientHospitalId("12238").withName("Elle Meyer")
        .withPreferredName("Elle").withFoodPreference("Maggie Goreng").withFamilyCondition("Husband working overseas")
        .withHobby("Dancing").build();
    public static final Patient FIONA = new PatientBuilder().withPatientHospitalId("12239").withName("Fiona Kunz")
        .withPreferredName("Ms Fi").withFoodPreference("Fish soup without milk")
        .withFamilyCondition("Daughter fights with her every week").withHobby("Count pebbles").build();
    public static final Patient GEORGE = new PatientBuilder().withPatientHospitalId("12240").withName("George Best")
        .withPreferredName("George").withFoodPreference("Salmon with lemon").withFamilyCondition("no children")
        .withHobby("Likes to talk with people").build();

    // Manually added
    public static final Patient HOON = new PatientBuilder().withPatientHospitalId("12241").withName("Hoon Meier")
        .withPreferredName("Ah Hoon").withFoodPreference("Char Kuey Teow").withFamilyCondition("Husband unable to walk")
        .withHobby("Reads novel").build();
    public static final Patient IDA = new PatientBuilder().withPatientHospitalId("12242").withName("Ida Mueller")
        .withPreferredName("Puan Ida").withFoodPreference("Nasi Kandang").withFamilyCondition("Children abandoned her")
        .withHobby("Plays congkak").build();

    // Manually added - Patient's details found in {@code CommandTestUtil}
    public static final Patient AMY = new PatientBuilder().withPatientHospitalId(VALID_ID_AMY).withName(VALID_NAME_AMY)
        .withPreferredName(VALID_PREFERRED_NAME_AMY).withFoodPreference(VALID_FOOD_AMY)
        .withFamilyCondition(VALID_FAMILY_CONDITION_AMY).withHobby(VALID_HOBBY_AMY)
        .withTags(VALID_TAG_DIABETES).build();
    public static final Patient BOB = new PatientBuilder().withPatientHospitalId(VALID_ID_BOB).withName(VALID_NAME_BOB)
        .withPreferredName(VALID_PREFERRED_NAME_BOB).withFoodPreference(VALID_FOOD_BOB)
        .withFamilyCondition(VALID_FAMILY_CONDITION_BOB).withHobby(VALID_HOBBY_BOB)
        .withTags(VALID_TAG_DEPRESSION, VALID_TAG_DIABETES).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPatients() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical patients.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Patient patient : getTypicalPatients()) {
            ab.addPatient(patient);
        }
        return ab;
    }

    public static List<Patient> getTypicalPatients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
