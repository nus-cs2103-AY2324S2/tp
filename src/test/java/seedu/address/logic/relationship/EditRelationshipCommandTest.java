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
                "family", "friend");
        assertThrows(CommandException.class, () -> editCommand.execute(model));
    }

    @Test
    public void execute_editSameDescriptor_throwsCommandException() {
        Model model = new ModelManager();
        Relationship existingRelationship = new Relationship(UUID.randomUUID(),
                UUID.randomUUID(), "family");
        model.addRelationship(existingRelationship);
        EditRelationshipCommand editCommand = new EditRelationshipCommand("uuid1", "uuid2",
                "family", "family");
        assertThrows(CommandException.class, () -> editCommand.execute(model));
    }

    @Test
    public void execute_invalidOriginUuid_throwsCommandException() {
        Model model = new ModelManager();
        String originUuid = null; // Invalid UUID format
        String targetUuid = "1234"; // Valid UUID format
        String oldDescriptor = "family";
        String newDescriptor = "friend";
        EditRelationshipCommand editCommand = new EditRelationshipCommand(originUuid, targetUuid,
                oldDescriptor, newDescriptor);
        assertThrows(CommandException.class, () -> editCommand.execute(model));
    }

    @Test
    public void execute_sameOriginAndTargetUuid_throwsCommandException() {
        Model model = new ModelManager();
        String sameUuid = "1234";
        String relationshipDescriptor = "friend";
        String relationshipDescriptor2 = "family";

        EditRelationshipCommand editCommand = new EditRelationshipCommand(sameUuid, sameUuid,
                relationshipDescriptor, relationshipDescriptor2);
        assertThrows(CommandException.class, () -> editCommand.execute(model));
    }

    @Test
    public void execute_sameOldDescriptorAndNewDescriptor_throwsCommandException() {
        Model model = new ModelManager();
        String uuid1 = "1234";
        String uuid2 = "4321";
        String relationshipDescriptor = "friend";
        EditRelationshipCommand editCommand = new EditRelationshipCommand(uuid1, uuid2,
                relationshipDescriptor, relationshipDescriptor);
        assertThrows(CommandException.class, () -> editCommand.execute(model));
    }

    @Test
    public void execute_relationshipToEditDoesNotExist_throwsCommandException() {
        Model model = new ModelManager();
        String originUuid = "1234";
        String targetUuid = "4321";
        String oldDescriptor = "family";
        String newDescriptor = "friend";

        EditRelationshipCommand editCommand = new EditRelationshipCommand(originUuid, targetUuid,
                oldDescriptor, newDescriptor);

        // The model doesn't contain a relationship with the old descriptor, so it should throw an exception
        assertThrows(CommandException.class, () -> editCommand.execute(model));
    }

    @Test
    public void execute_existingRelationships_throwsCommandException() {
        Model model = new ModelManager();
        UUID originUuid = UUID.randomUUID();
        UUID targetUuid = UUID.randomUUID();
        String uuidString = originUuid.toString();
        String uuid1 = uuidString.substring(uuidString.length() - 4);
        String uuidString2 = targetUuid.toString();
        String uuid2 = uuidString.substring(uuidString2.length() - 4);
        String relationshipDescriptor = "friend";

        // Set up an existing relationship with oldDescriptor
        Relationship existingRelationship = new Relationship(originUuid, targetUuid, relationshipDescriptor);
        model.addRelationship(existingRelationship);

        // Attempt to edit the relationship to newDescriptor
        EditRelationshipCommand editCommand = new EditRelationshipCommand(uuid1, uuid2,
                relationshipDescriptor, relationshipDescriptor);

        // Ensure the exception is thrown when executing the command
        assertThrows(CommandException.class, () -> editCommand.execute(model));
    }
}
