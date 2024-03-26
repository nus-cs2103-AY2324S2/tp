package seedu.address.logic.relationship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.relationship.Relationship;

public class ListRelationshipTypesCommandTest {

    @Test
    public void execute_listRelationshipTypes_success() {
        ListRelationshipTypesCommand command = new ListRelationshipTypesCommand();
        Model model = new ModelManager();

        CommandResult result = null;
        try {
            result = command.execute(model);
        } catch (CommandException e) {
            // Not expected to throw CommandException
        }

        assertEquals(Relationship.showRelationshipTypes(), result.getFeedbackToUser());
    }
}
