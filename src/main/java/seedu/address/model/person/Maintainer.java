package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Maintainer in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Maintainer extends Person {
    // Data fields
    private final Skill skill;
    private final Commission commission;

    /**
     * Every field must be present and not null.
     */
    public Maintainer(Name name, Phone phone, Email email, Address address, Note note, Set<Tag> tags,
                      Skill skill, Commission commission, Rating rating) {
        super(name, phone, email, address, note, tags, rating);
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
     * Returns a new instantiation of the current {@code Maintainer} with the updated note,
     * which throws {@code UnsupportedOperationException} if modification is attempted.
     */
    @Override
    public Maintainer updateNote(Note note) {
        Maintainer maintainerToReturn = new Maintainer(this.getName(), this.getPhone(), this.getEmail(),
                this.getAddress(), note, this.getTags(), this.skill, this.commission, this.getRating());
        maintainerToReturn.setPinIfPinned(this);
        return maintainerToReturn;
    }

    /**
     * Returns a new instantiation of the current {@code Maintainer} with the updated rating,
     * which throws {@code UnsupportedOperationException} if modification is attempted.
     */
    @Override
    public Maintainer updateRating(Rating rating) {
        Maintainer maintainerToReturn = new Maintainer(this.getName(), this.getPhone(), this.getEmail(),
                this.getAddress(), this.getNote(), this.getTags(), this.skill, this.commission, rating);
        maintainerToReturn.setPinIfPinned(this);
        return maintainerToReturn;
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
        return new ToStringBuilder(this)
                .add("name", getName())
                .add("phone", getPhone())
                .add("email", getEmail())
                .add("address", getAddress())
                .add("tags", getTags())
                .add("rating", getRating())
                .add("skill", skill)
                .add("commission", commission)
                .toString();
    }
}
