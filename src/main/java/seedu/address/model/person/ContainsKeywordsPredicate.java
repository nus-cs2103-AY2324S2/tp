package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Attribute} matches the corresponding keyword given.
 */
public class ContainsKeywordsPredicate<T> implements Predicate<Person> {

    private final Prefix prefix;
    private final Optional<T> keywords;

    /**
     * @param prefix of the corresponding field.
     * @param keywords that the test is run against.
     */
    public ContainsKeywordsPredicate(Prefix prefix, Optional<T> keywords) {
        this.prefix = prefix;
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (prefix.equals(PREFIX_NAME)) {
            Optional<Name> name = (Optional<Name>) keywords;
            return name.isEmpty() || person.getName().fullName.contains(name.get().fullName);
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
            return isPersonTagsContainsTag(person, tags);
        }
        throw new IllegalStateException("Unexpected value: " + prefix);
    }

    private boolean isPersonTagsContainsTag(Person person, Optional<Set<Tag>> tags) {
        assert tags.isPresent();
        boolean isContainTag = false;
        for (Tag tag : tags.get()) {
            for (Tag personTag : person.getTags()) {
                if (personTag.tagName.contains(tag.tagName)) {
                    isContainTag = true;
                    break;
                }
            }
        }
        return isContainTag;
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
