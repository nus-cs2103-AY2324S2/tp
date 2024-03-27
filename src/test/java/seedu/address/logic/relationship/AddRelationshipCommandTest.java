package seedu.address.logic.relationship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersonsUuid.getTypicalAddressBook;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.relationship.Relationship;
import seedu.address.model.person.relationship.SiblingRelationship;
import seedu.address.model.person.relationship.SpousesRelationship;

class AddRelationshipCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    void testExecute_duplicateInputThrowsException() {
        String testOriginUuid = "0001";
        String testTargetUuid = "0001";
        String relationshipDescriptor = "housemates";
        AddRelationshipCommand addRelationshipCommand =
                new AddRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
        assertCommandFailure(addRelationshipCommand, model,
                "Relationships must be between 2 different people");
    }

    @Test
    void execute_validInput_success() {
        String testOriginUuid = "0001";
        String testTargetUuid = "0002";
        String relationshipDescriptor = "housemates";
        AddRelationshipCommand addRelationshipCommand =
                new AddRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
        String expectedMessage = "Add success";
        UUID person1Uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        UUID person2Uuid = UUID.fromString("00000000-0000-0000-0000-000000000002");
        String familyRelationshipDescriptor = "housemates";
        expectedModel.addRelationship(
                new Relationship(person1Uuid, person2Uuid, familyRelationshipDescriptor));
        assertCommandSuccess(addRelationshipCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void testExecute_invalidUuidInputThrowsException() {
        String testOriginUuid = "0001";
        String testTargetUuid = "0019";
        String relationshipDescriptor = "housemates";
        AddRelationshipCommand addRelationshipCommand =
                new AddRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
        assertCommandFailure(addRelationshipCommand, model,
                "The UUID provided is invalid: ");
    }

    @Test
    void testExecuteAddingExistingRelationshipThrowsException() {
        String testOriginUuid = "0001";
        String testTargetUuid = "0002";
        String relationshipDescriptor = "housemates";
        AddRelationshipCommand addRelationshipCommand =
                new AddRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
        UUID person1Uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        UUID person2Uuid = UUID.fromString("00000000-0000-0000-0000-000000000002");
        String familyRelationshipDescriptor = "housemates";
        model.addRelationship(new Relationship(person1Uuid, person2Uuid, familyRelationshipDescriptor));
        assertCommandFailure(addRelationshipCommand, model,
                "Sorry, 00000000-0000-0000-0000-000000000001 and 00000000-0000-0000-0000-000000000002 are housemates");

    }
    @Test
    void testExecuteAddInvalidRelationshipDescriptorThrowsException() {
        String testOriginUuid = "0001";
        String testTargetUuid = "0002";
        String relationshipDescriptor = "123";
        AddRelationshipCommand addRelationshipCommand =
                new AddRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
        assertCommandFailure(addRelationshipCommand, model, "Invalid Relationship type");
    }
    @Test
    void testEqualsMethodWithSameArguments() {
        String testOriginUuid = "0001";
        String testTargetUuid = "0002";
        String relationshipDescriptor = "housemates";
        AddRelationshipCommand test1 =
                new AddRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
        AddRelationshipCommand test2 =
                new AddRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
        assertEquals(test1.equals(test2), true);
    }
    @Test
    void testEqualsMethodWithNotAddRelationshipCommandInstance() {
        String testOriginUuid = "0001";
        String testTargetUuid = "0002";
        String relationshipDescriptor = "housemates";
        AddRelationshipCommand test1 =
                new AddRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
        String test2 = "test";
        assertEquals(test1.equals(test2), false);
    }
    @Test
    void testEqualsMethodWithSameInstance() {
        String testOriginUuid = "0001";
        String testTargetUuid = "0002";
        String relationshipDescriptor = "housemates";
        AddRelationshipCommand test1 =
                new AddRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
        assertEquals(test1.equals(test1), true);
    }

    @Test
    void execute_addSiblingRelationship_success() {
        String testOriginUuid = "0001";
        String testTargetUuid = "0002";
        String relationshipDescriptor = "Siblings";
        AddRelationshipCommand addRelationshipCommand =
                new AddRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
        String expectedMessage = "Add success";
        UUID person1Uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        UUID person2Uuid = UUID.fromString("00000000-0000-0000-0000-000000000002");
        expectedModel.addRelationship(
                new SiblingRelationship(person1Uuid, person2Uuid));
        assertCommandSuccess(addRelationshipCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_addSpousesRelationship_success() {
        String testOriginUuid = "0001";
        String testTargetUuid = "0002";
        String relationshipDescriptor = "Spouses";
        AddRelationshipCommand addRelationshipCommand =
                new AddRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
        String expectedMessage = "Add success";
        UUID person1Uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        UUID person2Uuid = UUID.fromString("00000000-0000-0000-0000-000000000002");
        expectedModel.addRelationship(
                new SpousesRelationship(person1Uuid, person2Uuid));
        assertCommandSuccess(addRelationshipCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_validInputs_success() throws CommandException {
        String testOriginUuid = "0001";
        String testTargetUuid = "0002";
        String relationshipDescriptor = "housemates";
        AddRelationshipCommand addRelationshipCommand =
                new AddRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
        String expectedMessage = "Add success";
        UUID person1Uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        UUID person2Uuid = UUID.fromString("00000000-0000-0000-0000-000000000002");
        String familyRelationshipDescriptor = "housemates";

        // Ensure model does not initially contain the relationship
        assertEquals(model.hasRelationship(new Relationship(person1Uuid, person2Uuid,
                familyRelationshipDescriptor)), false);

        // Execute the command
        CommandResult commandResult = addRelationshipCommand.execute(model);

        // Ensure model now contains the relationship
        assertEquals(model.hasRelationship(new Relationship(person1Uuid, person2Uuid,
                familyRelationshipDescriptor)), true);

        // Ensure command result contains the expected message
        assertEquals(commandResult.getFeedbackToUser(), expectedMessage);
    }
}


