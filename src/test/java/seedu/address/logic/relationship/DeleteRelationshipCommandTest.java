package seedu.address.logic.relationship;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersonsUuid.getTypicalAddressBook;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.relationship.Relationship;

class DeleteRelationshipCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    void testExecute_validUuidInputThrowsException() {
        String testOriginUuid = "0001";
        String testTargetUuid = "0002";
        String relationshipDescriptor = "friend";
        UUID person1Uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        UUID person2Uuid = UUID.fromString("00000000-0000-0000-0000-000000000002");
        String expectedMessage = "Delete successful";
        DeleteRelationshipCommand addRelationshipCommand =
                new DeleteRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
        expectedModel.deleteRelationship(
                new Relationship(person1Uuid, person2Uuid, relationshipDescriptor));
        assertCommandSuccess(addRelationshipCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void testExecute_relationshipDoesNotExist_throwsException() {
        String testOriginUuid = "0001";
        String testTargetUuid = "0002";
        String relationshipDescriptor = "housemates";
        UUID person1Uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        UUID person2Uuid = UUID.fromString("00000000-0000-0000-0000-000000000002");
        // Don't add the relationship to the model
        String expectedMessage = String.format("Sorry %s do not exist", new Relationship(person1Uuid,
                person2Uuid, relationshipDescriptor));
        DeleteRelationshipCommand deleteRelationshipCommand =
                new DeleteRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
        assertCommandFailure(deleteRelationshipCommand, model, expectedMessage);
    }

    @Test
    void testExecute_invalidRelationshipType_throwsCommandException() {
        String testOriginUuid = "0001";
        String testTargetUuid = "0002";
        String relationshipDescriptor = "123"; // Provide an invalid relationship type
        DeleteRelationshipCommand deleteRelationshipCommand =
                new DeleteRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // Ensure that the IllegalArgumentException is thrown during execution
        assertThrows(CommandException.class, () -> deleteRelationshipCommand.execute(model));
    }

    @Test
    void execute_sameOriginAndTargetUuidsButNotTestUuids_throwsCommandException() {
        String testOriginUuid = "0001";
        String testTargetUuid = "0001"; // Same as originUuid
        String relationshipDescriptor = "friend";

        DeleteRelationshipCommand deleteRelationshipCommand = new DeleteRelationshipCommand(
                testOriginUuid, testTargetUuid, relationshipDescriptor);

        assertCommandFailure(deleteRelationshipCommand, model, "Relationships must be between 2 different people");
    }

    @Test
    void execute_deleteRelationTypeRelationshipTypeNotExists_throwsCommandException() {
        String relationshipDescriptor = "nonexistent";

        DeleteRelationshipCommand deleteRelationshipCommand = new DeleteRelationshipCommand(
                relationshipDescriptor, true);

        assertThrows(CommandException.class, () -> deleteRelationshipCommand.execute(model));
    }

    @Test
    void execute_invalidRelationshipType_throwsCommandException() {
        String originUuid = "0001";
        String targetUuid = "0002";
        String relationshipDescriptor = "invalid"; // Provide an invalid relationship type

        DeleteRelationshipCommand deleteRelationshipCommand = new DeleteRelationshipCommand(
                originUuid, targetUuid, relationshipDescriptor);

        assertThrows(CommandException.class, () -> deleteRelationshipCommand.execute(model));
    }

}
