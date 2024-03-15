package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Email;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.Phone;
import seedu.address.model.skill.Skill;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building CourseMate objects.
 */
public class CourseMateBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";

    private Name name;
    private Phone phone;
    private Email email;
    private Set<Skill> skills;

    /**
     * Creates a {@code CourseMateBuilder} with the default details.
     */
    public CourseMateBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        skills = new HashSet<>();
    }

    /**
     * Initializes the CourseMateBuilder with the data of {@code courseMateToCopy}.
     */
    public CourseMateBuilder(CourseMate courseMateToCopy) {
        name = courseMateToCopy.getName();
        phone = courseMateToCopy.getPhone();
        email = courseMateToCopy.getEmail();
        skills = new HashSet<>(courseMateToCopy.getSkills());
    }

    /**
     * Sets the {@code Name} of the {@code CourseMate} that we are building.
     */
    public CourseMateBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code skills} into a {@code Set<Skill>} and set it to the {@code CourseMate} that we are building.
     */
    public CourseMateBuilder withSkills(String ... skills) {
        this.skills = SampleDataUtil.getSkillSet(skills);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code CourseMate} that we are building.
     */
    public CourseMateBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code CourseMate} that we are building.
     */
    public CourseMateBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public CourseMate build() {
        return new CourseMate(name, phone, email, skills);
    }

}
