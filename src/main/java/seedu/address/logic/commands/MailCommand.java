package seedu.address.logic.commands;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Directs users to the HTML website with email links to all the students in the current list.
 */
public class MailCommand extends Command {

    public static final String COMMAND_WORD = "mail";

    @Override
    public CommandResult execute(Model model) {
        List<Person> personList = model.getFilteredPersonList();

        // Extract email addresses of all students
        List<String> emailList = personList.stream()
                .map(Person::getEmail)
                .filter(email -> !email.value.isEmpty())
                .map(email -> email.value)
                .collect(Collectors.toList());

        // Generate the mailto link
        String mailtoLink = "mailto:" + String.join(";", emailList);

        return new CommandResult(mailtoLink);
    }
}
