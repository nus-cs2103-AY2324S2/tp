package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.util.Objects.requireNonNull;

public class ImportCommand extends Command{

    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_SUCCESS = "Contacts from csv have been added!";
    public static final String MESSAGE_FILE_NOT_FOUND = "File could not be found! Check import.csv exists at ./import";
    public static final String MESSAGE_FIELDS_FORMAT_ERROR = "An error occurred while parsing the csv! " +
            "Check the fields!";
    public static final String MESSAGE_VALUES_FORMAT_ERROR = "An error occurred while parsing the csv! " +
            "Check the values!";
    public static final String MESSAGE_PARSE_FORMAT_ERROR = "An error occurred while adding persons to the csv! " +
            "The values in the csv are converted to add command format\n" +
            "Make sure the values match the correct format for the add command!\n" +
            "The error with the add command occurred as follows:\n";
    public static final String FILE_PATH = "./import/import.csv"; // Path to the .csv file to import

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Updates the Address book with contacts from the "
            + "csv file found at path: " + FILE_PATH
            + "Parameters: None "
            + "Example: " + COMMAND_WORD;

    private final List<String> FIELDS = List.of(
            "NAME", "NUMBER", "EMAIL", "ADDRESS", "TAG"
    ); // TO hold the fields present in the csv
    private final int NUMBER_OF_FIELDS = FIELDS.size();
    private final Map<String, String> PREFIX_MAP = Map.of(
            "NAME", "n/",
            "NUMBER", "p/",
            "EMAIL", "e/",
            "ADDRESS", "a/",
            "TAG", "t/"
    ); // To format the data in the csv to command format

    @Override
    public CommandResult execute(Model model) throws CommandException{
        requireNonNull(model);

        List<String> personsToAdd = new ArrayList<>(); // To hold the person data from the csv

        try { // parse the csv file at the path w scanner
            parse(personsToAdd);
            checkFields(personsToAdd.get(0));
            personsToAdd.remove(0); // remove the fields
            addPersons(personsToAdd, model);

        } catch (FileNotFoundException e) {
            throw new CommandException(MESSAGE_FILE_NOT_FOUND);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_VALUES_FORMAT_ERROR);
        } catch (ParseException e) {
            throw new CommandException(MESSAGE_PARSE_FORMAT_ERROR + e.getMessage());
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Parses the data in the csv into the given list
     *
     * @param list to hold the parsed values in String format
     */
    private void parse(List<String> list) throws FileNotFoundException, IndexOutOfBoundsException {
        StringBuilder curr_row = new StringBuilder(); // To hold the current row data
        Scanner sc = new Scanner(new File(FILE_PATH));
        sc.useDelimiter(",|\r?\n");
        int count = 0; // holds an idx to keep track of the current field

        while (sc.hasNext()) {
            String next = sc.next();

            if (!next.isEmpty()) { // append the field in the correct format if present
                String formatted = formatFieldValue(next, FIELDS.get(count));
                curr_row.append(formatted);
            }
            count += 1;
            if (count == NUMBER_OF_FIELDS) { // add the person data to the list once parsed
                curr_row.deleteCharAt(curr_row.length() - 1); // remove trailing " "
                list.add(curr_row.toString());
                curr_row = new StringBuilder();
                count = 0;
            }
        }
        sc.close();
    }

    /**
     * Formats the given data field into proper command format
     *
     * @param fieldValue the value to be formatted
     * @param field the field the value belongs to
     * @return the given field value formatted into command format
     */
    private String formatFieldValue(String fieldValue, String field) {
        final String UTF8_BOM = "\uFEFF";
        if (fieldValue.startsWith(UTF8_BOM)) { // to remove extra potential characters added due to format
            fieldValue = fieldValue.substring(1);
        }
        String prefix = PREFIX_MAP.get(field);
        return  prefix + fieldValue + " ";
    }

    /**
     * Returns True if given String matches the number and order of fields specified in FIELDS
     *
     * @param fieldString String parsed from the first row of the csv file
     * @return boolean for match between fieldString and FIELDS
     */
    private void checkFields(String fieldString) throws CommandException {
        StringBuilder fields = new StringBuilder();
        for (String field: FIELDS) {
            fields.append(PREFIX_MAP.get(field));
            fields.append(field).append(" ");
        }
        fields.deleteCharAt(fields.length() - 1); // adjust for extra " "
        if (!fieldString.equalsIgnoreCase(fields.toString())) {
            throw new CommandException(MESSAGE_FIELDS_FORMAT_ERROR);
        };
    }

    /**
     * Adds the person data from the list to the addressbook
     * Simulates the work of AddressBookParser
     *
     * @param personData the data parsed from the csv in list format
     * @param model reference to the model to add the data into
     */
    private void addPersons(List<String> personData, Model model) throws ParseException, CommandException {
        AddressBookParser addressBookParser = new AddressBookParser();
        // access model and add people into address book
        for (String person : personData) {
            String commandText = "add " + person;
            Command command = addressBookParser.parseCommand(commandText);
            CommandResult commandResult = command.execute(model);
        }
    }
}
