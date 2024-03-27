package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Id;
import seedu.address.model.person.Payment;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddPaymentCommandTest {

    private Model model = new ModelManager();

    @Test
    public void execute_paymentAcceptedByModel_addSuccessful() throws Exception {
        Payment initialPaymentAmount = new Payment(0.0);
        Person personToAddPayment = new PersonBuilder().withPayment(initialPaymentAmount.getAmount()).build();
        model.addPerson(personToAddPayment);
        Id idOfPersonToAddPayment = personToAddPayment.getUniqueId();

        double amountToAdd = 100.0;
        AddPaymentCommand addPaymentCommand = new AddPaymentCommand(idOfPersonToAddPayment, amountToAdd);

        double expectedNewPaymentAmount = initialPaymentAmount.getAmount() + amountToAdd;
        String expectedMessage = String.format(AddPaymentCommand.MESSAGE_SUCCESS,
                idOfPersonToAddPayment, expectedNewPaymentAmount);

        CommandResult commandResult = addPaymentCommand.execute(model);

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(expectedNewPaymentAmount,
                model.getPersonByUniqueId(idOfPersonToAddPayment.toString()).getPayment().getAmount());
    }

    @Test
    public void execute_personNotFound_throwsCommandException() {
        Id invalidId = new Id("nonExistentId");
        AddPaymentCommand addPaymentCommand = new AddPaymentCommand(invalidId, 100.0);

        assertThrows(CommandException.class,
                Messages.MESSAGE_PERSON_NOT_FOUND, () -> addPaymentCommand.execute(model));
    }

    @Test
    public void equals() {
        Id idOfPerson = new Id("000001");
        double paymentAmount = 100.0;
        AddPaymentCommand addPaymentToPersonCommand = new AddPaymentCommand(idOfPerson, paymentAmount);

        // same object -> returns true
        assertEquals(addPaymentToPersonCommand, addPaymentToPersonCommand);

        // same values -> returns true
        AddPaymentCommand addPaymentToPersonCommandCopy = new AddPaymentCommand(idOfPerson, paymentAmount);
        assertEquals(addPaymentToPersonCommand, addPaymentToPersonCommandCopy);

        // different types -> returns false
        assertFalse(addPaymentToPersonCommand.equals(1));

        // null -> returns false
        assertFalse(addPaymentToPersonCommand.equals(null));

        // different payment amount -> returns false
        AddPaymentCommand addDifferentPaymentToPersonCommand = new AddPaymentCommand(idOfPerson, 200.0);
        assertFalse(addPaymentToPersonCommand.equals(addDifferentPaymentToPersonCommand));
    }
}
