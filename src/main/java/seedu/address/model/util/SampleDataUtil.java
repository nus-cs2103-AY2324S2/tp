package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ContactList;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Email;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.Phone;
import seedu.address.model.skill.Skill;

/**
 * Contains utility methods for populating {@code ContactList} with sample data.
 */
public class SampleDataUtil {
    public static CourseMate[] getSampleCourseMates() {
        return new CourseMate[] {
            new CourseMate(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                getSkillSet("friends")),
            new CourseMate(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                getSkillSet("colleagues", "friends")),
            new CourseMate(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                getSkillSet("neighbours")),
            new CourseMate(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                getSkillSet("family")),
            new CourseMate(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                getSkillSet("classmates")),
            new CourseMate(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                getSkillSet("colleagues"))
        };
    }

    public static ReadOnlyContactList getSampleContactList() {
        ContactList sampleAb = new ContactList();
        for (CourseMate sampleCourseMate : getSampleCourseMates()) {
            sampleAb.addCourseMate(sampleCourseMate);
        }
        return sampleAb;
    }

    /**
     * Returns a skill set containing the list of strings given.
     */
    public static Set<Skill> getSkillSet(String... strings) {
        return Arrays.stream(strings)
                .map(Skill::new)
                .collect(Collectors.toSet());
    }

}
