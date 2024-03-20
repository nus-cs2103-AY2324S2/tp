package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.SKILL_DESC_CPP;
import static seedu.address.logic.commands.CommandTestUtil.SKILL_DESC_CSHARP;
import static seedu.address.logic.commands.CommandTestUtil.SKILL_DESC_JAVA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_CPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_CSHARP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COURSE_MATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_COURSE_MATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_COURSE_MATE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteSkillCommand;
import seedu.address.logic.commands.DeleteSkillCommand.DeleteSkillDescriptor;
import seedu.address.model.coursemate.QueryableCourseMate;
import seedu.address.testutil.DeleteSkillDescriptorBuilder;

public class DeleteSkillCommandParserTest {
    private static final String SKILL_EMPTY = " " + PREFIX_SKILL;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSkillCommand.MESSAGE_USAGE);

    private DeleteSkillCommandParser parser = new DeleteSkillCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, SKILL_DESC_CPP, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "#1", DeleteSkillCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "#-5" + SKILL_DESC_CPP, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "#0" + SKILL_DESC_JAVA, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "#1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "#1 -i string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_singleSkill_success() {
        Index targetIndex = INDEX_THIRD_COURSE_MATE;
        String userInput = "#" + targetIndex.getOneBased() + SKILL_DESC_CSHARP;

        DeleteSkillDescriptor descriptor = new DeleteSkillDescriptorBuilder()
                .withSkills(VALID_SKILL_CSHARP).build();
        DeleteSkillCommand expectedCommand = new DeleteSkillCommand(new QueryableCourseMate(targetIndex), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleSkills_success() {
        Index targetIndex = INDEX_SECOND_COURSE_MATE;
        String userInput = "#" + targetIndex.getOneBased() + SKILL_DESC_CPP + SKILL_DESC_CSHARP + SKILL_DESC_JAVA;

        DeleteSkillDescriptor descriptor = new DeleteSkillDescriptorBuilder()
                .withSkills(VALID_SKILL_CSHARP, VALID_SKILL_JAVA, VALID_SKILL_CPP).build();
        DeleteSkillCommand expectedCommand = new DeleteSkillCommand(new QueryableCourseMate(targetIndex), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_duplicateSkills_success() {
        Index targetIndex = INDEX_FIRST_COURSE_MATE;
        String userInput = "#" + targetIndex.getOneBased() + SKILL_DESC_CPP + SKILL_DESC_CPP;

        DeleteSkillDescriptor descriptor = new DeleteSkillDescriptorBuilder()
                .withSkills(VALID_SKILL_CPP).build();
        DeleteSkillCommand expectedCommand = new DeleteSkillCommand(new QueryableCourseMate(targetIndex), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_emptySkill_success() {
        Index targetIndex = INDEX_THIRD_COURSE_MATE;
        String userInput = "#" + targetIndex.getOneBased() + SKILL_EMPTY;

        DeleteSkillDescriptor descriptor = new DeleteSkillDescriptorBuilder().withSkills().build();
        DeleteSkillCommand expectedCommand = new DeleteSkillCommand(new QueryableCourseMate(targetIndex), descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
