package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUSID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.GroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;
import seedu.address.model.person.NusId;


/**
 * Parses input arguments and creates a new GroupCommand object
 */
public class GroupCommandParser implements Parser<GroupCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the GroupCommand
     * and returns an GroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GroupCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NUSID, PREFIX_GROUP, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NUSID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_USAGE));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_NUSID)
                && !arePrefixesPresent(argMultimap, PREFIX_GROUP)
                && !arePrefixesPresent(argMultimap, PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_GROUP, PREFIX_TAG);
        NusId nusid = ParserUtil.parseNusId(argMultimap.getValue(PREFIX_NUSID).get());


        GroupCommand.GroupPersonDescriptor groupPersonDescriptor = new GroupCommand.GroupPersonDescriptor();

        Set<NusId> nusIds = parseNusIdsForGroup(argMultimap.getAllValues(PREFIX_NUSID)).get();

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            groupPersonDescriptor.setTag(ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get()));
        }

        parseGroupsForGroup(argMultimap.getAllValues(PREFIX_GROUP)).ifPresent(groupPersonDescriptor::setGroups);
        return new GroupCommand(nusIds, groupPersonDescriptor);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses {@code Collection<String> groups} into a {@code Set<Group>} if {@code groups} is non-empty.
     * If {@code groups} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Group>} containing zero groups.
     */
    private Optional<Set<Group>> parseGroupsForGroup(Collection<String> groups) throws ParseException {
        assert groups != null;

        if (groups.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> groupSet = groups.size() == 1 && groups.contains("") ? Collections.emptySet() : groups;
        return Optional.of(ParserUtil.parseGroups(groupSet));
    }

    /**
     * Parses {@code Collection<String> nusIds} into a {@code Set<NusId>} if {@code nusIds} is non-empty.
     * If {@code nusIds} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<NusId>} containing zero nusId.
     */
    private Optional<Set<NusId>> parseNusIdsForGroup(Collection<String> nusIds) throws ParseException {
        assert nusIds != null;

        if (nusIds.isEmpty()) {
            return Optional.empty();
        }

        Collection<String> nusIdSet = nusIds.size() == 1 && nusIds.contains("") ? Collections.emptySet() : nusIds;
        return Optional.of(ParserUtil.parseNusIds(nusIdSet));
    }
}
