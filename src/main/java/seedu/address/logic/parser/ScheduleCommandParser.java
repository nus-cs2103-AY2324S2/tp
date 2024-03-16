package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import seedu.address.model.person.NusId;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Schedule;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

public class ScheduleCommandParser implements Parser<ScheduleCommand> {
    public ScheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NUSID, PREFIX_SCHEDULE, PREFIX_REMARK);

        if (!arePrefixesPresent(argMultimap, PREFIX_NUSID)
                || !argMultimap.getPreamble().isEmpty()
                || (arePrefixesPresent(argMultimap, PREFIX_REMARK)
                && !arePrefixesPresent(argMultimap, PREFIX_SCHEDULE))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NUSID, PREFIX_SCHEDULE, PREFIX_REMARK);
        NusId nusId = ParserUtil.parseNusId(argMultimap.getValue(PREFIX_NUSID).get());
        Schedule schedule = ParserUtil.parseSchedule(argMultimap.getValue(PREFIX_SCHEDULE).orElse(""));
        String remark = argMultimap.getValue(PREFIX_REMARK).orElse("");

        return new ScheduleCommand(nusId, schedule, new Remark(remark));
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}