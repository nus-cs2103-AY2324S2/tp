package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FUNDING_STAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditStartupDescriptor;
import seedu.address.model.startup.Startup;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Startup.
 */
public class StartupUtil {

    /**
     * Returns an add command string for adding the {@code startup}.
     */
    public static String getAddCommand(Startup startup) {
        return AddCommand.COMMAND_WORD + " " + getStartupDetails(startup);
    }

    /**
     * Returns the part of command string for the given {@code startup}'s details.
     */
    public static String getStartupDetails(Startup startup) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + startup.getName().fullName + " ");
        sb.append(PREFIX_FUNDING_STAGE + startup.getFundingStage().value + " ");
        sb.append(PREFIX_INDUSTRY + startup.getIndustry().value + " ");
        sb.append(PREFIX_PHONE + startup.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + startup.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + startup.getAddress().value + " ");
        startup.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStartupDescriptor}'s details.
     */
    public static String getEditStartupDescriptorDetails(EditStartupDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getIndustry().ifPresent(industry -> sb.append(PREFIX_INDUSTRY).append(industry.value).append(" "));
        descriptor.getFundingStage().ifPresent(fundingStage ->
            sb.append(PREFIX_FUNDING_STAGE).append(fundingStage.value).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
