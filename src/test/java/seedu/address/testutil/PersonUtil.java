package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEWTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROGRAMMING_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.time.format.DateTimeFormatter;
import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.language.ProgrammingLanguage;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;


/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        DateTimeFormatter reformat = DateTimeFormatter.ofPattern("ddMMyyyyHHmm");
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_COMPANY_NAME + person.getCompanyName().companyName + " ");
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        sb.append(PREFIX_INTERVIEWTIME + person.getDateTime().dateTime.format(reformat) + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        person.getProgrammingLanguages().stream().forEach(
                pl -> sb.append(PREFIX_PROGRAMMING_LANGUAGE + pl.languageName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        DateTimeFormatter reformat = DateTimeFormatter.ofPattern("ddMMyyyyHHmm");
        StringBuilder sb = new StringBuilder();
        descriptor.getCompanyName().ifPresent(companyName -> sb.append(PREFIX_COMPANY_NAME)
                .append(companyName.companyName).append(" "));
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getPriority().ifPresent(Integer -> sb.append(PREFIX_PRIORITY).append(Integer).append(" "));
        descriptor.getInfo().ifPresent(info -> sb.append(PREFIX_INFO).append(info.value).append(" "));
        descriptor.getSalary().ifPresent(salary -> sb.append(PREFIX_SALARY).append(salary.toString()).append(" "));
        descriptor.getDateTime().ifPresent(dateTime ->
                sb.append(PREFIX_INTERVIEWTIME).append(dateTime.dateTime.format(reformat)).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        sb.append(" ");
        if (descriptor.getProgrammingLanguages().isPresent()) {
            Set<ProgrammingLanguage> programmingLanguages = descriptor.getProgrammingLanguages().get();
            if (programmingLanguages.isEmpty()) {
                sb.append(PREFIX_PROGRAMMING_LANGUAGE);
            } else {
                programmingLanguages.forEach(s -> sb.append(PREFIX_PROGRAMMING_LANGUAGE)
                        .append(s.languageName).append(" "));
            }
        }
        return sb.toString();
    }
}
