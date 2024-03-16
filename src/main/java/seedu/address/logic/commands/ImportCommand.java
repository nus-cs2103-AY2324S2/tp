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

/**
 * Imports contacts from a file into the contact manager.
 */
public class ImportCommand extends Command {
    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_SUCCESS = "Contacts from file imported";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports a datafile into the contact manager."
            + "Parameters: "
            + PREFIX_FILENAME + "FILENAME_1 "
            + "[" + PREFIX_FILENAME + "FILENAME_2 "
            + "[" + PREFIX_FILENAME + "FILENAME_3 "
            + "...";

    public static final String MESSAGE_DUPLICATE_PERSON = "The person %s already exists in the contact manager.";

    private static final Logger logger = LogsCenter.getLogger(ImportCommand.class);
    private final Set<File> files;

    public ImportCommand(Set<File> fileSet) {
        requireNonNull(fileSet);
        files = fileSet;
    }
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<JsonAdaptedPerson> savedPersons = new ArrayList<>();
        retrievePersonsFromFile(savedPersons);
        for (JsonAdaptedPerson jsonAdaptedPerson : savedPersons) {
            try {
                Person person = jsonAdaptedPerson.toModelType();
                if (model.hasPerson(person)) {
                    logger.info(String.format(MESSAGE_DUPLICATE_PERSON, person.toString()));
                    continue;
                }
                model.addPerson(person);
            } catch (IllegalValueException ive) {
                logger.info("Illegal value detected!");
            }
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    private void retrievePersonsFromFile(List<JsonAdaptedPerson> savedPersons) {
        for (File file : files) {
            if (!file.exists()) {
                logger.info("File path: " + file.getPath() + " not found");
            }
            JsonAddressBookStorage curStorage = new JsonAddressBookStorage(file.toPath());
            try {
                Optional<ReadOnlyAddressBook> readOnlyAddressBook = curStorage.readAddressBook();
                savedPersons.addAll(
                        readOnlyAddressBook
                                .get()
                                .getPersonList()
                                .stream()
                                .map(JsonAdaptedPerson::new)
                                .collect(Collectors.toList()));
            } catch (DataLoadingException dle) {
                logger.info("Data loading exception in: " + file.getPath());
            }
        }
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
