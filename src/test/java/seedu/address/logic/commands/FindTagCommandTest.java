package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.TypicalPersons;

public class FindTagCommandTest {
    private static final List<Person> OWES =
            Arrays.asList(TypicalPersons.BENSON);
    private static final List<Person> E =
            Arrays.asList(TypicalPersons.ALICE, TypicalPersons.BENSON, TypicalPersons.DANIEL);

    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_emptyString_allPersons() {
        String expectedMessage = String.format(FindTagCommand.MESSAGE_FOUND_PEOPLE, 7, "");
        FindTagCommand command = new FindTagCommand("");

        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredPersonList(), TypicalPersons.getTypicalPersons());
    }

    @Test
    void execute_letterE_success() {
        String keyword = "E";
        FindTagCommand command = new FindTagCommand(keyword);
        String expectedMessage = String.format(FindTagCommand.MESSAGE_FOUND_PEOPLE, E.size(), keyword);
        expectedModel.updateFilteredPersonList(person -> E.contains(person));

        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredPersonList(), E);
    }

    @Test
    void execute_owesMoney_success() {
        String keyword = "owesMoney";
        FindTagCommand command = new FindTagCommand(keyword);
        String expectedMessage = String.format(FindTagCommand.MESSAGE_FOUND_PEOPLE, OWES.size(), keyword);
        expectedModel.updateFilteredPersonList(person -> OWES.contains(person));

        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredPersonList(), OWES);
    }
}
