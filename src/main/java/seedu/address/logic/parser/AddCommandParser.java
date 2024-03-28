package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRIC_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REFLECTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDIO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.exam.Exam;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Score;
import seedu.address.model.student.Matric;
import seedu.address.model.student.Reflection;
import seedu.address.model.student.Studio;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_TAG, PREFIX_MATRIC_NUMBER, PREFIX_REFLECTION, PREFIX_STUDIO);

        if (!arePrefixesPresent(
                argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }


        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                                                 PREFIX_MATRIC_NUMBER, PREFIX_REFLECTION, PREFIX_STUDIO);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Matric matric = handleOptionalMatric(argMultimap);
        Reflection reflection = handleOptionalReflection(argMultimap);
        Studio studio = handleOptionalStudio(argMultimap);
        Map<Exam, Score> scores = new HashMap<>();

        // Update the tagList automatically
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        tagList = autoTag(tagList, matric, reflection, studio);

        Person person = new Person(name, phone, email, address, tagList, matric, reflection, studio, scores);

        return new AddCommand(person);
    }

    /**
     * Automatically adds a tag to the tagList based on the presence of matric, reflection and studio.
     * @param tagList the list of tags to be updated
     * @param matric the matric number of the person
     * @param reflection the reflection of the person
     * @param studio the studio of the person
     * @return
     */
    private Set<Tag> autoTag(Set<Tag> tagList, Matric matric, Reflection reflection, Studio studio) {
        boolean isMatricPresent = !Matric.isEmptyMatric(matric.matricNumber);
        boolean isReflectionPresent = !Reflection.isEmptyReflection(reflection.reflection);
        boolean isStudioPresent = !Studio.isEmptyStudio(studio.studio);

        Optional<Tag> autoTag = createTag(isMatricPresent, isReflectionPresent, isStudioPresent);
        autoTag.ifPresent(tagList::add);

        return tagList;
    }

    /**
     * Creates a tag based on the presence of matric, reflection and studio.
     * @param isMatricPresent boolean whether a Matric number is present.
     * @param isReflectionPresent boolean whether a Reflection is present.
     * @param isStudioPresent boolean whether a Studio is present.
     * @return an Optional Tag based on the presence of matric, reflection and studio.
     */
    private Optional<Tag> createTag(boolean isMatricPresent, boolean isReflectionPresent, boolean isStudioPresent) {
        if (isMatricPresent && isReflectionPresent && isStudioPresent) {
            return Optional.of(new Tag("student"));
        } else if (isMatricPresent && (isReflectionPresent ^ isStudioPresent)) {
            return Optional.of(new Tag("TA"));
        } else if (!isMatricPresent && !isReflectionPresent && !isStudioPresent) {
            return Optional.of(new Tag("instructor"));
        }
        return Optional.empty();
    }

    private static Matric handleOptionalMatric(ArgumentMultimap argMultimap) throws ParseException {
        if (!arePrefixesPresent(argMultimap, PREFIX_MATRIC_NUMBER)) {
            return new Matric("");
        } else {
            return ParserUtil.parseMatric(argMultimap.getValue(PREFIX_MATRIC_NUMBER).get());
        }
    }

    private static Reflection handleOptionalReflection(ArgumentMultimap argMultimap) throws ParseException {
        if (!arePrefixesPresent(argMultimap, PREFIX_REFLECTION)) {
            return new Reflection("");
        } else {
            return ParserUtil.parseReflection(argMultimap.getValue(PREFIX_REFLECTION).get());
        }
    }

    private static Studio handleOptionalStudio(ArgumentMultimap argMultimap) throws ParseException {
        if (!arePrefixesPresent(argMultimap, PREFIX_STUDIO)) {
            return new Studio("");
        } else {
            return ParserUtil.parseStudio(argMultimap.getValue(PREFIX_STUDIO).get());
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
