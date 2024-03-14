package seedu.address.model.applicant;

import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
/**
 * Represents a Applicant in the address book.
 * Guarantees: roles and stage are present and not null, field values are validated, immutable.
 */
public class Applicant extends Person {

    private final Role role;
    private final Stage stage;


    /**
     * Every field must be present and not null.
     */
    public Applicant(Name name, Phone phone,
                     Email email, Address address, Role role, Stage stage,
                     Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.role = role;
        this.stage = stage;
    }

    /**
     *  For quick initialization of applicant with existing person
     */
    public Applicant(Person p, Role role, Stage stage) {
        super(p.getName(), p.getPhone(), p.getEmail(), p.getAddress(), p.getTags());
        this.role = role;
        this.stage = stage;
    }

    public Role getRole() {
        return role;
    }

    public Stage getStage() {
        return stage;
    }
}
