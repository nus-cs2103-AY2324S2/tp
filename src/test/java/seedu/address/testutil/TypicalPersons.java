package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DRUG_ALLERGY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DRUG_ALLERGY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ILLNESS_GENETIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ILLNESS_INFECTIOUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.note.Description;
import seedu.address.model.person.note.Note;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withNric("S1234567A")
            .withName("Alice Pauline")
            .withGender("F")
            .withBirthDate("01-02-2000")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withDrugAllergy("No allergy")
            .withIllnesses("Infectious Disease").withNotes(new Note[] {
                    new Note(LocalDateTime.of(2024, 2, 19, 21, 30),
                    new Description("General Flu")),
                    new Note(LocalDateTime.of(2024, 2, 28, 8, 30),
                    new Description("Headache")),
        }).build();
    public static final Person BENSON = new PersonBuilder()
            .withNric("S1234567B")
            .withName("Benson Meier")
            .withGender("M")
            .withBirthDate("01-03-1999")
            .withEmail("johnd@example.com")
            .withPhone("98765432")
            .withDrugAllergy("Penicillin")
            .withIllnesses("Infectious Disease")
            .withNotes(new Note[] {
                    new Note(LocalDateTime.of(2024, 2, 20, 15, 30),
                            new Description("Joint pain assessment")),
                    new Note(LocalDateTime.of(2024, 4, 4, 10, 30),
                            new Description("Post-surgery checkup")),
                    new Note(LocalDateTime.of(2024, 5, 19, 17, 0),
                            new Description("Sports injury follow-up")),
        }).build();
    public static final Person CARL = new PersonBuilder()
            .withNric("T1234567A")
            .withName("Carl Kurz")
            .withGender("Prefer not to say")
            .withBirthDate("04-10-1998")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withDrugAllergy("No allergy").build();
    public static final Person DANIEL = new PersonBuilder()
            .withNric("T1234567B")
            .withName("Daniel Meier")
            .withGender("M")
            .withBirthDate("02-06-2001")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withDrugAllergy("Antibiotics")
            .withIllnesses("Chronic Condition").build();
    public static final Person ELLE = new PersonBuilder()
            .withNric("T1234567C")
            .withName("Elle Meyer")
            .withGender("F")
            .withBirthDate("03-02-1997")
            .withPhone("9482224")
            .withEmail("werner@example.com").build();
    public static final Person FIONA = new PersonBuilder()
            .withNric("F1234567A")
            .withName("Fiona Kunz")
            .withGender("F")
            .withBirthDate("03-06-1996")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .build();
    public static final Person GEORGE = new PersonBuilder()
            .withNric("F1234567B")
            .withName("George Best")
            .withGender("M")
            .withBirthDate("01-03-2002")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withIllnesses("Genetic Disorder").build();

    // Manually added
    public static final Person HOON = new PersonBuilder()
            .withNric("F1234567C")
            .withGender("M")
            .withBirthDate("04-11-2000")
            .withName("Hoon Meier")
            .withPhone("8482424")
            .withEmail("stefan@example.com")
            .withIllnesses("Nutritional Deficiency").build();
    public static final Person IDA = new PersonBuilder()
            .withNric("G1234567A")
            .withName("Ida Mueller")
            .withGender("F")
            .withPhone("8482131")
            .withEmail("hans@example.com")
            .withDrugAllergy("Sulfonamides").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder()
            .withNric(VALID_NRIC_AMY)
            .withName(VALID_NAME_AMY)
            .withGender(VALID_GENDER_AMY)
            .withBirthDate(VALID_BIRTHDATE_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withDrugAllergy(VALID_DRUG_ALLERGY_AMY)
            .withIllnesses(VALID_ILLNESS_INFECTIOUS).build();
    public static final Person BOB = new PersonBuilder()
            .withNric(VALID_NRIC_BOB)
            .withName(VALID_NAME_BOB)
            .withGender(VALID_GENDER_BOB)
            .withBirthDate(VALID_BIRTHDATE_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withDrugAllergy(VALID_DRUG_ALLERGY_BOB)
            .withIllnesses(VALID_ILLNESS_INFECTIOUS, VALID_ILLNESS_GENETIC)
        .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {
    } // prevents instantiation

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
