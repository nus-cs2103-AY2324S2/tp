package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Marks the tutorial attendance of a student
 */

public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the attendance of the person identified "
            + "by the index number used in the last person listing.\n"
            + "Parameters: INDEX (must be a positive integer), WEEK (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 " + "5";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Week Number: %2$d";
    public static final String MESSAGE_MARK_PERSON_SUCCESS = "Marked Person: %1$s";
    private final Index targetIndex;
    private final Index weekNumber;

    /**
     * @param index of the person in the filtered person list to edit
     * @param weekNumber week number to mark the person with
     */
    public MarkCommand(Index index, Index weekNumber) {
        requireAllNonNull(index, weekNumber);

        this.targetIndex = index;
        this.weekNumber = weekNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        if (weekNumber.getZeroBased() > 13) {
            throw new CommandException(Messages.MESSAGE_INVALID_WEEK);
        }

        Person personToMark = lastShownList.get(targetIndex.getZeroBased());
        List<Integer> oldParticipationScores = personToMark.getParticipationScores();
        List<Integer> newParticipationScores = new ArrayList<>();
        int weekIndex = weekNumber.getZeroBased() - 1;

        for (int i = 0; i < oldParticipationScores.size(); i++) {
            if (i == weekIndex) {
                newParticipationScores.add(1);
            } else {
                newParticipationScores.add(oldParticipationScores.get(i));
            }
        }

        Person updatedPerson = createMarkedPerson(personToMark, newParticipationScores);

        model.setPerson(personToMark, updatedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_MARK_PERSON_SUCCESS, Messages.format(personToMark)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToMark}
     * with the updated participation scores. Utilizes the overload Person constructor.
     */
    private static Person createMarkedPerson(Person personToMark, List<Integer> updatedParticipationScores) {
        assert personToMark != null;

        Name name = personToMark.getName();
        Phone phone = personToMark.getPhone();
        Email email = personToMark.getEmail();
        Address address = personToMark.getAddress();
        Set<Tag> tags = personToMark.getTags();

        return new Person(name, phone, email, address, tags, updatedParticipationScores);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkCommand)) {
            return false;
        }

        // state check
        MarkCommand e = (MarkCommand) other;
        return targetIndex.equals(e.targetIndex)
                && weekNumber.equals(e.weekNumber);
    }

}
