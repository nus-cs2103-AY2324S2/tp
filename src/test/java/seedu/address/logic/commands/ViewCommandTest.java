package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

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
                        "Viewing Person: %s; Phone: %s; Email: %s; Address: %s; Tags: %s; Job Description: %s; Interview Date: %s; Internship Duration: %s; Salary: %s",
                        personToView.getCompanyName(),
                        personToView.getPhone(),
                        personToView.getEmail(),
                        personToView.getAddress(),
                        personToView.getTag(),
                        personToView.getJobDescription(),
                        personToView.getInterviewDate(),
                        personToView.getInternDuration(),
                        personToView.getSalary()
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
        Index index = Index.fromZeroBased(3);
        ViewCommand viewCommand = new ViewCommand(index);
        String expectedToString = "seedu.address.logic.commands.ViewCommand{targetIndex=seedu.address.commons.core.index.Index{zeroBasedIndex=3}}";
        assertEquals(expectedToString, viewCommand.toString());
    }
}
