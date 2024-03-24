package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddPaymentCommandTest {

    private Model model = new ModelManager();

    @Test
    public void execute_paymentAcceptedByModel_addSuccessful() throws Exception {
        Person personToAddPayment = new PersonBuilder(ALICE).build(); // Assuming ALICE is a typical Person object you've predefined
        model.addPerson(personToAddPayment);
        Id idOfPersonToAddPayment = personToAddPayment.getUniqueId();

        double amountToAdd = 100.0;
        AddPaymentCommand addPaymentCommand = new AddPaymentCommand(idOfPersonToAddPayment, amountToAdd);

        // Assuming ALICE's initial payment is $0.00
        double expectedNewPaymentAmount = ALICE.getPayment().getAmount() + amountToAdd;
        String expectedMessage = String.format(AddPaymentCommand.MESSAGE_SUCCESS, idOfPersonToAddPayment, expectedNewPaymentAmount);

        CommandResult commandResult = addPaymentCommand.execute(model);

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        // Verify that ALICE's payment amount has been updated correctly in the model
        assertEquals(expectedNewPaymentAmount, model.getPersonByUniqueId(idOfPersonToAddPayment.toString()).getPayment().getAmount());
    }

    @Test
    public void execute_personNotFound_throwsCommandException() {
        Id invalidId = new Id("nonExistentId");
        AddPaymentCommand addPaymentCommand = new AddPaymentCommand(invalidId, 100.0);

        assertThrows(CommandException.class, Messages.MESSAGE_PERSON_NOT_FOUND, () -> addPaymentCommand.execute(model));
    }

    @Test
    public void equals() {
        Id aliceId = ALICE.getUniqueId();
        double paymentAmount = 100.0;
        AddPaymentCommand addPaymentToAliceCommand = new AddPaymentCommand(aliceId, paymentAmount);

        // same object -> returns true
        assertEquals(addPaymentToAliceCommand, addPaymentToAliceCommand);

        // same values -> returns true
        AddPaymentCommand addPaymentToAliceCommandCopy = new AddPaymentCommand(aliceId, paymentAmount);
        assertEquals(addPaymentToAliceCommand, addPaymentToAliceCommandCopy);

        // different types -> returns false
        assertFalse(addPaymentToAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addPaymentToAliceCommand.equals(null));

        // different payment amount -> returns false
        AddPaymentCommand addDifferentPaymentToAliceCommand = new AddPaymentCommand(aliceId, 200.0);
        assertFalse(addPaymentToAliceCommand.equals(addDifferentPaymentToAliceCommand));
    }
}
