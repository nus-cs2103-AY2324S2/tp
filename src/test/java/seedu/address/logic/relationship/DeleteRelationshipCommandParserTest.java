package seedu.address.logic.relationship;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersonsUuid.getTypicalAddressBook;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.relationship.Relationship;

class DeleteRelationshipCommandParserTest {
    private AddRelationshipCommandParser parser = new AddRelationshipCommandParser();
    private Model model;
    private Model expectedModel;
    @BeforeEach
    void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    void parse_invalidInputLength_throwsParseException() {
        String userInput = ("1234 workmates");
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    void parse_validInput_success() {
        String testOriginUuid = "0001";
        String testTargetUuid = "0005";
        String relationshipDescriptor = "housemates";
        UUID person1Uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        UUID person2Uuid = UUID.fromString("00000000-0000-0000-0000-000000000005");
        model.addRelationship(new Relationship(person1Uuid, person2Uuid, relationshipDescriptor));
        expectedModel.addRelationship(new Relationship(person1Uuid, person2Uuid, relationshipDescriptor));
        expectedModel.deleteRelationship(new Relationship(person1Uuid, person2Uuid, relationshipDescriptor));
        String expectedMessage = "Delete successful";
        DeleteRelationshipCommand deleteRelationshipCommand =
                new DeleteRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
        assertCommandSuccess(deleteRelationshipCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void parse_validInputDeleteRelationType_success() {
        String testOriginUuid = "0001";
        String testTargetUuid = "0005";
        String relationshipDescriptor = "housemates";
        UUID person1Uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        UUID person2Uuid = UUID.fromString("00000000-0000-0000-0000-000000000005");
        model.addRelationship(new Relationship(person1Uuid, person2Uuid, relationshipDescriptor));
        expectedModel.addRelationship(new Relationship(person1Uuid, person2Uuid, relationshipDescriptor));
        expectedModel.deleteRelationship(new Relationship(person1Uuid, person2Uuid, relationshipDescriptor));
        String expectedMessage = "Delete successful";
        DeleteRelationshipCommand deleteRelationshipCommand =
                new DeleteRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
        expectedModel.deleteRelationType(relationshipDescriptor);
        assertCommandSuccess(deleteRelationshipCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void parse_invalidFamilialRelationshipDescriptor_throwsParseException() {
        DeleteRelationshipCommandParser parser = new DeleteRelationshipCommandParser();
        String userInput = "family";
        assertThrows(ParseException.class, () -> parser.parse(userInput),
                "Expected parse method to throw ParseException for 'family' relationship descriptor");
    }

    @Test
    void parse_invalidFamilialRelationshipDescriptorCaseInsensitive_throwsParseException() {
        DeleteRelationshipCommandParser parser = new DeleteRelationshipCommandParser();
        String userInput = "Family";
        assertThrows(ParseException.class, () -> parser.parse(userInput),
                "Expected parse method to throw ParseException for 'Family' relationship descriptor");
    }

    @Test
    void parse_invalidPredefinedRelationshipDescriptor_throwsParseException() {
        String userInput = "friend";
        assertThrows(ParseException.class, () -> parser.parse(userInput));

        String userInput2 = "siblings";
        assertThrows(ParseException.class, () -> parser.parse(userInput2));

        String userInput3 = "bioparents";
        assertThrows(ParseException.class, () -> parser.parse(userInput3));

        String userInput4 = "spouses";
        assertThrows(ParseException.class, () -> parser.parse(userInput4));
    }

    @Test
    void parse_invalidUuid_throwsParseException() {
        String userInput = "invalid_uuid 1234 relationshipDescriptor";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }
}
