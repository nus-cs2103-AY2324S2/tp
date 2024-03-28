package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREMIUM;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            + "Existing policy will be overwritten by the input. If policy is not presented, "
            + "it will be considered as removed policy.\n"
            + "There could be multiple policies, expiry date and premium is optional.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_POLICY + "[POLICY] "
            + PREFIX_EXPIRY_DATE + "[EXPIRY DATE] "
            + PREFIX_PREMIUM + "[PREMIUM]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_POLICY + "Policy XYZ "
            + PREFIX_EXPIRY_DATE + "01-01-2020";

    public static final String MESSAGE_ADD_POLICY_SUCCESS = "Added policy to Person: %1$s";
    public static final String MESSAGE_DELETE_POLICY_SUCCESS = "Removed policy from Person: %1$s";
    public static final String MESSAGE_PERSON_NOT_CLIENT_FAILURE =
            "Invalid person. Only clients can be assigned a policy";

    private final Index index;
    private final Set<Policy> policies = new HashSet<>();

    /**
     * @param index of the person in the filtered person list to edit the policy
     * @param policies of the person to be updated to
     */
    public PolicyCommand(Index index, Set<Policy> policies) {
        requireAllNonNull(index, policies);

        this.index = index;
        this.policies.addAll(policies);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (!personToEdit.isClient()) {
            throw new CommandException(MESSAGE_PERSON_NOT_CLIENT_FAILURE);
        }

        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getRelationship(), policies, personToEdit.getClientStatus(),
                personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.commitAddressBook();

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether the policy is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !policies.isEmpty() ? MESSAGE_ADD_POLICY_SUCCESS : MESSAGE_DELETE_POLICY_SUCCESS;
        return String.format(message, Messages.format(personToEdit));
    }

    public Index getIndex() {
        return this.index;
    }

    public Set<Policy> getPolicies() {
        return this.policies;
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

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        // state check
        PolicyCommand e = (PolicyCommand) other;
        return index.equals(e.index)
                && policies.equals(e.policies);
    }
}
