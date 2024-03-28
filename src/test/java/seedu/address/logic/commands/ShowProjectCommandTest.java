package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ShowProjectCommandTest {



    @Test
    public void equals() {

        ShowProjectCommand showAB3Command = new ShowProjectCommand("AB3");
        ShowProjectCommand showDukeCommand = new ShowProjectCommand("Duke");

        // same object -> returns true
        assertTrue(showAB3Command.equals(showAB3Command));

        // same values -> returns true
        ShowProjectCommand showAB3CommandCopy = new ShowProjectCommand("AB3");
        assertTrue(showAB3Command.equals(showAB3CommandCopy));

        // different types -> returns false
        assertFalse(showAB3Command.equals(1));

        // null -> returns false
        assertFalse(showAB3Command.equals(null));

        // different person -> returns false
        assertFalse(showAB3Command.equals(showDukeCommand));
    }


    @Test
    public void toStringMethod() {
        String projectName = "Duke";
        ShowProjectCommand showProjectCommand = new ShowProjectCommand("Duke");
        String expected = ShowProjectCommand.class.getCanonicalName() + "{targetName=" + projectName + "}";
        assertEquals(expected, showProjectCommand.toString());
    }
}
