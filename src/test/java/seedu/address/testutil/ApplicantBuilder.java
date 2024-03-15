package seedu.address.testutil;


import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Role;
import seedu.address.model.applicant.Stage;
import seedu.address.model.person.Person;


/**
 * A utility class to help with building Applicant objects.
 */
public class ApplicantBuilder extends PersonBuilder {

    public static final String DEFAULT_ROLE = "SWE";
    public static final String DEFAULT_STAGE = "initial_application";
    private Role role;
    private Stage stage;
    /**
     * Creates a {@code ApplicantBuilder} with the default details.
     */
    public ApplicantBuilder() {
        super();
        role = new Role(DEFAULT_ROLE);
        stage = new Stage(DEFAULT_STAGE);
    }

    /**
     * Initializes the ApplicantBuilder with the data of {@code applicantToCopy}.
     */
    public ApplicantBuilder(Applicant applicantToCopy) {
        super(applicantToCopy);
        role = applicantToCopy.getRole();
        stage = applicantToCopy.getStage();
    }

    /**
     * Initializes the ApplicantBuilder with the data of {@code personToCopy} with default fields of applicants
     */
    public ApplicantBuilder(Person personToCopy) {
        super(personToCopy);
        role = new Role(DEFAULT_ROLE);
        stage = new Stage(DEFAULT_STAGE);
    }

    /**
     * Sets the {@code Role} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withRole(String role) {
        this.role = new Role(role);
        return this;
    }

    /**
     * Sets the {@code Stage} of the {@code Applicant} that we are building.
     */
    public ApplicantBuilder withStage(String stage) {
        this.stage = new Stage(stage);
        return this;
    }

    @Override
    public Applicant build() {
        Person p = super.build();
        return new Applicant(p, role, stage);
    }

}
