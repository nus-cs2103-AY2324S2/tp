package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedInternship.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.ALICE_MICROSOFT;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.ContactEmail;
import seedu.address.model.internship.ContactName;
import seedu.address.model.internship.ContactNumber;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Location;
import seedu.address.model.internship.Role;
import seedu.address.model.internship.Task;

public class JsonAdaptedInternshipTest {
    private static final String INVALID_COMPANY_NAME = "";
    private static final String INVALID_CONTACT_NAME = "R@chel";
    private static final String INVALID_CONTACT_EMAIL = "example!com";
    private static final String INVALID_CONTACT_NUMBER = "+651234";
    private static final String INVALID_LOCATION = "invalid location";
    private static final String INVALID_APPLICATION_STATUS = "invalid status";
    private static final String INVALID_DESCRIPTION = "";
    private static final String INVALID_ROLE = "";

    private static final String VALID_COMPANY_NAME = ALICE_MICROSOFT.getCompanyName().toString();
    private static final String VALID_CONTACT_NAME = ALICE_MICROSOFT.getContactName().toString();
    private static final String VALID_CONTACT_EMAIL = ALICE_MICROSOFT.getContactEmail().toString();
    private static final String VALID_CONTACT_NUMBER = ALICE_MICROSOFT.getContactNumber().toString();
    private static final String VALID_LOCATION = ALICE_MICROSOFT.getLocation().toString();
    private static final String VALID_APPLICATION_STATUS = ALICE_MICROSOFT.getApplicationStatus().toString();
    private static final String VALID_DESCRIPTION = ALICE_MICROSOFT.getDescription().toString();
    private static final String VALID_ROLE = ALICE_MICROSOFT.getRole().toString();
    private static final String VALID_REMARK = ALICE_MICROSOFT.getRemark().toString();
    private static final ArrayList<Task> VALID_TASKLIST = ALICE_MICROSOFT.getTaskList().getArrayListTaskList();

    @Test
    public void toModelType_validInternshipDetails_returnsInternship() throws Exception {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(ALICE_MICROSOFT);
        assertEquals(ALICE_MICROSOFT, internship.toModelType());
    }

    @Test
    public void toModelType_invalidCompanyName_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(INVALID_COMPANY_NAME, VALID_CONTACT_NAME, VALID_CONTACT_EMAIL,
                        VALID_CONTACT_NUMBER, VALID_LOCATION, VALID_APPLICATION_STATUS, VALID_DESCRIPTION, VALID_ROLE,
                        VALID_REMARK, VALID_TASKLIST);
        String expectedMessage = CompanyName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullCompanyName_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(null, VALID_CONTACT_NAME, VALID_CONTACT_EMAIL,
                VALID_CONTACT_NUMBER, VALID_LOCATION, VALID_APPLICATION_STATUS, VALID_DESCRIPTION, VALID_ROLE,
                        VALID_REMARK, VALID_TASKLIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CompanyName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidContactName_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY_NAME, INVALID_CONTACT_NAME, VALID_CONTACT_EMAIL,
                        VALID_CONTACT_NUMBER, VALID_LOCATION, VALID_APPLICATION_STATUS, VALID_DESCRIPTION, VALID_ROLE,
                        VALID_REMARK, VALID_TASKLIST);
        String expectedMessage = seedu.address.model.internship.ContactName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullContactName_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY_NAME, null, VALID_CONTACT_EMAIL,
                        VALID_CONTACT_NUMBER, VALID_LOCATION, VALID_APPLICATION_STATUS, VALID_DESCRIPTION, VALID_ROLE,
                        VALID_REMARK, VALID_TASKLIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ContactName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidContactEmail_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_CONTACT_NAME, INVALID_CONTACT_EMAIL,
                        VALID_CONTACT_NUMBER, VALID_LOCATION, VALID_APPLICATION_STATUS, VALID_DESCRIPTION, VALID_ROLE,
                        VALID_REMARK, VALID_TASKLIST);
        String expectedMessage = ContactEmail.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullContactEmail_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_CONTACT_NAME, null,
                        VALID_CONTACT_NUMBER, VALID_LOCATION, VALID_APPLICATION_STATUS, VALID_DESCRIPTION, VALID_ROLE,
                        VALID_REMARK, VALID_TASKLIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ContactEmail.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidContactNumber_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_CONTACT_NAME, VALID_CONTACT_EMAIL,
                        INVALID_CONTACT_NUMBER, VALID_LOCATION, VALID_APPLICATION_STATUS,
                        VALID_DESCRIPTION, VALID_ROLE, VALID_REMARK, VALID_TASKLIST);
        String expectedMessage = ContactNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullContactNumber_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_CONTACT_NAME, VALID_CONTACT_EMAIL,
                        null, VALID_LOCATION, VALID_APPLICATION_STATUS, VALID_DESCRIPTION, VALID_ROLE,
                        VALID_REMARK, VALID_TASKLIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ContactNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidLocation_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_CONTACT_NAME, VALID_CONTACT_EMAIL,
                        VALID_CONTACT_NUMBER, INVALID_LOCATION, VALID_APPLICATION_STATUS, VALID_DESCRIPTION,
                        VALID_ROLE, VALID_REMARK, VALID_TASKLIST);
        String expectedMessage = Location.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullLocation_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_CONTACT_NAME, VALID_CONTACT_EMAIL,
                        VALID_CONTACT_NUMBER, null, VALID_APPLICATION_STATUS, VALID_DESCRIPTION, VALID_ROLE,
                        VALID_REMARK, VALID_TASKLIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidApplicationStatus_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_CONTACT_NAME, VALID_CONTACT_EMAIL,
                        VALID_CONTACT_NUMBER, VALID_LOCATION, INVALID_APPLICATION_STATUS, VALID_DESCRIPTION,
                        VALID_ROLE, VALID_REMARK, VALID_TASKLIST);
        String expectedMessage = ApplicationStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullApplicationStatus_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_CONTACT_NAME, VALID_CONTACT_EMAIL,
                        VALID_CONTACT_NUMBER, VALID_LOCATION, null, VALID_DESCRIPTION, VALID_ROLE,
                        VALID_REMARK, VALID_TASKLIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ApplicationStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_CONTACT_NAME, VALID_CONTACT_EMAIL,
                        VALID_CONTACT_NUMBER, VALID_LOCATION, VALID_APPLICATION_STATUS, INVALID_DESCRIPTION,
                        VALID_ROLE, VALID_REMARK, VALID_TASKLIST);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_CONTACT_NAME, VALID_CONTACT_EMAIL,
                        VALID_CONTACT_NUMBER, VALID_LOCATION, VALID_APPLICATION_STATUS, null,
                        VALID_ROLE, VALID_REMARK, VALID_TASKLIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidRole_throwsIllegalValueException() {
        JsonAdaptedInternship internship =
                new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_CONTACT_NAME, VALID_CONTACT_EMAIL,
                        VALID_CONTACT_NUMBER, VALID_LOCATION, VALID_APPLICATION_STATUS, VALID_DESCRIPTION,
                        INVALID_ROLE, VALID_REMARK, VALID_TASKLIST);
        String expectedMessage = Role.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }
}
