package seedu.internhub.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.internhub.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.internhub.commons.core.index.Index;
import seedu.internhub.logic.commands.exceptions.CommandException;
import seedu.internhub.model.Model;
import seedu.internhub.model.ModelManager;
import seedu.internhub.model.UserPrefs;
import seedu.internhub.model.person.Person;

class ViewCommandTest {

    @Test
    void execute_validIndex_success() throws CommandException {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        List<Person> personList = model.getFilteredPersonList();
        Person personToView = personList.get(0);
        ViewCommand viewCommand = new ViewCommand(Index.fromZeroBased(0));

        CommandResult commandResult = viewCommand.execute(model);

        assertEquals(
                String.format(
                        "Viewing Person: %s; Phone: %s; Email: %s; Address: %s; Tags: %s; "
                                + "Job Description: %s; Interview Date: %s; Internship Duration: %s; Salary: %s"
                                + "; Note: %s",
                        personToView.getCompanyName(),
                        personToView.getPhone(),
                        personToView.getEmail(),
                        personToView.getAddress(),
                        personToView.getTag(),
                        personToView.getJobDescription(),
                        personToView.getInterviewDate(),
                        personToView.getInternDuration(),
                        personToView.getSalary(),
                        personToView.getNote()
                ),
                commandResult.getFeedbackToUser()
        );
        assertEquals(personToView, commandResult.getViewPerson());
    }


    @Test
    void execute_invalidIndex_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        ViewCommand viewCommand = new ViewCommand(Index.fromZeroBased(10)); // Assuming there are fewer than 10 persons

        assertThrows(CommandException.class, () -> viewCommand.execute(model));
    }

    @Test
    void equals_sameObject_true() {
        ViewCommand viewCommand = new ViewCommand(Index.fromZeroBased(0));

        assertEquals(viewCommand, viewCommand);
    }

    @Test
    void equals_differentObject_false() {
        ViewCommand viewCommand1 = new ViewCommand(Index.fromZeroBased(0));
        ViewCommand viewCommand2 = new ViewCommand(Index.fromZeroBased(1));

        assertNotEquals(viewCommand1, viewCommand2);
    }

    @Test
    void equals_differentClass_false() {
        ViewCommand viewCommand = new ViewCommand(Index.fromZeroBased(0));
        Object otherObject = new Object();

        assertNotEquals(viewCommand, otherObject);
    }

    @Test
    public void toString_validIndex_success() {
        // Create an Index object
        Index index = Index.fromZeroBased(3);

        // Create a ViewCommand object using the Index
        ViewCommand viewCommand = new ViewCommand(index);

        // Create the expected string representation using variables
        String expectedToString = String.format("seedu.internhub.logic.commands.ViewCommand{targetIndex=%s}", index);

        // Perform the assertion
        assertEquals(expectedToString, viewCommand.toString());
    }

}
