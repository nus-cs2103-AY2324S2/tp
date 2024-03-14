package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedApplicant.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplicants.BENSON_APPLICANT;
import static seedu.address.testutil.TypicalApplicants.BENSON;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.applicant.Role;
import seedu.address.model.applicant.Stage;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class JsonAdaptedApplicantTest {
    // Currently stage and role has no constraint hence no invalid data
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_ROLE = BENSON_APPLICANT.getRole().toString();
    private static final String VALID_STAGE = BENSON_APPLICANT.getPhone().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
        .map(JsonAdaptedTag::new)
        .collect(Collectors.toList());
    @Test
    public void toModelType_validApplicantDetails_returnsApplicant() throws Exception {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(BENSON_APPLICANT);
        assertEquals(BENSON_APPLICANT, applicant.toModelType());
    }

    @Test
    public void toModelType_nullRole_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant = new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
            null, VALID_STAGE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

    @Test
    public void toModelType_nullStage_throwsIllegalValueException() {
        JsonAdaptedApplicant applicant =
                new JsonAdaptedApplicant(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_ROLE, null,
                    VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Stage.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, applicant::toModelType);
    }

}
