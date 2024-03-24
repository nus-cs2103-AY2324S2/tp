package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.model.person.Id;

import seedu.address.testutil.PersonBuilder;

public class ResetPaymentsCommandTest {

    private Model model = new ModelManager();

    @Test
    public void execute_paymentResetSuccessfully() throws Exception {
        Person personToResetPayment = new PersonBuilder().build(); // Assuming initial payment amount is set
        model.addPerson(personToResetPayment);
        Id idOfPerson = personToResetPayment.getUniqueId();

        ResetPaymentsCommand resetPaymentsCommand = new ResetPaymentsCommand(idOfPerson);

        double expectedNewPaymentAmount = 0.0;
        String expectedMessage = String.format(ResetPaymentsCommand.MESSAGE_SUCCESS, idOfPerson);

        CommandResult commandResult = resetPaymentsCommand.execute(model);

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(expectedNewPaymentAmount, model.getPersonByUniqueId(idOfPerson.toString()).getPayment().getAmount());
    }

    @Test
    public void execute_personNotFound_throwsCommandException() {
        Id nonExistentId = new Id("nonExistentId");
        ResetPaymentsCommand resetPaymentsCommand = new ResetPaymentsCommand(nonExistentId);

        assertThrows(CommandException.class, Messages.MESSAGE_PERSON_NOT_FOUND, () -> resetPaymentsCommand.execute(model));
    }
}
