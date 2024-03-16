package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILENAME;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Optional;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonAdaptedPerson;

import javax.xml.crypto.Data;

/**
 * Imports contacts from a file into the contact manager.
 */
public class ImportCommand extends Command {
    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_SUCCESS = "Contacts from file imported";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports a datafile into the contact manager.\n"
            + "Parameters: "
            + PREFIX_FILENAME + "FILENAME_1 "
            + "[" + PREFIX_FILENAME + "FILENAME_2] "
            + "[" + PREFIX_FILENAME + "FILENAME_3] "
            + "...";

    public static final String MESSAGE_DUPLICATE_PERSON = "The person %s with phone number %s already exists in the contact manager.";

    public static final String MESSAGE_FILE_NOT_FOUND = "Filename %s is not found!";

    private static final Logger logger = LogsCenter.getLogger(ImportCommand.class);
    private final Set<File> files;

    /**
     * Constructs a new ImportCommand to add the contacts in the specified files in {@code fileSet}.
     *
     * @param fileSet A set of Files.
     */
    public ImportCommand(Set<File> fileSet) {
        requireNonNull(fileSet);
        files = fileSet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<JsonAdaptedPerson> jsonAdaptedPersonList = new ArrayList<>();
        List<Person> persons = new ArrayList<>();

        try {
            retrievePersonsFromFile(jsonAdaptedPersonList);
        } catch (IllegalValueException ive) {
            throw new CommandException(ive.getMessage());
        } catch (DataLoadingException dle) {
            throw new CommandException(dle.getMessage());
        }

        for (JsonAdaptedPerson jsonAdaptedPerson : jsonAdaptedPersonList) {
            try {
                Person person = jsonAdaptedPerson.toModelType();
                if (model.hasPerson(person)) {
                    logger.info(String.format(MESSAGE_DUPLICATE_PERSON, person.getName(), person.getPhone()));
                    throw new IllegalValueException(String.format(MESSAGE_DUPLICATE_PERSON,
                            person.getName(), person.getPhone()));
                }
                persons.add(person);
            } catch (IllegalValueException ive) {
                logger.info("Illegal value detected!: " + ive.getMessage());
                throw new CommandException(ive.getMessage());
            }
        }

        for (Person person : persons) {
            model.addPerson(person);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    /**
     * Retrieves the persons from {@code files} and adds it to {@code savedPersons}
     *
     * @param savedPersons A List of JsonAdaptedPerson.
     * @throws IllegalValueException If there are files not found or unable to be loaded.
     */
    public void retrievePersonsFromFile(List<JsonAdaptedPerson> savedPersons)
        throws IllegalValueException, DataLoadingException {
        for (File file : files) {
            if (!file.exists()) {
                logger.info(String.format(MESSAGE_FILE_NOT_FOUND, file.getPath()));
                throw new IllegalValueException(String.format(MESSAGE_FILE_NOT_FOUND, file.getPath()));
            }

            try {
                savedPersons.addAll(readPersons(file));
            } catch (DataLoadingException dle) {
                logger.info("Data loading exception in: " + file.getPath());
                throw dle;
            }
        }
    }

    /**
     * Reads the JsonAdaptedPersons inside {@code file} and returns a List of them.
     *
     * @param file A File.
     * @return A List of JsonAdaptedPerson present inside the {@code file}.
     * @throws DataLoadingException If the {@code file} is unable to be loaded.
     */
    public List<JsonAdaptedPerson> readPersons(File file)
    throws DataLoadingException {
        JsonAddressBookStorage curStorage = new JsonAddressBookStorage(file.toPath());
        Optional<ReadOnlyAddressBook> readOnlyAddressBook = curStorage.readAddressBook();
        return readOnlyAddressBook
                .orElseThrow(() ->
                        new DataLoadingException(new Exception("Cannot load file: " + file.getPath())))
                .getPersonList()
                .stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ImportCommand)) {
            return false;
        }

        ImportCommand otherImportCommand = (ImportCommand) other;
        return files.equals(otherImportCommand.files);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("files", files)
                .toString();
    }
}
