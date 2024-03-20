package educonnect.model.student.predicates;

import java.util.function.Predicate;

import educonnect.commons.util.StringUtil;
import educonnect.commons.util.ToStringBuilder;
import educonnect.model.student.Student;

/**
 * Tests that a {@code Student}'s {@code Email} matches any of the keywords given.
 */
public class TelegramContainsKeywordsPredicate implements Predicate<Student> {
    private final String keywordTelegram;

    public TelegramContainsKeywordsPredicate(String keywordTelegram) {
        this.keywordTelegram = keywordTelegram; //replace
    }

    @Override
    public boolean test(Student student) {
        return StringUtil.fuzzyMatchIgnoreCase(student.getTelegramHandle().value, keywordTelegram);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TelegramContainsKeywordsPredicate)) {
            return false;
        }

        TelegramContainsKeywordsPredicate otherTelegramContainsKeywordsPredicate =
                (TelegramContainsKeywordsPredicate) other;
        return keywordTelegram.equals(otherTelegramContainsKeywordsPredicate.keywordTelegram);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywordTelegram).toString();
    }
}
