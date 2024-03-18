package seedu.address.logic.commands.delete;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

public class DeleteCommandExecutor {

    public static Person deleteByIndex(List<Person> lastShownList, Index targetIndex) throws CommandException {
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        return lastShownList.get(targetIndex.getZeroBased());
    }

    public static Person deleteByName(List<Person> lastShownList, Name targetName) throws CommandException {
        for (Person person : lastShownList) {
            if (person.getName().equals(targetName)) {
                return person;
            }
        }
        throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
    }
}
