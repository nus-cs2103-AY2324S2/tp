package seedu.address.logic.relationship;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.relationship.Relationship;

public class EditRelationshipCommandTest {
    @Test
    public void execute_editNonExistentRelationship_throwsCommandException() {
        Model model = new ModelManager();
        EditRelationshipCommand editCommand = new EditRelationshipCommand("uuid1", "uuid2",
                "family", "friends");
        assertThrows(CommandException.class, () -> editCommand.execute(model));
    }

    @Test
    public void execute_editSameDescriptor_throwsCommandException() {
        Model model = new ModelManager();
        Relationship existingRelationship = new Relationship(UUID.randomUUID(), UUID.randomUUID(), "family");
        model.addRelationship(existingRelationship);
        EditRelationshipCommand editCommand = new EditRelationshipCommand("uuid1", "uuid2",
                "family", "family");
        assertThrows(CommandException.class, () -> editCommand.execute(model));
    }
}
