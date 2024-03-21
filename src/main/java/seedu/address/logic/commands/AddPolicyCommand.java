package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import seedu.address.logic.Messages;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PolicyName;
import seedu.address.commons.core.index.Index;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class AddPolicyCommand extends Command {
    public static final String COMMAND_WORD = "addPolicy";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the remark of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "r/ [REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "r/ Likes to swim.";

    public static final String MESSAGE_ARGUMENTS = "Index %1$d, Remark: %2$s";
    public static final String MESSAGE_ADD_POLICY_NAME_SUCCESS = "Added remark to Person: %1$s";
    public static final String MESSAGE_DELETE_POLICY_NAME_SUCCESS = "Removed remark from Person: %1$s";

    private final Index index;
    private final PolicyName policyName;

    public AddPolicyCommand(Index index, PolicyName policyName) {
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
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail().orElse(null),
                personToEdit.getAddress().orElse(null), policyName, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    private String generateSuccessMessage(Person personToEdit) {
        String message = !policyName.value.isEmpty() ? MESSAGE_ADD_POLICY_NAME_SUCCESS : MESSAGE_DELETE_POLICY_NAME_SUCCESS;
        return String.format(message, personToEdit);
    }
}
