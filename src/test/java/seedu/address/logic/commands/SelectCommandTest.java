package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

import com.sun.source.tree.TypeCastTree;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.*;
import seedu.address.model.ModelManager;
import seedu.address.testutil.TypicalEvents;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


public class SelectCommandTest {
    @Test
    public void event_index_outOfRange() {
        ModelManager model = new ModelManager();
        model.setEventBook(TypicalEvents.getTypicalEventBook());
        assertThrows(CommandException.class, () -> new SelectCommand(Index.fromZeroBased(7)).execute(model));
    }

    @Test
    public void equals() {
        SelectCommand one = new SelectCommand(Index.fromZeroBased(5));
        SelectCommand two = new SelectCommand(Index.fromZeroBased(5));
        assertEquals(one, two);
    }

    @Test
    public void execute_selectSuccessful() throws CommandException {
        ModelManager model = new ModelManager();
        model.setEventBook(TypicalEvents.getTypicalEventBook());
        SelectCommand selectCommand = new SelectCommand(Index.fromZeroBased(0));
        CommandResult commandResult = selectCommand.execute(model);
        assertEquals(String.format(SelectCommand.MESSAGE_SELECT_EVENT_SUCCESS, Messages.format(model.getEventBook().getEventList().get(0))), commandResult.getFeedbackToUser());
    }

    @Test
    public void toStringMethod() {
        SelectCommand selectCommand = new SelectCommand(Index.fromZeroBased(0));
        String expected = new ToStringBuilder(selectCommand)
                .add("targetIndex", selectCommand.targetIndex) // Accessing targetIndex via selectCommand
                .toString();
        String actual = selectCommand.toString();
        assertEquals(expected, actual);
    }

}
