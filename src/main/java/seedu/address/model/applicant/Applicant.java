package seedu.address.model.applicant;

import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represents an Applicant.
 */
public class Applicant extends Person {

    private final Role role;
    private final Stage stage;

    /**
     * Every field must be present and not null.
     */
    public Applicant(Name name, Phone phone, Email email, Address address, Role role, Stage stage, Set<Tag> tags,
                     Note note, String noteDate) {
        super(name, phone, email, address, tags, note, noteDate);
        this.role = role;
        this.stage = stage;

    }

    /**
     *  For quick initialization of applicant with existing person
     */
    public Applicant(Person p, Role role, Stage stage) {
        super(p.getName(), p.getPhone(), p.getEmail(), p.getAddress(), p.getTags(), p.getNote(), p.getNoteDate());
        this.role = role;
        this.stage = stage;
    }

    public Role getRole() {
        return role;
    }

    public Stage getStage() {
        return stage;
    }

    /**
     * Returns true if both applicants have the same identity and data fields.
     * This defines a stronger notion of equality between two applicants.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.model.applicant.Applicant)) {
            return false;
        }

        Applicant otherApplicant = (Applicant) other;
        return super.equals(otherApplicant)
            && role.equals(otherApplicant.role)
            && stage.equals(otherApplicant.stage);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two applicants.
     */
    public boolean isSameApplicant(Applicant otherApplicant) {
        if (otherApplicant == this) {
            return true;
        }

        return otherApplicant != null
                && otherApplicant.getName().equals(getName())
                && otherApplicant.getPhone().equals(getPhone())
                && otherApplicant.getEmail().equals(getEmail())
                && otherApplicant.getAddress().equals(getAddress())
                && otherApplicant.getRole().equals(getRole())
                && otherApplicant.getStage().equals(getStage())
                && otherApplicant.getTags().equals(getTags())
                && otherApplicant.getNote().equals(getNote());
    }

}
