package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Policy;

/**
 * Changes the policy of an existing person in the address book.
 */
public class PolicyCommand extends Command {

    public static final String COMMAND_WORD = "policy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the policy of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing policy will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_POLICY + "[POLICY]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_POLICY + "Likes to swim.";

    public static final String MESSAGE_ADD_POLICY_SUCCESS = "Added policy to Person: %1$s";
    public static final String MESSAGE_DELETE_POLICY_SUCCESS = "Removed policy from Person: %1$s";

    private final Index index;
    private final Policy policy;

    /**
     * @param index of the person in the filtered person list to edit the policy
     * @param policy of the person to be updated to
     */
    public PolicyCommand(Index index, Policy policy) {
        requireAllNonNull(index, policy);

        this.index = index;
        this.policy = policy;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getRelationship(), policy, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether the policy is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !policy.value.isEmpty() ? MESSAGE_ADD_POLICY_SUCCESS : MESSAGE_DELETE_POLICY_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PolicyCommand)) {
            return false;
        }

        // state check
        PolicyCommand e = (PolicyCommand) other;
        return index.equals(e.index)
                && policy.equals(e.policy);
    }
}
