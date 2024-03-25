package seedu.address.logic.commands;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Policy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class DeletePolicyCommand extends Command {
    private final Logger logger = LogsCenter.getLogger(DeletePolicyCommand.class);

    public static final String COMMAND_WORD = "delPolicy";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the specific policy linked to the client identified "
            + "by the index number used in the last person listing.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "pol/ [POLICY NAME]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "pol/ SuperSaver";

    public static final String MESSAGE_DELETE_POLICY_SUCCESS = "Removed policy from Person: %1$s";

    Index index;
    String policyName;

    public DeletePolicyCommand(Index index, String policyName) {
        requireAllNonNull(index, policyName);
        this.index = index;
        this.policyName = policyName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Set<Policy> currentPolicies = new HashSet<>(personToEdit.getPolicies());
        String targetPolicyName = policyName;
        boolean isRemoved = currentPolicies.removeIf(policy -> policy.policyName.equals(targetPolicyName));

        if (!isRemoved) {
            throw new CommandException(Messages.MESSAGE_INVALID_POLICY);
        }

        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getMeeting(), personToEdit.getTags(), currentPolicies);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    private String generateSuccessMessage(Person editedPerson) {
        return String.format(MESSAGE_DELETE_POLICY_SUCCESS, editedPerson);
    }
}
