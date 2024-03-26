package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.student.Bolt;
import seedu.address.model.student.Email;
import seedu.address.model.student.Major;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Star;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_MAJOR = "Business";

    private Name name;
    private Phone phone;
    private Email email;
    private Major major;
    private Star star;
    private Bolt bolt;
    private Set<Tag> tags;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        major = new Major(DEFAULT_MAJOR);
        star = Star.NO_STAR; // default value from Star
        bolt = Bolt.NO_BOLT; // default value from Bolt
        tags = new HashSet<>();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        major = studentToCopy.getMajor();
        star = studentToCopy.getStar();
        bolt = studentToCopy.getBolt();
        tags = new HashSet<>(studentToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Major} of the {@code Student} that we are building.
     */
    public StudentBuilder withMajor(String major) {
        this.major = new Major(major);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Star} of the {@code Student} that we are building.
     */
    public StudentBuilder withStar(Integer star) {
        this.star = new Star(star);
        return this;
    }

    /**
     * Sets the {@code Bolt} of the {@code Student} that we are building.
     */
    public StudentBuilder withBolt(Integer bolt) {
        this.bolt = new Bolt(bolt);
        return this;
    }

    /**
     * Builds a new Student.
     * @return A new Student built from the values given.
     */
    public Student build() {
        return new Student(name, phone, email, major, star, bolt, tags);
        // we do not include star here because our constructor creates a default value of new Star(0) and new Bolt(0)
    }

}
