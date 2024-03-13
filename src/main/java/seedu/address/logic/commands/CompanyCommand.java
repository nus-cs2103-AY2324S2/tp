package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.Messages;

import seedu.address.model.Model;
import seedu.address.model.person.Company;
import seedu.address.model.person.NameEqualsKeywordPredicate;
import seedu.address.model.person.Person;


/**
 * Changes the remark of an existing person in the address book.
 */
public class CompanyCommand extends Command {

    public static final String COMMAND_WORD = "company";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a company to the person identified by the contact name "
            + "Existing company will be overwritten by the input.\n"
            + "g/ [COMPANY_NAME]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "g/ Friends";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$s, Remark: %2$s";
    public static final String MESSAGE_ADD_COMPANY_SUCCESS = "Tagged %1$s's company as %2$s";
    public static final String MESSAGE_DELETE_COMPANY_SUCCESS = "Removed the company tag from %1$s's contact";

    private final String name;
    private final Company company;

    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "Company command not implemented yet";

    /**
     * @param name  of the person in the filtered person list to edit the remark
     * @param company of the person to be updated to
     */
    public CompanyCommand(String name, Company company) {
        requireAllNonNull(name, company);

        this.name = name;
        this.company = company;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(new NameEqualsKeywordPredicate(name));
        List<Person> lastShownList = model.getFilteredPersonList();

        if (lastShownList.size() <= 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(0);
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), company, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !company.value.isEmpty() ? MESSAGE_ADD_COMPANY_SUCCESS : MESSAGE_DELETE_COMPANY_SUCCESS;
        return String.format(message, personToEdit.getName(), company);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CompanyCommand)) {
            return false;
        }

        CompanyCommand e = (CompanyCommand) other;
        return name.equals(e.name)
                && company.equals(e.company);

    }

}
