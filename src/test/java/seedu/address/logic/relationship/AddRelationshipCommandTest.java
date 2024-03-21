package seedu.address.logic.relationship;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersonsUuid.getTypicalAddressBook;


import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.relationship.Relationship;

class AddRelationshipCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    void execute_validInput_success() {
        String testOriginUuid = "0001";
        String testTargetUuid = "0002";
        String relationshipDescriptor = "family";
        AddRelationshipCommand addRelationshipCommand =
                new AddRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
        String expectedMessage = "Add success";
        UUID person1Uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        UUID person2Uuid = UUID.fromString("00000000-0000-0000-0000-000000000002");
        String FAMILY_RELATIONSHIP_DESCRIPTOR = "family";
        expectedModel.addRelationship(
                new Relationship(person1Uuid, person2Uuid, FAMILY_RELATIONSHIP_DESCRIPTOR));
        assertCommandSuccess(addRelationshipCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void testExecute_duplicateInputThrowsException() {
        String testOriginUuid = "0001";
        String testTargetUuid = "0001";
        String relationshipDescriptor = "family";
        AddRelationshipCommand addRelationshipCommand =
                new AddRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
        assertCommandFailure(addRelationshipCommand, model,
                "Relationships must be between 2 different people");
    }

    @Test
    void testExecute_invalidUuidInputThrowsException() {
        String testOriginUuid = "0001";
        String testTargetUuid = "0019";
        String relationshipDescriptor = "family";
        AddRelationshipCommand addRelationshipCommand =
                new AddRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
        assertCommandFailure(addRelationshipCommand, model,
                "The UUID provided is invalid.");
    }
}
//    @Test
//    void testExecute_AddingExistingRelationshipThrowsException() {
//        String testOriginUuid = "0001";
//        String testTargetUuid = "0002";
//        String relationshipDescriptor = "family";
//        AddRelationshipCommand addRelationshipCommand =
//                new AddRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
//        UUID PERSON_1_UUID = UUID.fromString("00000000-0000-0000-0000-000000000001");
//        UUID PERSON_2_UUID =  UUID.fromString("00000000-0000-0000-0000-000000000002");
//        String FAMILY_RELATIONSHIP_DESCRIPTOR = "family";
//        model.addRelationship(new Relationship(PERSON_1_UUID, PERSON_2_UUID, FAMILY_RELATIONSHIP_DESCRIPTOR));
//        assertCommandFailure(addRelationshipCommand, model,
//                "Sorry, 00000000-0000-0000-0000-000000000001 and 00000000-0000-0000-0000-000000000002 are family");
//
//    }

