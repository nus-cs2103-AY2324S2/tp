package staffconnect.logic.parser;

import static staffconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static staffconnect.logic.commands.CommandTestUtil.FACULTY_DESC_AMY;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_FACULTY_DESC;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_MODULE_DESC;
import static staffconnect.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static staffconnect.logic.commands.CommandTestUtil.MODULE_DESC_AMY;
import static staffconnect.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static staffconnect.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static staffconnect.logic.commands.CommandTestUtil.VALID_FACULTY_AMY;
import static staffconnect.logic.commands.CommandTestUtil.VALID_MODULE_AMY;
import static staffconnect.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static staffconnect.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static staffconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static staffconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import staffconnect.logic.commands.FilterCommand;
import staffconnect.model.person.Faculty;
import staffconnect.model.person.Module;
import staffconnect.model.person.PersonHasFacultyPredicate;
import staffconnect.model.person.PersonHasModulePredicate;
import staffconnect.model.person.PersonHasTagsPredicate;
import staffconnect.model.tag.Tag;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();
    private PersonHasModulePredicate emptyModulePredicate = new PersonHasModulePredicate(null);
    private PersonHasFacultyPredicate emptyFacultyPredicate = new PersonHasFacultyPredicate(null);
    private PersonHasTagsPredicate emptyTagsPredicate = new PersonHasTagsPredicate(null);

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidModule_throwsParseException() {
        // module is empty string (contains '')
        assertParseFailure(parser, INVALID_MODULE_DESC,
                String.format(Module.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_validModule_success() {
        Module module = new Module(VALID_MODULE_AMY);

        // single module
        // 1 leading and no trailing whitespaces (MODULE_DESC_xxx always has 1 leading)
        PersonHasModulePredicate modulePredicate = new PersonHasModulePredicate(module);
        FilterCommand expectedFilterCommand = new FilterCommand(modulePredicate, emptyFacultyPredicate,
                emptyTagsPredicate);
        assertParseSuccess(parser, MODULE_DESC_AMY, expectedFilterCommand);

        // 1 leading and multiple trailing whitespaces
        // 1 leading, 3 trailing
        assertParseSuccess(parser, MODULE_DESC_AMY + "   ", expectedFilterCommand);

        // multiple leading and trailing whitespaces
        // 2 leading, 1 trailing
        assertParseSuccess(parser, "  " + MODULE_DESC_AMY + " ", expectedFilterCommand);
        // 5 leading, 3 trailing
        assertParseSuccess(parser, "     " + MODULE_DESC_AMY + "   ", expectedFilterCommand);
    }

    @Test
    public void parse_invalidTagName_throwsParseException() {
        // tagname is non-alphanumeric (contains '*')
        assertParseFailure(parser, INVALID_TAG_DESC,
                String.format(Tag.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_validTagName_success() {
        Set<Tag> singleTag = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_FRIEND)));

        // single tag
        // 1 leading and no trailing whitespaces (TAG_DESC_xxx always has 1 leading)
        PersonHasTagsPredicate tagsPredicate = new PersonHasTagsPredicate(singleTag);
        FilterCommand expectedFilterCommand = new FilterCommand(emptyModulePredicate, emptyFacultyPredicate,
                tagsPredicate);
        assertParseSuccess(parser, TAG_DESC_FRIEND, expectedFilterCommand);

        // 1 leading and multiple trailing whitespaces
        // 1 leading, 3 trailing
        assertParseSuccess(parser, TAG_DESC_FRIEND + "   ", expectedFilterCommand);

        // multiple leading and trailing whitespaces
        // 2 leading, 1 trailing
        assertParseSuccess(parser, "  " + TAG_DESC_FRIEND + " ", expectedFilterCommand);
        // 5 leading, 3 trailing
        assertParseSuccess(parser, "     " + TAG_DESC_FRIEND + "   ", expectedFilterCommand);

        // multiple tags
        Set<Tag> multipleTags = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_FRIEND),
                new Tag(VALID_TAG_HUSBAND)));
        tagsPredicate = new PersonHasTagsPredicate(multipleTags);
        expectedFilterCommand = new FilterCommand(emptyModulePredicate, emptyFacultyPredicate, tagsPredicate);

        // 1 leading and no trailing whitespaces
        assertParseSuccess(parser, TAG_DESC_FRIEND + TAG_DESC_HUSBAND, expectedFilterCommand);

        // 1 leading and multiple trailing whitespaces
        // 1 leading, 3 trailing
        assertParseSuccess(parser, TAG_DESC_FRIEND + TAG_DESC_HUSBAND + "   ", expectedFilterCommand);

        // multiple leading and trailing whitespaces
        // 2 leading, 1 trailing
        assertParseSuccess(parser, "  " + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + " ", expectedFilterCommand);
        // 5 leading, 3 trailing
        assertParseSuccess(parser, "     " + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + "   ", expectedFilterCommand);

        // whitespaces in middle
        // 1 leading, 1 middle, 3 trailing
        assertParseSuccess(parser, TAG_DESC_FRIEND + " " + TAG_DESC_HUSBAND + "   ", expectedFilterCommand);
        // 1 leading, 3 middle, 1 trailing
        assertParseSuccess(parser, TAG_DESC_FRIEND + "   " + TAG_DESC_HUSBAND + " ", expectedFilterCommand);
    }

    @Test
    public void parse_invalidFaculty_throwsParseException() {
        // invalid faculty -> "faculty"
        assertParseFailure(parser, INVALID_FACULTY_DESC,
                String.format(Faculty.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_validFaculty_success() {
        Faculty faculty = new Faculty(VALID_FACULTY_AMY);

        // single faculty
        // 1 leading and no trailing whitespaces (FACULTY_DESC_xxx always has 1 leading)
        PersonHasFacultyPredicate facultyPredicate = new PersonHasFacultyPredicate(faculty);
        FilterCommand expectedFilterCommand = new FilterCommand(emptyModulePredicate, facultyPredicate,
                emptyTagsPredicate);
        assertParseSuccess(parser, FACULTY_DESC_AMY, expectedFilterCommand);

        // 1 leading and multiple trailing whitespaces
        // 1 leading, 3 trailing
        assertParseSuccess(parser, FACULTY_DESC_AMY + "   ", expectedFilterCommand);

        // multiple leading and trailing whitespaces
        // 2 leading, 1 trailing
        assertParseSuccess(parser, "  " + FACULTY_DESC_AMY + " ", expectedFilterCommand);
        // 5 leading, 3 trailing
        assertParseSuccess(parser, "     " + FACULTY_DESC_AMY + "   ", expectedFilterCommand);
    }

}
