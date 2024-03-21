package seedu.address.logic.relationship;import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
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
    public void execute_originUuidIsNull_throwsCommandException() {
        // Setup
        Model model = new ModelManager();
        String originUuid = null;
        String targetUuid = "target123";
        String oldRelationshipDescriptor = "family";
        String newRelationshipDescriptor = "friend";
        EditRelationshipCommand editCommand = new EditRelationshipCommand(originUuid, targetUuid,
                oldRelationshipDescriptor, newRelationshipDescriptor);

        // Verify
        assertThrows(CommandException.class, () -> editCommand.execute(model),
                Messages.MESSAGE_INVALID_PERSON_UUID);
    }

    @Test
    public void execute_invalidTargetUuid_throwsCommandException() {
        Model model = new ModelManager();
        String originUuid = "1234";
        String targetUuid = null;
        String oldDescriptor = "family";
        String newDescriptor = "friend";
        EditRelationshipCommand editCommand = new EditRelationshipCommand(originUuid, targetUuid,
                oldDescriptor, newDescriptor);
        assertThrows(CommandException.class, () -> editCommand.execute(model),
                Messages.MESSAGE_INVALID_PERSON_UUID);
    }

    @Test
    public void execute_originAndTargetUuidsAreSame_throwsCommandException() {
        // Setup
        Model model = new ModelManager();
        String originUuid = "person123";
        String targetUuid = "person123";
        String oldRelationshipDescriptor = "family";
        String newRelationshipDescriptor = "friend";
        EditRelationshipCommand editCommand = new EditRelationshipCommand(originUuid, targetUuid,
                oldRelationshipDescriptor, newRelationshipDescriptor);

        // Verify
        assertThrows(CommandException.class, () -> editCommand.execute(model),
                "Relationships must be between 2 different people");
    }

    @Test
    public void execute_oldAndNewDescriptorsAreSame_throwsCommandException() {
        // Setup
        Model model = new ModelManager();
        String originUuid = "person123";
        String targetUuid = "person456";
        String oldRelationshipDescriptor = "family";
        String newRelationshipDescriptor = "family";
        EditRelationshipCommand editCommand = new EditRelationshipCommand(originUuid, targetUuid,
                oldRelationshipDescriptor, newRelationshipDescriptor);

        // Verify
        assertThrows(CommandException.class, () -> editCommand.execute(model),
                "There's no need to edit the relationship");
    }

    @Test
    public void execute_relationshipToEditDoesNotExist_throwsCommandException() {
        // Setup
        Model model = new ModelManager();
        UUID originUuid = UUID.randomUUID();
        UUID targetUuid = UUID.randomUUID();
        String oldDescriptor = "family";
        String newDescriptor = "friend";

        // Attempt to edit a non-existent relationship
        EditRelationshipCommand editCommand = new EditRelationshipCommand(
                originUuid.toString(), targetUuid.toString(), oldDescriptor, newDescriptor);

        // Verify
        assertThrows(CommandException.class, () -> editCommand.execute(model),
                String.format("Sorry %s do not exist", new Relationship(originUuid, targetUuid, oldDescriptor)));
    }

    @Test
    public void execute_existingRelationships_throwsCommandException() {
        // Setup
        Model model = new ModelManager();
        UUID originUuid = UUID.randomUUID();
        UUID targetUuid = UUID.randomUUID();
        String oldDescriptor = "family";

        // Add an existing relationship
        Relationship existingRelationship = new Relationship(originUuid, targetUuid, oldDescriptor);
        model.addRelationship(existingRelationship);

        // Attempt to edit the relationship with the same descriptor
        EditRelationshipCommand editCommand = new EditRelationshipCommand(
                originUuid.toString(), targetUuid.toString(), oldDescriptor, oldDescriptor);

        // Verify
        assertThrows(CommandException.class, () -> editCommand.execute(model),
                String.format("%s already exists", existingRelationship));
    }
}
