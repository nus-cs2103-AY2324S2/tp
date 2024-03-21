package seedu.address.testutil;

import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.ContactEmail;
import seedu.address.model.internship.ContactName;
import seedu.address.model.internship.ContactNumber;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Location;
import seedu.address.model.internship.Remark;
import seedu.address.model.internship.Role;

/**
 * A utility class to help with building Internship objects.
 */
public class InternshipBuilder {

    public static final String DEFAULT_COMPANY_NAME = "Microsoft";
    public static final String DEFAULT_CONTACT_NAME = "Amy Pauline";
    public static final String DEFAULT_CONTACT_EMAIL = "amy@example.com";
    public static final String DEFAULT_CONTACT_NUMBER = "91001274";
    public static final String DEFAULT_LOCATION = "remote";
    public static final String DEFAULT_APPLICATION_STATUS = "pending";
    public static final String DEFAULT_DESCRIPTION = "Develop new microsoft web applications";
    public static final String DEFAULT_ROLE = "Application Engineer";

    private CompanyName companyName;
    private ContactName contactName;
    private ContactEmail contactEmail;
    private ContactNumber contactNumber;
    private Location location;
    private ApplicationStatus applicationStatus;
    private Description description;
    private Role role;
    private Remark remark;

    /**
     * Creates an {@code InternshipBuilder} with the default details.
     */
    public InternshipBuilder() {
        companyName = new CompanyName(DEFAULT_COMPANY_NAME);
        contactName = new ContactName(DEFAULT_CONTACT_NAME);
        contactEmail = new ContactEmail(DEFAULT_CONTACT_EMAIL);
        contactNumber = new ContactNumber(DEFAULT_CONTACT_NUMBER);
        location = new Location(DEFAULT_LOCATION);
        applicationStatus = new ApplicationStatus(DEFAULT_APPLICATION_STATUS);
        description = new Description(DEFAULT_DESCRIPTION);
        role = new Role(DEFAULT_ROLE);
    }

    /**
     * Initializes the InternshipBuilder with the data of {@code internshipToCopy}.
     */
    public InternshipBuilder(Internship internshipToCopy) {
        companyName = internshipToCopy.getCompanyName();
        contactName = internshipToCopy.getContactName();
        contactEmail = internshipToCopy.getContactEmail();
        contactNumber = internshipToCopy.getContactNumber();
        location = internshipToCopy.getLocation();
        applicationStatus = internshipToCopy.getApplicationStatus();
        description = internshipToCopy.getDescription();
        role = internshipToCopy.getRole();
    }

    /**
     * Sets the {@code CompanyName} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withCompanyName(String companyName) {
        this.companyName = new CompanyName(companyName);
        return this;
    }

    /**
     * Sets the {@code ContactName} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withContactName(String contactName) {
        this.contactName = new ContactName(contactName);
        return this;
    }

    /**
     * Sets the {@code ContactEmail} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withContactEmail(String contactEmail) {
        this.contactEmail = new ContactEmail(contactEmail);
        return this;
    }

    /**
     * Sets the {@code ContactNumber} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withContactNumber(String contactNumber) {
        this.contactNumber = new ContactNumber(contactNumber);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    /**
     * Sets the {@code ApplicationStatus} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withApplicationStatus(String applicationStatus) {
        this.applicationStatus = new ApplicationStatus(applicationStatus);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withRole(String role) {
        this.role = new Role(role);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Builds the Internship object.
     */
    public Internship build() {
        return new Internship(companyName, contactName, contactEmail, contactNumber, location, applicationStatus,
                description, role, remark);
    }

}
