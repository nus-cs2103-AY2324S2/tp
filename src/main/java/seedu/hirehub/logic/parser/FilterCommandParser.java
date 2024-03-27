package seedu.hirehub.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.hirehub.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.ArrayList;
import java.util.Optional;

import seedu.hirehub.logic.commands.FilterCommand;
import seedu.hirehub.logic.parser.exceptions.ParseException;
import seedu.hirehub.model.person.ContainsKeywordsPredicate;
import seedu.hirehub.model.person.Email;
import seedu.hirehub.model.person.SearchPredicate;
import seedu.hirehub.model.person.Status;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {


    @Override
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_EMAIL, PREFIX_TITLE, PREFIX_STATUS);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        if (!args.trim().isEmpty()) {
            throw new ParseException(FilterCommand.MESSAGE_NO_FIELD_PROVIDED);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_EMAIL, PREFIX_TITLE, PREFIX_STATUS);

        ArrayList<ContainsKeywordsPredicate> predicateList = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
            ContainsKeywordsPredicate<Email> emailSearch =
                    new ContainsKeywordsPredicate<>(PREFIX_EMAIL, Optional.of(email));
            predicateList.add(emailSearch);
        }

        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            String title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
            ContainsKeywordsPredicate<Title> titleSearch = new ContainsKeywordsPredicate<>(PREFIX_TITLE, title);
            predicateList.add(titleSearch);
        }

        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            Status status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
            ContainsKeywordsPredicate<Status> statusSearch =
                    new ContainsKeywordsPredicate<>(PREFIX_STATUS, Optional.of(status));
            predicateList.add(statusSearch);
        }

        return new FilterCommand(new SearchPredicate(predicateList));
    }
}
