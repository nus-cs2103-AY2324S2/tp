package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalApplicants.getTypicalApplicantsAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.person.Person;
import seedu.address.testutil.ApplicantBuilder;
import seedu.address.testutil.PersonBuilder;


/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddApplicantCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalApplicantsAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newApplicant_success() {
        Person validPerson = new PersonBuilder().withName("Ma Yun").build();
        Applicant validApplicant = new ApplicantBuilder(validPerson).withRole("CEO").build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validApplicant);

        assertCommandSuccess(new AddApplicantCommand(validApplicant), model,
                String.format(AddApplicantCommand.MESSAGE_SUCCESS, Messages.format(validApplicant)),
                expectedModel);
    }

    @Test
    public void execute_duplicateApplicant_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddApplicantCommand((Applicant) personInList), model,
            AddApplicantCommand.MESSAGE_DUPLICATE_APPLICANT);

    }

}
