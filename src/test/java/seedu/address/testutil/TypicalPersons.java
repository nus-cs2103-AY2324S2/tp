package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALLERGIES_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALLERGIES_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOODTYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOODTYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONDITION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONDITION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COUNTRY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COUNTRY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATEOFADMISSION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATEOFADMISSION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATEOFBIRTH_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATEOFBIRTH_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DIAGNOSIS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DIAGNOSIS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEX_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEX_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SYMPTOM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SYMPTOM_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withNric("T0139571B").withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withPhone("94351253").withDateOfBirth("2001-01-01")
            .withSex("F").withStatus("HEALTHY").withEmail("test1@mail.com").withCountry("Malaysia")
            .withAllergies("rashes").withBloodType(new String[]{"B", "POSITIVE"}).withCondition("diabetes")
            .withDateOfAdmission("2023-01-01").withDiagnosis("good").withSymptom("runny nose").build();
    public static final Person BENSON = new PersonBuilder().withNric("T0439571C").withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withPhone("98765432").withDateOfBirth("2001-01-01")
            .withSex("M").withStatus("UNWELL").withEmail("test1@mail.com").withCountry("Malaysia")
            .withAllergies("rashes").withBloodType(new String[]{"B", "POSITIVE"}).withCondition("diabetes")
            .withDateOfAdmission("2023-01-01").withDiagnosis("good").withSymptom("runny nose").build();
    public static final Person CARL = new PersonBuilder().withNric("T0284994B").withName("Carl Kurz")
            .withPhone("95352563").withAddress("wall street").withDateOfBirth("2001-01-01")
            .withSex("M").withStatus("PENDING").withEmail("test1@mail.com").withCountry("Malaysia")
            .withAllergies("rashes").withBloodType(new String[]{"B", "POSITIVE"}).withCondition("diabetes")
            .withDateOfAdmission("2023-01-01").withDiagnosis("good").withSymptom("runny nose").build();
    public static final Person DANIEL = new PersonBuilder().withNric("S9839571A").withName("Daniel Meier")
            .withPhone("87652533").withAddress("10th street").withDateOfBirth("2001-01-01")
            .withSex("M").withStatus("HEALTHY").withEmail("test1@mail.com").withCountry("Malaysia")
            .withAllergies("rashes").withBloodType(new String[]{"B", "POSITIVE"}).withCondition("diabetes")
            .withDateOfAdmission("2023-01-01").withDiagnosis("good").withSymptom("runny nose").build();
    public static final Person ELLE = new PersonBuilder().withNric("S8913957B").withName("Elle Meyer")
            .withPhone("9482224").withAddress("michigan ave").withDateOfBirth("2001-01-01")
            .withSex("F").withStatus("UNWELL").withEmail("test1@mail.com").withCountry("Malaysia")
            .withAllergies("rashes").withBloodType(new String[]{"B", "POSITIVE"}).withCondition("diabetes")
            .withDateOfAdmission("2023-01-01").withDiagnosis("good").withSymptom("runny nose").build();
    public static final Person FIONA = new PersonBuilder().withNric("T0536171Z").withName("Fiona Kunz")
            .withPhone("9482427").withAddress("little tokyo").withDateOfBirth("2001-01-01")
            .withSex("F").withStatus("PENDING").withEmail("test1@mail.com").withCountry("Malaysia")
            .withAllergies("rashes").withBloodType(new String[]{"B", "POSITIVE"}).withCondition("diabetes")
            .withDateOfAdmission("2023-01-01").withDiagnosis("good").withSymptom("runny nose").build();
    public static final Person GEORGE = new PersonBuilder().withNric("T0829102Z").withName("George Best")
            .withPhone("9482442").withAddress("4th street").withDateOfBirth("2001-01-01")
            .withSex("M").withStatus("HEALTHY").withEmail("test1@mail.com").withCountry("Malaysia")
            .withAllergies("rashes").withBloodType(new String[]{"B", "POSITIVE"}).withCondition("diabetes")
            .withDateOfAdmission("2023-01-01").withDiagnosis("good").withSymptom("runny nose").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withNric("T0123071C").withName("Hoon Meier")
            .withPhone("8482424").withAddress("little india").withDateOfBirth("2001-01-01")
            .withSex("M").withStatus("HEALTHY").withEmail("test1@mail.com").withCountry("Malaysia")
            .withAllergies("rashes").withBloodType(new String[]{"B", "POSITIVE"}).withCondition("diabetes")
            .withDateOfAdmission("2023-01-01").withDiagnosis("good").withSymptom("runny nose").build();
    public static final Person IDA = new PersonBuilder().withNric("T0239521A").withName("Ida Mueller")
            .withPhone("8482131").withAddress("chicago ave").withDateOfBirth("2001-01-01")
            .withSex("F").withStatus("PENDING").withEmail("test1@mail.com").withCountry("Malaysia")
            .withAllergies("rashes").withBloodType(new String[]{"B", "POSITIVE"}).withCondition("diabetes")
            .withDateOfAdmission("2023-01-01").withDiagnosis("good").withSymptom("runny nose").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withNric(VALID_NRIC_AMY).withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withAddress(VALID_ADDRESS_AMY).withDateOfBirth(VALID_DATEOFBIRTH_AMY)
            .withSex(VALID_SEX_AMY).withStatus(VALID_STATUS_AMY).withEmail(VALID_EMAIL_AMY)
            .withCountry(VALID_COUNTRY_AMY).withAllergies(VALID_ALLERGIES_AMY)
            .withBloodType(VALID_BLOODTYPE_AMY).withCondition(VALID_CONDITION_AMY)
            .withDateOfAdmission(VALID_DATEOFADMISSION_AMY).withDiagnosis(VALID_DIAGNOSIS_AMY)
            .withSymptom(VALID_SYMPTOM_AMY).build();
    public static final Person BOB = new PersonBuilder().withNric(VALID_NRIC_BOB).withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withAddress(VALID_ADDRESS_BOB).withDateOfBirth(VALID_DATEOFBIRTH_BOB)
            .withSex(VALID_SEX_BOB).withStatus(VALID_STATUS_BOB).withEmail(VALID_EMAIL_BOB)
            .withCountry(VALID_COUNTRY_BOB).withAllergies(VALID_ALLERGIES_BOB)
            .withBloodType(VALID_BLOODTYPE_BOB).withCondition(VALID_CONDITION_BOB)
            .withDateOfAdmission(VALID_DATEOFADMISSION_BOB).withDiagnosis(VALID_DIAGNOSIS_BOB)
            .withSymptom(VALID_SYMPTOM_BOB).build();
    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER
    private TypicalPersons() {} // prevents instantiation
    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }
    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
