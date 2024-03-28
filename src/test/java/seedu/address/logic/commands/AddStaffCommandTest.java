package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICESTAFF;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.AddMessages;
import seedu.address.logic.stubs.ModelStub;
import seedu.address.logic.stubs.ModelStubAcceptingPersonAdded;
import seedu.address.logic.stubs.ModelStubWithPerson;
import seedu.address.model.person.Staff;
import seedu.address.testutil.StaffBuilder;

public class AddStaffCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddStaffCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Staff validPerson = new StaffBuilder().build();

        CommandResult commandResult = new AddStaffCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddMessages.MESSAGE_ADD_PERSON_SUCCESS, AddMessages.format(validPerson)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Staff validPerson = new StaffBuilder().build();
        AddStaffCommand addCommand = new AddStaffCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class,
                AddMessages.MESSAGE_ADD_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Staff alice = new StaffBuilder().withName("Alice").build();
        Staff bob = new StaffBuilder().withName("Bob").build();
        AddStaffCommand addAliceCommand = new AddStaffCommand(alice);
        AddStaffCommand addBobCommand = new AddStaffCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddStaffCommand addAliceCommandCopy = new AddStaffCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddStaffCommand addCommand = new AddStaffCommand(ALICESTAFF);
        String expected = AddStaffCommand.class.getCanonicalName() + "{toAdd=" + ALICESTAFF + "}";
        assertEquals(expected, addCommand.toString());
    }
}
