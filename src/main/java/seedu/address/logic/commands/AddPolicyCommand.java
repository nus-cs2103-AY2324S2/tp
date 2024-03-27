package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Policy;

/**
 * Adds a policy to the client identified by the index number used in the last person listing
 * and the policy name.
 */
public class AddPolicyCommand extends Command {

    public static final String COMMAND_WORD = "addPolicy";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a policy to the client identified "
            + "by the index number used in the last person listing\n"
            + "and relevant fields. "
            + "Parameters: INDEX (must be a positive integer) "
            + "pol/[POLICY NAME] polnum/[POLICY ID] (POLICY ID must be at least 3 digits)\n"
            + "pterm/[PREMIUM_TERM] + prem/[POLICY PREMIUM] b/[BENEFIT]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "pol/SuperSaver polnum/393 pterm/3months prem/3000 b/100000";

    public static final String MESSAGE_ADD_POLICY_SUCCESS = "Added policy to Person: %1$s";

    private final Logger logger = LogsCenter.getLogger(AddPolicyCommand.class);

    private final Index index;
    private final String policyName;
    private final String policyNumber;
    private final String premiumTerm;
    private final String premium;
    private final String benefit;
    /**
     * Creates an AddPolicyCommand to add the specified {@code Policy} to the client at the specified index.
     *
     * @param index The index of the client in the filtered person list.
     * @param policyName The name of the policy to be added.
     */
    public AddPolicyCommand(Index index, String policyName,
                            String policyNumber, String premiumTerm, String premium, String benefit) {
        requireAllNonNull(index, policyName, policyNumber, premiumTerm, premium, benefit);
        this.index = index;
        this.policyName = policyName;
        this.policyNumber = policyNumber;
        this.premiumTerm = premiumTerm;
        this.premium = premium;
        this.benefit = benefit;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Set<Policy> currentPolicies = new HashSet<>(personToEdit.getPolicies());
        Policy newPolicy = new Policy(policyName, policyNumber, premiumTerm, premium, benefit);
        currentPolicies.add(newPolicy);

        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getMeeting(), personToEdit.getTags(), currentPolicies);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    private String generateSuccessMessage(Person editedPerson) {
        return String.format(MESSAGE_ADD_POLICY_SUCCESS, editedPerson);
    }
}
