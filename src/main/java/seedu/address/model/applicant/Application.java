package seedu.address.model.applicant;

/**
 * A class that represents a particular
 * interview application.
 */
public class Application {

    public static final Stage INITIAL_APPLICATION = new Stage("Initial Application");
    public static final Stage TECHNICAL_ASSESSMENT = new Stage("Technical Assessment");
    public static final Stage INTERVIEW = new Stage("Interview");
    public static final Stage DECISION_AND_OFFER = new Stage("Decision & Offer");

    // TODO: Add more required roles here.
    public static final Role FRONT_END = new Role("Front End");
    public static final Role BACK_END = new Role("Back End");

    public Application(Applicant applicant, Role role) {
        // TODO: Add application logic here.
    }
}
