package seedu.address.logic.commands;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.MoneyOwed;
import seedu.address.model.person.Person;

/**
 * Resets the moneyOwed of a Person to 0. This is called by a button click instead of a CLI command.
 */
public class ResetDebtCommand extends Command {
    public static final String PERSON_NOT_FOUND_MESSAGE = "The person with the number %s cannot be"
            + " found in the address book!";
    public static final String RESET_SUCCESS_MESSAGE = "Reset money owed to %s to $0.";
    private final Person originalPerson;

    public ResetDebtCommand(Person originalPerson) {
        this.originalPerson = originalPerson;
    }

    private Person resetPersonDebt(Person person) {
        return new Person(person.getName(), person.getPhone(), person.getEmail(),
                person.getAddress(), person.getRemark(), person.getTags(), person.getBirthday(), new MoneyOwed("0"));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Optional<Person> personMaybe = model.findPerson(
                person -> person.getPhone().equals(originalPerson.getPhone()));
        Person person;
        try {
            person = personMaybe.get();
        } catch (NoSuchElementException e) {
            throw new CommandException(String.format(PERSON_NOT_FOUND_MESSAGE, originalPerson.getPhone()));
        }
        if (person.getMoneyOwed().moneyOwed == 0) {
            return new CommandResult(String.format(RESET_SUCCESS_MESSAGE, person.getName()));
        }
        Person editedPerson = resetPersonDebt(person);
        model.setPerson(person, editedPerson);
        return new CommandResult(String.format(RESET_SUCCESS_MESSAGE, person.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ResetDebtCommand)) {
            return false;
        }
        ResetDebtCommand otherCommand = (ResetDebtCommand) other;
        return Objects.equals(originalPerson, otherCommand.originalPerson);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("originalPerson", originalPerson)
                .toString();
    }
}
