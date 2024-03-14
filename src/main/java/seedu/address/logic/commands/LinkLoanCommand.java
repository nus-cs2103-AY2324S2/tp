package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RETURN_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VALUE;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Links a loan to a person in the address book.
 */
public class LinkLoanCommand extends Command {

    public static final String COMMAND_WORD = "link";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Links a loan to the person identified "
            + "by the index number used in the displayed person list. "
            + "Parameters: "
            + "INDEX "
            + PREFIX_VALUE + "VALUE "
            + PREFIX_START_DATE + "START_DATE "
            + PREFIX_RETURN_DATE + "RETURN_DATE\n"
            + "Example: " + COMMAND_WORD + " "
            + "5 "
            + PREFIX_VALUE + "500.00 "
            + PREFIX_START_DATE + "15-02-2024 "
            + PREFIX_RETURN_DATE + "21-04-2024";

    public static final String MESSAGE_SUCCESS = "New loan linked: %1$s";

    private final LinkLoanDescriptor toLink;

    private final Index linkTarget;

    /**
     * @param loanDescription of the loan to be linked
     * @param index of the person in the filtered person list to link the loan to
     */
    public LinkLoanCommand(LinkLoanDescriptor loanDescription, Index index) {
        requireAllNonNull(loanDescription, index);
        requireAllNonNull(loanDescription.getValue(), loanDescription.getStartDate(),
                loanDescription.getReturnDate());
        toLink = loanDescription;
        linkTarget = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (linkTarget.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person targetPerson = lastShownList.get(linkTarget.getZeroBased());

        targetPerson.linkLoan(toLink);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(targetPerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LinkLoanCommand)) {
            return false;
        }

        LinkLoanCommand otherLinkLoanCommand = (LinkLoanCommand) other;
        return toLink.equals(otherLinkLoanCommand.toLink)
                && linkTarget.equals(otherLinkLoanCommand.linkTarget);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("linkTarget", linkTarget)
                .add("loanDescription", toLink)
                .toString();
    }

    /**
     * Stores the details of the loan to be linked.
     */
    public static class LinkLoanDescriptor {
        private float value;
        private Date startDate;
        private Date returnDate;

        /**
         * Creates a instance of this LinkLoanDescriptor
         * @param value The value of the loan to be linked
         * @param startDate The start date of the loan
         * @param returnDate The date which the loan must be returned by
         */
        public LinkLoanDescriptor(float value, Date startDate, Date returnDate) {
            this.value = value;
            this.startDate = startDate;
            this.returnDate = returnDate;
        }

        /**
         * Copy constructor.
         */
        public LinkLoanDescriptor(LinkLoanCommand.LinkLoanDescriptor toCopy) {
            setValue(toCopy.value);
            setStartDate(toCopy.startDate);
            setReturnDate(toCopy.returnDate);
        }

        public void setValue(float value) {
            this.value = value;
        }

        public float getValue() {
            return value;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setReturnDate(Date returnDate) {
            this.returnDate = returnDate;
        }

        public Date getReturnDate() {
            return returnDate;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof LinkLoanDescriptor)) {
                return false;
            }

            LinkLoanDescriptor otherLinkLoanDescriptor = (LinkLoanDescriptor) other;
            return (value == otherLinkLoanDescriptor.value)
                    && Objects.equals(startDate, otherLinkLoanDescriptor.startDate)
                    && Objects.equals(returnDate, otherLinkLoanDescriptor.returnDate);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("value", value)
                    .add("startDate", startDate)
                    .add("returnDate", returnDate)
                    .toString();
        }
    }
}
