package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.TutorialClass;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_STUDENT_ID = "A1111111Z";
    public static final String DEFAULT_MODULE = "CS1010S";
    public static final String DEFAULT_TUTORIAL = "T01";

    private Name name;
    private Email email;
    private StudentId studentId;
    private ModuleCode moduleCode;
    private TutorialClass tutorialClass;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        email = new Email(DEFAULT_EMAIL);
        studentId = new StudentId(DEFAULT_STUDENT_ID);
        moduleCode = new ModuleCode(DEFAULT_MODULE);
        tutorialClass = new TutorialClass(DEFAULT_TUTORIAL);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        email = personToCopy.getEmail();
        studentId = personToCopy.getStudentId();
        moduleCode = personToCopy.getModule();
        tutorialClass = personToCopy.getTutorialClass();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code StudentId} of the {@code Person} that we are building.
     */
    public PersonBuilder withStudentId(String studentId) {
        this.studentId = new StudentId(studentId);
        return this;
    }

    /**
     * Sets the {@code ModuleCode} of the {@code Person} that we are building.
     */
    public PersonBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    /**
     * Sets the {@code TutorialClass} of the {@code Person} that we are building.
     */
    public PersonBuilder withTutorialClass(String tutorialClass) {
        this.tutorialClass = new TutorialClass(tutorialClass);
        return this;
    }

    public Person build() {
        return new Person(name, email, studentId, moduleCode, tutorialClass, tags);
    }

}
