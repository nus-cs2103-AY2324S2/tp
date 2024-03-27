package seedu.address.logic.relationship;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersonsUuid.getTypicalAddressBook;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.relationship.BioParentsRelationship;
import seedu.address.model.person.relationship.RoleBasedRelationship;
import seedu.address.model.person.relationship.SiblingRelationship;
import seedu.address.model.person.relationship.SpousesRelationship;

class AddRelationshipCommandParserTest {
    private Model model;
    private Model expectedModel;
    private AddRelationshipCommandParser parser = new AddRelationshipCommandParser();
    @BeforeEach
    void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    void parse_validInputFamilyWithRoles_success() {
        String userInput = "parent 0001 child 0003 bioparents";
        AddRelationshipCommand expected = new AddRelationshipCommand("0001", "0003",
                "bioParents", "parent", "child");
        assertParseSuccess(parser, userInput, expected);
    }

    @Test
    void parse_validInputWithRoles_success() {
        String userInput = "Boss 0001 Subordinate 0003 Workbuddies";
        AddRelationshipCommand expected = new AddRelationshipCommand("0001", "0003",
                "Workbuddies", "Boss", "Subordinate");
        assertParseSuccess(parser, userInput, expected);
    }

    @Test
    void parseInvalidInputWithRoles_throwsParseException() {
        String userInput = "parent 0001 child 19000 bioparents";
        assertParseFailure(parser, userInput, "The UUID provided is invalid: ");

        String userInput2 = "parent 00010 child 0003 bioparents";
        assertParseFailure(parser, userInput2, "The UUID provided is invalid: ");
    }
    @Test
    void parse_invalidInputMissingPartsWithRoles_throwsIllegalArgumentException() {
        String userInput = "parent child 0002 bioparents";
        assertParseFailure(parser, userInput, "Invalid command format! \n%1$s");

        String userInput2 = "parent 0001 0003 bioparents";
        assertParseFailure(parser, userInput2, "Invalid command format! \n%1$s");
    }

    @Test
    void parse_upperAndLowerCaseInputs_success() {
        String userInput = "parent 0001 child 0003 Bioparents";
        AddRelationshipCommand expected = new AddRelationshipCommand("0001", "0003",
                "bioparents", "parent", "child");
        assertParseSuccess(parser, userInput, expected);

        String userInput2 = "0001 0003 Siblings";
        AddRelationshipCommand expected2 = new AddRelationshipCommand("0001", "0003",
                "siblings");
        assertParseSuccess(parser, userInput2, expected2);

        String userInput3 = "0001 0003 Spouses";
        AddRelationshipCommand expected3 = new AddRelationshipCommand("0001", "0003",
                "spouses");
        assertParseSuccess(parser, userInput3, expected3);

        String userInput4 = "Parent 0001 child 0005 Bioparents";
        AddRelationshipCommand expected4 = new AddRelationshipCommand("0001", "0005",
                "bioparents", "parent", "child");
        assertParseSuccess(parser, userInput4, expected4);

        String userInput5 = "parent 0001 Child 0006 Bioparents";
        AddRelationshipCommand expected5 = new AddRelationshipCommand("0001", "0006",
                "bioparents", "parent", "child");
        assertParseSuccess(parser, userInput5, expected5);
    }

    @Test
    void execute_invalidNewRelationshipIfExistingRelationship_throwsCommandException() {
        String testOriginUuid = "0001";
        String testTargetUuid = "0005";
        String relationshipDescriptor = "bioparents";
        String role1 = "parent";
        String role2 = "child";
        UUID person1Uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        UUID person2Uuid = UUID.fromString("00000000-0000-0000-0000-000000000005");
        RoleBasedRelationship existingRelationship = new RoleBasedRelationship(person1Uuid, person2Uuid,
                relationshipDescriptor, role1, role2);
        model.addRelationship(existingRelationship);
        AddRelationshipCommand addRelationshipCommand =
                new AddRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor, role1, role2);
        assertThrows(CommandException.class, () -> addRelationshipCommand.execute(model));
    }

    @Test
    void execute_addBioParentsRelationship_success() {
        String testOriginUuid = "0001";
        String testTargetUuid = "0002";
        String relationshipDescriptor = "bioparents";
        AddRelationshipCommand addRelationshipCommand =
                new AddRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
        String expectedMessage = "Add success";
        UUID person1Uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        UUID person2Uuid = UUID.fromString("00000000-0000-0000-0000-000000000002");
        expectedModel.addRelationship(
                new BioParentsRelationship(person1Uuid, person2Uuid));
        assertCommandSuccess(addRelationshipCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_addSpousesRelationship_success() {
        String testOriginUuid = "0001";
        String testTargetUuid = "0003";
        String relationshipDescriptor = "spouses";
        AddRelationshipCommand addRelationshipCommand =
                new AddRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
        String expectedMessage = "Add success";
        UUID person1Uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        UUID person2Uuid = UUID.fromString("00000000-0000-0000-0000-000000000003");
        expectedModel.addRelationship(
                new SpousesRelationship(person1Uuid, person2Uuid));
        assertCommandSuccess(addRelationshipCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_addSiblingRelationship_success() {
        String testOriginUuid = "0001";
        String testTargetUuid = "0005";
        String relationshipDescriptor = "siblings";
        AddRelationshipCommand addRelationshipCommand =
                new AddRelationshipCommand(testOriginUuid, testTargetUuid, relationshipDescriptor);
        String expectedMessage = "Add success";
        UUID person1Uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        UUID person2Uuid = UUID.fromString("00000000-0000-0000-0000-000000000005");
        expectedModel.addRelationship(
                new SiblingRelationship(person1Uuid, person2Uuid));
        assertCommandSuccess(addRelationshipCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void parse_validInput_success() {
        String userInput = "0001 0003 siblings";
        AddRelationshipCommand expected = new AddRelationshipCommand("0001",
                "0003", "siblings");
        assertParseSuccess(parser, userInput, expected);
    }

    @Test
    void parseInvalidInput_throwsParseException() {
        String userInput = "0001 19000 siblings";
        assertParseFailure(parser, userInput, "The UUID provided is invalid: ");

        String userInput2 = "00010 0003 siblings";
        assertParseFailure(parser, userInput2, "The UUID provided is invalid: ");
    }
    @Test
    void parse_invalidInputMissingParts_throwsIllegalArgumentException() {
        String targetUuid = "0001";
        String originUuid = "0003";
        String userInput = targetUuid + "siblings";
        assertParseFailure(parser, userInput, "Invalid command format! \n%1$s");

        String userInput2 = originUuid + "siblings";
        assertParseFailure(parser, userInput2, "Invalid command format! \n%1$s");
    }

    @Test
    void parse_invalidInputFamilyWithRoles_throwsParseException() {
        String userInput = "parent 0001 child 0003 family";
        assertParseFailure(parser, userInput, "Please specify the type of familial relationship "
                + "instead of 'Family'.\n"
                + " Valid familial relations are: [bioParents, siblings, spouses]");
    }

    @Test
    void parse_invalidInputFamily_throwsParseException() {
        String userInput = "0001 0003 family";
        assertParseFailure(parser, userInput, "Please specify the type of "
                + "familial relationship instead of 'Family'.\n"
                + " Valid familial relations are: [bioParents, siblings, spouses]");
    }
}
