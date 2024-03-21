package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;

public class SearchTagCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_friendTagSearch_showSuccess() throws CommandException {
        SearchTagCommand sc = new SearchTagCommand(new Tag("friends"));
        CommandResult result = sc.execute(model);
        assertEquals(SearchTagCommand.MESSAGE_SUCCESS, result.getFeedbackToUser());
    }

    @Test
    public void execute_unknownTagSearch_throws() {
        SearchTagCommand sc = new SearchTagCommand(new Tag("Uzuzuzuz"));
        assertThrows(CommandException.class, () -> sc.execute(model));
    }
}
