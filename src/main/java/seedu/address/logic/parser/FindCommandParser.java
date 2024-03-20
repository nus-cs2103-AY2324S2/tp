package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DRUG_ALLERGY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ILLNESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.BirthdateContainsKeywordsPredicate;
import seedu.address.model.person.predicates.DrugAllergyContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.GenderContainsKeywordsPredicate;
import seedu.address.model.person.predicates.IllnessContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NricContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_NRIC,
                        PREFIX_NAME,
                        PREFIX_GENDER,
                        PREFIX_BIRTHDATE,
                        PREFIX_PHONE,
                        PREFIX_EMAIL,
                        PREFIX_DRUG_ALLERGY,
                        PREFIX_ILLNESS);

        if (!atLeastOnePrefixPresent(argMultimap, PREFIX_NRIC, PREFIX_NAME, PREFIX_BIRTHDATE, PREFIX_PHONE,
                 PREFIX_EMAIL, PREFIX_GENDER, PREFIX_DRUG_ALLERGY, PREFIX_ILLNESS)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NRIC, PREFIX_NAME, PREFIX_BIRTHDATE, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_GENDER, PREFIX_DRUG_ALLERGY, PREFIX_ILLNESS);


        String[] nricKeywords;
        String[] nameKeywords;
        String[] genderKeywords;
        String[] birthdateKeywords;
        String[] phoneKeywords;
        String[] emailKeywords;
        String[] allergyKeywords;
        String[] illnessKeywords;

        // Extract search parameters based on prefixes
        ArrayList<Predicate<Person>> predicates = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_NRIC).isPresent()) {
            nricKeywords = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get())
                    .toString()
                    .split("\\s+");
            predicates.add(new NricContainsKeywordsPredicate(Arrays.asList(nricKeywords)));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            nameKeywords = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get())
                    .toString()
                    .split("\\s+");
            predicates.add(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            genderKeywords = ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get())
                    .toString()
                    .split("\\s+");
            predicates.add(new GenderContainsKeywordsPredicate(Arrays.asList(genderKeywords)));
        }

        if (argMultimap.getValue(PREFIX_BIRTHDATE).isPresent()) {
            birthdateKeywords = ParserUtil.parseBirthDate(argMultimap.getValue(PREFIX_BIRTHDATE).get())
                    .toString()
                    .split("\\s+");
            predicates.add(new BirthdateContainsKeywordsPredicate(Arrays.asList(birthdateKeywords)));
        }

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            phoneKeywords = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get())
                    .toString()
                    .split("\\s+");
            predicates.add(new PhoneContainsKeywordsPredicate(Arrays.asList(phoneKeywords)));
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            emailKeywords = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get())
                    .toString()
                    .split("\\s+");
            predicates.add(new EmailContainsKeywordsPredicate(Arrays.asList(emailKeywords)));
        }

        if (argMultimap.getValue(PREFIX_DRUG_ALLERGY).isPresent()) {
            allergyKeywords = ParserUtil.parseDrugAllergy(argMultimap.getValue(PREFIX_DRUG_ALLERGY).get())
                    .toString()
                    .split("\\s+");
            predicates.add(new DrugAllergyContainsKeywordsPredicate(Arrays.asList(allergyKeywords)));
        }

        if (argMultimap.getValue(PREFIX_ILLNESS).isPresent()) {
            illnessKeywords = ParserUtil.parseIllness(argMultimap.getValue(PREFIX_ILLNESS).get())
                    .toString()
                    .replaceAll("[\\[\\],]", "")
                    .split("\\s+");
            predicates.add(new IllnessContainsKeywordsPredicate(Arrays.asList(illnessKeywords)));
        }

        Predicate<Person> combinedPredicate = predicates.stream().reduce(w -> true, Predicate::and);
        return new FindCommand(combinedPredicate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean atLeastOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
