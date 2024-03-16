package seedu.address.model.person;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.tag.Tag;

import javax.swing.text.html.Option;

import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Tests that all a {@code Person}'s {@code Attributes} matches the corresponding keywords given.
 */
public class ContainsKeywordsPredicate<T> implements Predicate<Person> {

    private final Prefix prefix;
    private final Optional<T> keywords;

    public ContainsKeywordsPredicate(Prefix prefix, Optional<T> keywords) {
        this.prefix = prefix;
        this.keywords = keywords;
    }


    @Override
    public boolean test(Person person) {
        if (prefix.equals(PREFIX_NAME)) {
            Optional<Name> name = (Optional<Name>) keywords;
            return person.getName().fullName.contains(name.orElse(new Name("")).fullName);
        } else if (prefix.equals(PREFIX_PHONE)) {
            Optional<Phone> phone = (Optional<Phone>) keywords;
            return person.getPhone().value.equals(phone.orElse(person.getPhone()).value);
        } else if (prefix.equals(PREFIX_EMAIL)) {
            Optional<Email> email = (Optional<Email>) keywords;
            return person.getEmail().value.equals(email.orElse(person.getEmail()).value);
        } else if (prefix.equals(PREFIX_ADDRESS)) {
            Optional<Address> address = (Optional<Address>) keywords;
            return person.getAddress().value.equals(address.orElse(person.getAddress()).value);
        } else if (prefix.equals(PREFIX_COMMENT)) {
            Optional<Comment> comment = (Optional<Comment>) keywords;
            return person.getComment().value.contains(comment.orElse(new Comment("")).value);
        } else if (prefix.equals(PREFIX_TAG)) {
            Optional<Set<Tag>> tags = (Optional<Set<Tag>>) keywords;
            if (tags.isEmpty()) {
                return true;
            }
            boolean isContainTag = false;
            for (Tag tag : tags.get()) {
                if (person.getTags().contains(tag)) {
                    isContainTag = true;
                    break;
                }
            }
            return isContainTag;
        }
        throw new IllegalStateException("Unexpected value: " + prefix);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContainsKeywordsPredicate)) {
            return false;
        }

        ContainsKeywordsPredicate<T> otherContainsKeywordsPredicate = (ContainsKeywordsPredicate<T>) other;
        return keywords.equals(otherContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
