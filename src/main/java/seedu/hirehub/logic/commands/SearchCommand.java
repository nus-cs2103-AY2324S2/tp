package seedu.hirehub.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_COUNTRY;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.hirehub.commons.util.ToStringBuilder;
import seedu.hirehub.logic.Messages;
import seedu.hirehub.model.Model;
import seedu.hirehub.model.person.Comment;
import seedu.hirehub.model.person.ContainsKeywordsPredicate;
import seedu.hirehub.model.person.Country;
import seedu.hirehub.model.person.Email;
import seedu.hirehub.model.person.Name;
import seedu.hirehub.model.person.Phone;
import seedu.hirehub.model.person.SearchPredicate;
import seedu.hirehub.model.tag.Tag;

/**
 * Searches all persons whose attributes match the specified attributes.
 */
public class SearchCommand extends Command {
    public static final String COMMAND_WORD = "search";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose attributes match all "
            + "the corresponding specified attributes.\n"
            + "For phone number, email, country and status, only full words will be matched,\n"
            + "while for name, comment and tags, partial words will be matched.\n"
            + "Parameters: [" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_COUNTRY + "COUNTRY] "
            + "[" + PREFIX_COMMENT + "COMMENT] "
            + "[" + PREFIX_TAG + "TAG]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_NO_FIELD_PROVIDED = "At least one field to search for must be provided.";

    private final SearchPersonDescriptor searchPersonDescriptor;

    public SearchCommand(SearchPersonDescriptor searchPersonDescriptor) {
        this.searchPersonDescriptor = searchPersonDescriptor;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(searchPersonDescriptor.getPredicate());
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.hirehub.logic.commands.SearchCommand)) {
            return false;
        }

        SearchCommand otherSearchCommand = (SearchCommand) other;
        return searchPersonDescriptor.equals(otherSearchCommand.searchPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("searchPersonDescriptor", searchPersonDescriptor.toString())
                .toString();
    }

    /**
     * Stores the details to search the person with. Each non-empty field value will search the
     * corresponding field value of the person.
     */
    public static class SearchPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Country country;
        private Comment comment;
        private Set<Tag> tags;

        public SearchPersonDescriptor() {}

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setCountry(Country country) {
            this.country = country;
        }

        public Optional<Country> getCountry() {
            return Optional.ofNullable(country);
        }

        public void setComment(Comment comment) {
            this.comment = comment;
        }

        public Optional<Comment> getComment() {
            return Optional.ofNullable(comment);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        public SearchPredicate getPredicate() {
            ArrayList<ContainsKeywordsPredicate> predicateList = new ArrayList<>();
            ContainsKeywordsPredicate<Name> nameSearch =
                    new ContainsKeywordsPredicate<>(PREFIX_NAME, this.getName());
            predicateList.add(nameSearch);
            ContainsKeywordsPredicate<Phone> phoneSearch =
                    new ContainsKeywordsPredicate<>(PREFIX_PHONE, this.getPhone());
            predicateList.add(phoneSearch);
            ContainsKeywordsPredicate<Email> emailSearch =
                    new ContainsKeywordsPredicate<>(PREFIX_EMAIL, this.getEmail());
            predicateList.add(emailSearch);
            ContainsKeywordsPredicate<Country> countrySearch =
                    new ContainsKeywordsPredicate<>(PREFIX_COUNTRY, this.getCountry());
            predicateList.add(countrySearch);
            ContainsKeywordsPredicate<Comment> commentSearch =
                    new ContainsKeywordsPredicate<>(PREFIX_COMMENT, this.getComment());
            predicateList.add(commentSearch);
            ContainsKeywordsPredicate<Set<Tag>> tagSearch =
                    new ContainsKeywordsPredicate<>(PREFIX_TAG, this.getTags());
            predicateList.add(tagSearch);
            return new SearchPredicate(predicateList);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof SearchCommand.SearchPersonDescriptor)) {
                return false;
            }

            SearchCommand.SearchPersonDescriptor otherSearchPersonDescriptor =
                    (SearchCommand.SearchPersonDescriptor) other;
            return Objects.equals(name, otherSearchPersonDescriptor.name)
                    && Objects.equals(phone, otherSearchPersonDescriptor.phone)
                    && Objects.equals(email, otherSearchPersonDescriptor.email)
                    && Objects.equals(country, otherSearchPersonDescriptor.country)
                    && Objects.equals(comment, otherSearchPersonDescriptor.comment)
                    && Objects.equals(tags, otherSearchPersonDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("country", country)
                    .add("comment", comment)
                    .add("tags", tags)
                    .toString();
        }
    }
}
