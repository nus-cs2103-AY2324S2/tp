
package seedu.address.logic.commands;


import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POINTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Points;
/**
 * Adds points to an existing person in the loyalty program.
 */
public class AddPointsCommand extends Command {
    public static final String COMMAND_WORD = "addpoints";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds points to the person identified"
            + "Parameters: NAME " + PREFIX_POINTS + "POINTS \n"
            + "Example: " + COMMAND_WORD + " John Doe " + PREFIX_POINTS + "40";

    public static final String MESSAGE_ARGUMENTS = "Name: %1$s, Points: %2$i";
    public static final String MESSAGE_ADDPOINTS_SUCCESS =
            "Added %1$s points to %2$s";
    private final Name name;
    private final Points points;

    /**
     * Constructs an AddPointsCommand to add the specified {@code Points}
     * to the person identified by {@code Name}.
     *
     * @param name of the person in the list to edit the points
     * @param points to be added to the persons current points
     */
    public AddPointsCommand(Name name, Points points) {
        requireAllNonNull(name, points);
        this.name = name;
        this.points = points;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        Optional<Person> personOptional = lastShownList.stream()
                .filter(person -> person.getName().equals(this.name))
                .findFirst();

        if (personOptional.isEmpty()) {
            throw new CommandException(
                    Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = personOptional.get();

        Points newPoints = new Points(Integer.toString(
                personToEdit.getPoints().getValue() + this.points.getValue()));

        Person editedPerson = new Person(personToEdit.getName(),
                personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getMembership(),
                personToEdit.getTags(), newPoints, personToEdit.getOrders());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof AddPointsCommand)) {
            return false;
        }

        AddPointsCommand e = (AddPointsCommand) other;
        return name.equals(e.name)
                && points.equals(e.points);
    }

    private String generateSuccessMessage(Person person) {
        return String.format(
                MESSAGE_ADDPOINTS_SUCCESS, this.points.value,
                person.getName());
    }
}
