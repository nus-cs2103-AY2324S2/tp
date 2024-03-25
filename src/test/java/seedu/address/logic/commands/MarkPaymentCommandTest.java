package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


public class MarkPaymentCommandTest {

    private Model model = new ModelManager();

    @Test
    public void execute_paymentUpdatedSuccessfully() throws Exception {
        Person personToMarkPayment = new PersonBuilder().build();
        model.addPerson(personToMarkPayment);
        Id idOfPerson = personToMarkPayment.getUniqueId();

        double paymentAmount = 50.0;
        MarkPaymentCommand markPaymentCommand = new MarkPaymentCommand(idOfPerson, paymentAmount);

        double expectedNewPaymentAmount = Math.max(0, personToMarkPayment.getPayment().getAmount() - paymentAmount);
        String expectedMessage = String.format(MarkPaymentCommand.MESSAGE_SUCCESS, idOfPerson, paymentAmount);

        CommandResult commandResult = markPaymentCommand.execute(model);

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(expectedNewPaymentAmount,
                model.getPersonByUniqueId(idOfPerson.toString()).getPayment().getAmount());
    }

    @Test
    public void execute_personNotFound_throwsCommandException() {
        Id nonExistentId = new Id("nonExistentId");
        MarkPaymentCommand markPaymentCommand = new MarkPaymentCommand(nonExistentId, 50.0);

        assertThrows(CommandException.class,
                Messages.MESSAGE_PERSON_NOT_FOUND, () -> markPaymentCommand.execute(model));
    }
}
