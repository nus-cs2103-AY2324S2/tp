package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.attribute.Attribute;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Optional Parameters: /YourAttributeName YourAttributeValue\n"
            + "Predefined parameters: "
            + "/Name "
            + "/Phone "
            + "/Sex "
            + "/Birthday\n"
            + "Example: " + COMMAND_WORD + " "
            + "/Name John Doe "
            + "/Phone 98765432 "
            + "/Email johnd@example.com "
            + "/Address 311, Clementi Ave 2, #02-25 ";

    public static final String MESSAGE_SUCCESS = "New person added.\n%1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";
    private final HashMap<String, String> attributeMap;

    /**
     * Creates an AddCommand to add the specified {@code Person} and {@code Attribute}s.
     * The attributesToAdd array can be empty.
     */
    public AddCommand(HashMap<String, String> attributeMap) {
        requireNonNull(attributeMap);
        this.attributeMap = attributeMap;
    }

    /**
     * Executes the command to add a person to the address book.
     * All persons created have a unique uuid, thus user-created duplicate persons are impossible.
     *
     * @param model The model interface containing the address book data and methods needed to perform operations.
     * @return A CommandResult object containing the result message.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Attribute[] attributesToAdd = generateAttributeList();
        Person addedPerson = addPersonToModel(model, attributesToAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(addedPerson)));
    }

    private Person addPersonToModel(Model model, Attribute[] attributesToAdd) {
        Person personToAdd = new Person(attributesToAdd);
        model.addPerson(personToAdd);
        return personToAdd;
    }

    private Attribute[] generateAttributeList() {
        Attribute[] attributesToAdd = new Attribute[attributeMap.size()];

        for (int i = 0; i < attributeMap.size(); i++) {
            String attributeName = (String) attributeMap.keySet().toArray()[i];
            String attributeValue = attributeMap.get(attributeName);
            attributesToAdd[i] = Attribute.fromString(attributeName, attributeValue);
        }
        return attributesToAdd;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;

        boolean isEquals = attributeMap.equals(otherAddCommand.attributeMap);
        return isEquals;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("attributeMap", attributeMap)
                .toString();
    }
}
