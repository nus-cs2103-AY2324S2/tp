package seedu.address.logic.commands;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class FindAndExportCommand extends Command {

    public static final String COMMAND_WORD = "find_and_export";
    private final String tag;
    private final String name;
    private final String address;
    private final String filename;
    public static final String MESSAGE_USAGE = "find_and_export: Exports the users filtered by a tag "
            + "and other optional parameters.\n"
            + "Parameters: TAG [n/NAME] [a/ADDRESS] [o/FILENAME]\n"
            + "Example: find_and_export cs2103t n/john a/olive street 42 o/output1";

    public FindAndExportCommand(String tag, String name, String address, String filename) {
        this.tag = tag;
        this.name = name;
        this.address = address;
        this.filename = filename;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Predicate<Person> predicate = createPredicateForFiltering(tag, name, address);
        model.updateFilteredPersonList(predicate);

        List<Person> filteredList = model.getFilteredPersonList();

        if (filteredList.isEmpty()) {
            return new CommandResult("No users found.");
        }

        try {
            exportData(filteredList, filename);
            return new CommandResult(String.format("Export successful to [%s].", filename));
        } catch (IOException e) {
            throw new CommandException("Error exporting data: " + e.getMessage());
        }
    }

    private Predicate<Person> createPredicateForFiltering(String tag, String name, String address) {
        Predicate<Person> predicate = person -> true; // start with a predicate that always returns true

        if (tag != null && !tag.isBlank()) {
            predicate = predicate.and(person -> person.getTags().stream().anyMatch(t -> t.tagName.equals(tag)));
        }
        if (name != null && !name.isBlank()) {
            predicate = predicate.and(person -> person.getName().fullName.toLowerCase().contains(name.toLowerCase()));
        }
        if (address != null && !address.isBlank()) {
            predicate = predicate.and(person -> person.getAddress().value.toLowerCase().contains(address.toLowerCase()));
        }

        return predicate;
    }

    private void exportData(List<Person> users, String filename) throws IOException {
        Path path = Paths.get(filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("Name,Email,Address\n");

            for (Person user : users) {
                String line = String.format("\"%s\",\"%s\",\"%s\"\n",
                        user.getName().fullName,
                        user.getEmail().value,
                        user.getAddress().value);
                writer.write(line);
            }
        }
    }
}
