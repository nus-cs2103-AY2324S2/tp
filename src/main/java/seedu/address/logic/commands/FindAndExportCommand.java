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

/**
 * Represents a command to find users based on specified criteria and export their information.
 * The command allows filtering of users by tags, name, and address, and exports the
 * filtered list to a specified file.
 */
public class FindAndExportCommand extends Command {

    public static final String COMMAND_WORD = "find_and_export";
    public static final String MESSAGE_USAGE = "find_and_export: Exports the users filtered by a tag "
            + "and other optional parameters.\n"
            + "Parameters: TAG [n/NAME] [a/ADDRESS] [o/FILENAME]\n"
            + "Example: find_and_export cs2103t n/john a/olive street 42 o/output1";
    private final String tag;
    private final String name;
    private final String address;
    private final String filename;

    /**
     * Constructs a FindAndExportCommand to find and export users' information.
     *
     * @param tag The tag by which users are filtered.
     * @param name The name substring by which users are further filtered. Can be {@code null}.
     * @param address The address substring by which users are further filtered. Can be {@code null}.
     * @param filename The name of the file to which the filtered users are exported.
     */
    public FindAndExportCommand(String tag, String name, String address, String filename) {
        this.tag = tag;
        this.name = name;
        this.address = address;
        this.filename = filename;
    }

    public String getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getFilename() {
        return filename;
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
            predicate = predicate.and(person -> person.getAddress().value
                    .toLowerCase().contains(address.toLowerCase()));
        }

        return predicate;
    }

    private void exportData(List<Person> users, String filename) throws IOException {
        Path path = Paths.get(filename).toAbsolutePath();
        if (Files.exists(path) && !Files.isWritable(path)) {
            throw new IOException("File exists but is not writable: " + path);
        }
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
