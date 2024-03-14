package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Applicant} objects to be used in tests.
 */
public class TypicalApplicants {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();

    public static final Applicant ALICE_APPLICANT = new ApplicantBuilder(ALICE)
        .withRole("SWE").withStage("initial_application").build();

    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
        .withAddress("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com").withPhone("98765432").build();

    public static final Applicant BENSON_APPLICANT = new ApplicantBuilder(BENSON)
        .withRole("SWE").withStage("initial_application").build();

    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();

    public static final Applicant CARL_APPLICANT = new ApplicantBuilder(CARL)
        .withRole("UX designer").withStage("technical_assessment").build();

    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends").build();
    public static final Applicant DANIEL_APPLICANT = new ApplicantBuilder(DANIEL)
        .withRole("UX designer").withStage("technical_assessment").build();

    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Applicant ELLE_APPLICANT = new ApplicantBuilder(ELLE)
        .withRole("Backend Engineer").withStage("interview").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Applicant FIONA_APPLICANT = new ApplicantBuilder(FIONA)
        .withRole("ML Engineer").withStage("decision_and_offer").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();
    public static final Applicant GEORGE_APPLICANT = new ApplicantBuilder(GEORGE)
        .withRole("DevOps Engineer").withStage("decision_and_offer").build();

    // Manually added

    public static final Person HELLEN = new PersonBuilder().withName("Hellen Kelly").withPhone("98453242")
        .withEmail("hellen@example.com").withAddress("9th street").build();
    public static final Applicant HELLEN_APPLICANT = new ApplicantBuilder(HELLEN)
        .withRole("Cloud Engineer").withStage("initial_application").build();

    public static final Person IVAN = new PersonBuilder().withName("Ivan Lim").withPhone("97233423")
        .withEmail("ivan@example.com").withAddress("10th street").build();
    public static final Applicant IVAN_APPLICANT = new ApplicantBuilder(IVAN)
        .withRole("Network Engineer").withStage("initial_application").build();

    private TypicalApplicants() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalApplicantsAddressBook() {
        AddressBook ab = new AddressBook();
        for (Applicant person : getTypicalApplicants()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Applicant> getTypicalApplicants() {
        return new ArrayList<>(Arrays.asList(ALICE_APPLICANT, BENSON_APPLICANT, CARL_APPLICANT, DANIEL_APPLICANT,
            ELLE_APPLICANT, FIONA_APPLICANT, GEORGE_APPLICANT));
    }
}
