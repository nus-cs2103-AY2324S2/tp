package seedu.address.testutil;

import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.EmailMatchesPredicate;
import seedu.address.model.person.GroupMatchesPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PhoneMatchesPredicate;
import seedu.address.model.person.TagMatchesPredicate;

/**
 * A class that aids in creating {@code FindCommand} instances for testing
 * The default {@code FindCommand} created results in no filtering
 */
public class FindCommandBuilder {
    public static final NameContainsKeywordsPredicate DEFAULT_NAME_PRED =
            new NameContainsKeywordsPredicate(List.of(FindCommand.NOT_REQUIRED_VALUE));
    public static final EmailMatchesPredicate DEFAULT_EMAIL_PRED =
            new EmailMatchesPredicate(FindCommand.NOT_REQUIRED_VALUE);
    public static final GroupMatchesPredicate DEFAULT_GROUPS_PRED =
            new GroupMatchesPredicate(List.of());
    public static final PhoneMatchesPredicate DEFAULT_PHONE_PRED =
            new PhoneMatchesPredicate(FindCommand.NOT_REQUIRED_VALUE);
    public static final TagMatchesPredicate DEFAULT_TAG_PRED =
            new TagMatchesPredicate(FindCommand.NOT_REQUIRED_VALUE);

    private NameContainsKeywordsPredicate n;
    private EmailMatchesPredicate e;
    private GroupMatchesPredicate g;
    private PhoneMatchesPredicate p;
    private TagMatchesPredicate t;

    /**
     * Constructs a Utility class to help build a FindCommand.
     * All fields are initially set to be optional.
     */
    public FindCommandBuilder() {
        n = DEFAULT_NAME_PRED;
        e = DEFAULT_EMAIL_PRED;
        g = DEFAULT_GROUPS_PRED;
        p = DEFAULT_PHONE_PRED;
        t = DEFAULT_TAG_PRED;
    }

    /**
     * Changes the {@code FindCommand}'s NameMatchesPredicate we are building.
     */
    public FindCommandBuilder withNamePred(NameContainsKeywordsPredicate namePred) {
        n = namePred;
        return this;
    }

    /**
     * Changes the {@code FindCommand's} {@code EmailMatchesPredicate} we are building.
     */
    public FindCommandBuilder withEmail(EmailMatchesPredicate emailPred) {
        e = emailPred;
        return this;
    }

    /**
     * Changes the {@code FindCommand's} {@code GroupMatchesPredicate} we are building.
     */
    public FindCommandBuilder withGroups(GroupMatchesPredicate groupsPred) {
        g = groupsPred;
        return this;
    }

    /**
     * Changes the {@code FindCommand's} {@code PhoneMatchesPredicate} we are building.
     */
    public FindCommandBuilder withPhone(PhoneMatchesPredicate phonePred) {
        p = phonePred;
        return this;
    }

    /**
     * Changes the {@code FindCommand's} {@code TagMatchesPredicate} we are building.
     */
    public FindCommandBuilder withTag(TagMatchesPredicate tagPred) {
        t = tagPred;
        return this;
    }

    /**
     * Build the {@code FindCommand} we have customised.
     */
    public FindCommand build() {
        return new FindCommand(n, e, g, p, t);
    }
}
