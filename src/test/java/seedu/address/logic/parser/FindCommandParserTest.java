package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.predicates.AddressContainsSubstringPredicate;
import seedu.address.model.person.predicates.CombinedPredicates;
import seedu.address.model.person.predicates.EmailContainsSubstringPredicate;
import seedu.address.model.person.predicates.NameContainsSubstringPredicate;
import seedu.address.model.person.predicates.NoteContainsSubstringPredicate;
import seedu.address.model.person.predicates.PhoneContainsSubstringPredicate;
import seedu.address.model.person.predicates.TagSetContainsAllTagsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nameFieldPresent_returnsFindCommand() {
        NameContainsSubstringPredicate namePredicate = new NameContainsSubstringPredicate("Alice");
        PhoneContainsSubstringPredicate phonePredicate = new PhoneContainsSubstringPredicate("");
        EmailContainsSubstringPredicate emailPredicate = new EmailContainsSubstringPredicate("");
        AddressContainsSubstringPredicate addressPredicate = new AddressContainsSubstringPredicate("");
        NoteContainsSubstringPredicate notePredicate = new NoteContainsSubstringPredicate("");
        TagSetContainsAllTagsPredicate tagsPredicate = new TagSetContainsAllTagsPredicate(new HashSet<>());

        FindCommand expectedCommand = new FindCommand(new CombinedPredicates(
                namePredicate, phonePredicate, emailPredicate, addressPredicate, notePredicate, tagsPredicate));

        assertParseSuccess(parser, " n/Alice", expectedCommand);

        // multiple whitespaces
        assertParseSuccess(parser, " n/Alice \n", expectedCommand);
    }

}
