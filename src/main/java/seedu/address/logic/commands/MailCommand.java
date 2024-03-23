package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import seedu.address.model.Model;
import seedu.address.model.group.GroupContainsKeywordsPredicate;
import seedu.address.model.person.Person;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Directs users to the HTML website with email links to all the students in the current list.
 */
public class MailCommand extends Command {

    public static final String COMMAND_WORD = "mail";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": generates mailto link to students from "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " LAB10 TUT04";

    private final GroupContainsKeywordsPredicate predicate;

    public MailCommand(GroupContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        // Extract email addresses of filtered students
        List<String> emailList = model.getFilteredPersonList().stream()
                .map(Person::getEmail)
                .filter(email -> !email.value.isEmpty())
                .map(email -> email.value)
                .collect(Collectors.toList());

        // Generate the mailto link
        String mailtoLink = "mailto:" + String.join(";", emailList);

        return new CommandResult(mailtoLink);
    }
}
