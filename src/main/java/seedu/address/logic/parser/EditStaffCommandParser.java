package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.messages.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMISSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditStaffCommand;
import seedu.address.logic.commands.EditStaffCommand.EditStaffDescriptor;
import seedu.address.logic.messages.EditMessages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditStaffCommand object
 */
public class EditStaffCommandParser implements Parser<EditStaffCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditStaffCommand
     * and returns an EditStaffCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditStaffCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Name name;
        String fieldArgs;

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_FIELD);

        boolean hasDuplicateNamePrefix = argMultimap.hasDuplicateNamePrefix();
        if (hasDuplicateNamePrefix) {
            throw new ParseException(String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD,
                    "Editing Pooch Contact name is not allowed!"));
        }

        // check for missing name
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            throw new ParseException(String.format(EditMessages.MESSAGE_EDIT_MISSING_NAME,
                    EditStaffCommand.MESSAGE_USAGE));
        }

        // check for missing field
        if (!arePrefixesPresent(argMultimap, PREFIX_FIELD)) {
            throw new ParseException(String.format(EditMessages.MESSAGE_EDIT_MISSING_FIELD,
                    EditStaffCommand.MESSAGE_USAGE));
        }

        name = mapName(argMultimap);
        fieldArgs = mapFields(argMultimap);

        ArgumentMultimap fieldArgMultimap =
                ArgumentTokenizer.tokenize(fieldArgs, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_NAME, PREFIX_PRODUCT, PREFIX_PRICE, PREFIX_EMPLOYMENT, PREFIX_SALARY,
                        PREFIX_SKILL, PREFIX_COMMISSION);

        // check for invalid fields
        checkEditFieldValidity(fieldArgMultimap);

        fieldArgMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_EMPLOYMENT, PREFIX_SALARY);

        EditStaffDescriptor editStaffDescriptor;

        try {
            editStaffDescriptor = editStaffDescription(fieldArgMultimap);
        } catch (ParseException pe) {
            throw new ParseException(String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD, pe.getMessage()));
        }

        if (!editStaffDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditMessages.MESSAGE_EDIT_EMPTY_FIELD);
        }

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("staff"));
        editStaffDescriptor.setTags(tags);

        return new EditStaffCommand(name, editStaffDescriptor);
    }

    /**
     * Checks all invalid fields for staff class.
     * @param fieldArgMultimap The prefix to value mapping.
     * @throws ParseException Throws when there exist invalid fields.
     */
    public void checkEditFieldValidity(ArgumentMultimap fieldArgMultimap) throws ParseException {
        ArrayList<String> invalidFields = new ArrayList<>();
        if (arePrefixesPresent(fieldArgMultimap, PREFIX_NAME)) {
            invalidFields.add("name");
        }
        if (arePrefixesPresent(fieldArgMultimap, PREFIX_PRODUCT)) {
            invalidFields.add("product");
        }
        if (arePrefixesPresent(fieldArgMultimap, PREFIX_PRICE)) {
            invalidFields.add("price");
        }
        if (arePrefixesPresent(fieldArgMultimap, PREFIX_SKILL)) {
            invalidFields.add("skill");
        }
        if (arePrefixesPresent(fieldArgMultimap, PREFIX_COMMISSION)) {
            invalidFields.add("commission");
        }
        if (invalidFields.size() > 0) {
            throw new ParseException(formatInvalidFieldString(invalidFields));
        }
    }

    /**
     * Formats the string for invalid fields.
     * @param invalidFields The exact invalid fields.
     * @return A string that specifies the exact invalid fields.
     */
    public String formatInvalidFieldString(ArrayList<String> invalidFields) {
        String formattedFieldString = "";
        if (invalidFields.size() == 1) {
            return String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD,
                "Editing Pooch Contact " + invalidFields.get(0) + " is not allowed for staff");
        }
        for (int i = 0; i < invalidFields.size(); i++) {
            if (i == invalidFields.size() - 2) {
                formattedFieldString += invalidFields.get(i) + " and ";
            } else if (i == invalidFields.size() - 1) {
                formattedFieldString += invalidFields.get(i);
            } else {
                formattedFieldString += invalidFields.get(i) + ", ";
            }
        }
        return String.format(EditMessages.MESSAGE_EDIT_INVALID_FIELD,
                "Editing Pooch Contact " + formattedFieldString + " is not allowed for staff");
    }

    /**
     * Returns name value using PREFIX.
     * @param argMultimap Object that contains mapping of prefix to value.
     * @return Returns object representing name.
     * @throws ParseException Thrown when command is in invalid format.
     */
    public Name mapName(ArgumentMultimap argMultimap) throws ParseException {
        try {
            return ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(EditMessages.MESSAGE_EDIT_INVALID_NAME, pe.getMessage()));
        }
    }

    /**
     * Returns field values using PREFIX.
     * @param argMultimap Object that contains mapping of prefix to value.
     * @return Returns object representing the respective fields.
     * @throws ParseException Thrown when command is in invalid format.
     */
    public String mapFields(ArgumentMultimap argMultimap) throws ParseException {
        try {
            return ParserUtil.parseField(argMultimap.getValue(PREFIX_FIELD).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditStaffCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Edits the description of a Staff.
     *
     * @param fieldArgMultimap The mapping of field arguments into different specific fields.
     * @return EditStaffDescriptor that contains the new values from the user.
     * @throws ParseException Indicates the invalid format that users might have entered.
     */
    private EditStaffDescriptor editStaffDescription(ArgumentMultimap fieldArgMultimap) throws ParseException {
        EditStaffDescriptor editStaffDescription = new EditStaffDescriptor();

        if (fieldArgMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editStaffDescription.setPhone(ParserUtil.parsePhone(fieldArgMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editStaffDescription.setEmail(ParserUtil.parseEmail(fieldArgMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editStaffDescription.setAddress(ParserUtil.parseAddress(fieldArgMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_SALARY).isPresent()) {
            editStaffDescription.setSalary(ParserUtil.parseSalary(fieldArgMultimap.getValue(PREFIX_SALARY).get()));
        }
        if (fieldArgMultimap.getValue(PREFIX_EMPLOYMENT).isPresent()) {
            editStaffDescription.setEmployment(ParserUtil.parseEmployment(
                    fieldArgMultimap.getValue(PREFIX_EMPLOYMENT).get()));
        }

        return editStaffDescription;
    }
}
