package seedu.address.logic.relationship;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.relationship.Relationship;
import seedu.address.testutil.TypicalPersonsUuid;

public class EditRelationshipCommandTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");

    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");

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
        AddressBook typicalPersonsAddressBook = TypicalPersonsUuid.getTypicalAddressBook();
        model.setAddressBook(typicalPersonsAddressBook);
        String originUuid = "0001";
        String targetUuid = "0006";
        String oldRelationshipDescriptor = "family";
        String newRelationshipDescriptor = "family";
        EditRelationshipCommand editCommand = new EditRelationshipCommand(originUuid, targetUuid,
                oldRelationshipDescriptor, newRelationshipDescriptor);

        // Verify
        CommandException exception = assertThrows(CommandException.class, () -> editCommand.execute(model),
                "There's no need to edit the relationship if the new relationship is the same as the old one.");

        // Check the exception message
        assertEquals("There's no need to edit the relationship if the new relationship is the same as the old one.",
                exception.getMessage());
    }

    @Test
    public void execute_relationshipToEditDoesNotExist_throwsCommandException() {
        // Setup
        Model model = new ModelManager();
        AddressBook typicalPersonsAddressBook = TypicalPersonsUuid.getTypicalAddressBook();
        model.setAddressBook(typicalPersonsAddressBook);
        String originUuid = "0001";
        String targetUuid = "0003";
        String oldDescriptor = "family";
        String newDescriptor = "friend";

        // Attempt to edit a non-existent relationship
        EditRelationshipCommand editCommand = new EditRelationshipCommand(
                originUuid, targetUuid, oldDescriptor, newDescriptor);
        Relationship toEditOff = new Relationship(UUID.fromString("00000000-0000-0000-0000-000000000001"),
                UUID.fromString("00000000-0000-0000-0000-000000000003"), oldDescriptor);
        // Verify
        CommandException exception = assertThrows(CommandException.class, () -> editCommand.execute(model),
                String.format("Sorry %s do not exist", toEditOff));

        // Check the exception message
        assertEquals(String.format("Sorry %s do not exist", toEditOff),
                exception.getMessage());

    }

    @Test
    public void execute_existingRelationships_throwsCommandException() {
        // Setup
        Model model = new ModelManager();
        AddressBook typicalPersonsAddressBook = TypicalPersonsUuid.getTypicalAddressBook();
        model.setAddressBook(typicalPersonsAddressBook);
        String originUuid = "0001";
        String targetUuid = "0002";
        String oldDescriptor = "friend";
        String newDescriptor = "family";

        // Add the relationship to the model
        Relationship oldRelationship = new Relationship(
                UUID.fromString("00000000-0000-0000-0000-000000000001"),
                UUID.fromString("00000000-0000-0000-0000-000000000002"), oldDescriptor);
        Relationship existingRelationship = new Relationship(
                UUID.fromString("00000000-0000-0000-0000-000000000001"),
                UUID.fromString("00000000-0000-0000-0000-000000000002"), newDescriptor);
        model.addRelationship(oldRelationship);
        model.addRelationship(existingRelationship);

        // Attempt to edit the relationship with the same descriptor
        EditRelationshipCommand editCommand = new EditRelationshipCommand(
                originUuid, targetUuid, oldDescriptor, newDescriptor);

        // Verify
        CommandException exception = assertThrows(CommandException.class, () -> editCommand.execute(model),
                String.format("%s already exists", existingRelationship));

        // Check the exception message
        assertEquals(String.format("%s already exists", existingRelationship),
                exception.getMessage());
    }

    @Test
    public void execute_invalidRelationshipType_throwsCommandException() {
        // Setup
        Model model = new ModelManager();
        AddressBook typicalPersonsAddressBook = TypicalPersonsUuid.getTypicalAddressBook();
        model.setAddressBook(typicalPersonsAddressBook);
        String originUuid = "0001";
        String targetUuid = "0006";
        String oldRelationshipDescriptor = "family";
        String newRelationshipDescriptor = "invalid";
        EditRelationshipCommand editCommand = new EditRelationshipCommand(originUuid, targetUuid,
                oldRelationshipDescriptor, newRelationshipDescriptor);

        // Verify
        CommandException exception = assertThrows(CommandException.class, () -> editCommand.execute(model),
                "Invalid Relationship type");
        // Check the exception message
        assertEquals(String.format("Invalid Relationship type"),
                exception.getMessage());
    }

    @Test
    public void execute_successfulRelationship_throwsCommandException() {
        // Setup
        Model model = new ModelManager();
        AddressBook typicalPersonsAddressBook = TypicalPersonsUuid.getTypicalAddressBook();
        model.setAddressBook(typicalPersonsAddressBook);
        String originUuid = "0001";
        String targetUuid = "0002";
        String oldRelationshipDescriptor = "friend";
        String newRelationshipDescriptor = "family";
        EditRelationshipCommand editCommand = new EditRelationshipCommand(originUuid, targetUuid,
                oldRelationshipDescriptor, newRelationshipDescriptor);

        CommandResult result = assertDoesNotThrow(() -> editCommand.execute(model));

        // Verify
        assertEquals(EditRelationshipCommand.MESSAGE_EDIT_RELATIONSHIP_SUCCESS, result.getFeedbackToUser());

        // Assert that the relationship was deleted and added successfully
        UUID fullOriginUuid = model.getFullUuid(originUuid);
        UUID fullTargetUuid = model.getFullUuid(targetUuid);
        Relationship expectedDeletedRelationship =
                new Relationship(fullOriginUuid, fullTargetUuid, oldRelationshipDescriptor);
        Relationship expectedAddedRelationship =
                new Relationship(fullOriginUuid, fullTargetUuid, newRelationshipDescriptor);

        assertFalse(model.hasRelationshipWithDescriptor(expectedDeletedRelationship));
        assertTrue(model.hasRelationshipWithDescriptor(expectedAddedRelationship));
    }
}
