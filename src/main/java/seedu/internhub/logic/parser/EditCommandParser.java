package seedu.internhub.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.internhub.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.internhub.commons.core.index.Index;
import seedu.internhub.logic.commands.EditCommand;
import seedu.internhub.logic.parser.exceptions.ParseException;
import seedu.internhub.model.person.InterviewDate;


/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_PHONE,
                        CliSyntax.PREFIX_EMAIL, CliSyntax.PREFIX_ADDRESS, CliSyntax.PREFIX_TAG,
                        CliSyntax.PREFIX_JOB_DESCRIPTION, CliSyntax.PREFIX_INTERVIEW_DATE,
                        CliSyntax.PREFIX_INTERN_DURATION, CliSyntax.PREFIX_SALARY,
                        CliSyntax.PREFIX_NOTE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_PHONE,
                CliSyntax.PREFIX_EMAIL, CliSyntax.PREFIX_ADDRESS, CliSyntax.PREFIX_TAG,
                CliSyntax.PREFIX_JOB_DESCRIPTION, CliSyntax.PREFIX_INTERVIEW_DATE,
                CliSyntax.PREFIX_INTERN_DURATION,
                CliSyntax.PREFIX_SALARY);

        EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();

        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(CliSyntax.PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(CliSyntax.PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(
                    argMultimap.getValue(CliSyntax.PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_JOB_DESCRIPTION).isPresent()) {
            editPersonDescriptor.setJobDescription(
                    ParserUtil.parseJobDescription(argMultimap.getValue(CliSyntax.PREFIX_JOB_DESCRIPTION).get())
            );
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_INTERVIEW_DATE).isPresent()) {
            String givenDate = argMultimap.getValue(CliSyntax.PREFIX_INTERVIEW_DATE).get();
            if (!givenDate.isEmpty()) {
                editPersonDescriptor.setInterviewDate(
                        ParserUtil.parseInterviewDate(argMultimap.getValue(CliSyntax.PREFIX_INTERVIEW_DATE).get())
                );
            } else {
                editPersonDescriptor.setInterviewDate(new InterviewDate(null));
            }
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_INTERN_DURATION).isPresent()) {
            editPersonDescriptor.setInternDuration(
                    ParserUtil.parseInternDuration(argMultimap.getValue(CliSyntax.PREFIX_INTERN_DURATION).get())
            );
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_SALARY).isPresent()) {
            editPersonDescriptor.setSalary(ParserUtil.parseSalary(argMultimap.getValue(CliSyntax.PREFIX_SALARY).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_TAG).isPresent()) {
            editPersonDescriptor.setTag(ParserUtil.parseTag(argMultimap.getValue(CliSyntax.PREFIX_TAG).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_NOTE).isPresent()) {
            editPersonDescriptor.setNote(ParserUtil.parseNote(argMultimap.getValue(CliSyntax.PREFIX_NOTE).get()));
        }
        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }
}
