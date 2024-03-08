package seedu.address.model.person;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

import java.util.Objects;
import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Maintainer in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Maintainer extends Person{
    // Data fields
    private final Skill skill;
    private final Commission commission;

    /**
     * Every field must be present and not null.
     */
    public Maintainer(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                      Skill skill, Commission commission) {
        super(name, phone, email, address, tags);
        requireAllNonNull(skill, commission);
        this.skill = skill;
        this.commission = commission;
    }

    public Skill getSkill() {
        return skill;
    }

    public Commission getCommission() {
        return commission;
    }

    /**
     * Returns true if both Maintainer have the same identity and data fields.
     * This defines a stronger notion of equality between two maintainer.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Maintainer)) {
            return false;
        }

        Maintainer otherPerson = (Maintainer) other;
        return super.equals(otherPerson)
                && skill.equals(otherPerson.skill)
                && commission.equals(otherPerson.commission);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(super.hashCode(), skill, commission);
    }

    @Override
    public String toString() {
        return super.toString() + new ToStringBuilder(this)
                .add("skill", skill)
                .add("commission", commission)
                .toString();
    }
}
