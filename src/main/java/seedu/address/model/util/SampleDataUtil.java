package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ContactList;
import seedu.address.model.GroupList;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyGroupList;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Email;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.Phone;
import seedu.address.model.group.Group;
import seedu.address.model.skill.Skill;

/**
 * Contains utility methods for populating {@code ContactList} with sample data.
 */
public class SampleDataUtil {
    public static CourseMate[] getSampleCourseMates() {
        return new CourseMate[] {
            new CourseMate(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                getSkillSet("C++")),
            new CourseMate(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                getSkillSet("Java", "C++")),
            new CourseMate(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                getSkillSet("Python")),
            new CourseMate(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                getSkillSet("C")),
            new CourseMate(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                getSkillSet("Python", "C++")),
            new CourseMate(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                getSkillSet("Java"))
        };
    }

    public static Group[] getSampleGroups() {
        return new Group[] {
            new Group(new Name("CS2103T"),
                new HashSet<>(Arrays.asList(
                    new CourseMate(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                        getSkillSet("C++")),
                    new CourseMate(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        getSkillSet("Java", "C++")),
                    new CourseMate(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                        getSkillSet("C")),
                    new CourseMate(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                        getSkillSet("Python", "C++"))
                    )),
                new HashSet<>(Arrays.asList(new Skill("C++")))),
            new Group(new Name("CS2101"),
                new HashSet<>(Arrays.asList(
                    new CourseMate(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                        getSkillSet("C++")),
                    new CourseMate(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        getSkillSet("Java", "C++")),
                    new CourseMate(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                        getSkillSet("C")),
                    new CourseMate(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                        getSkillSet("Python", "C++"))
                    )))
        };
    }

    public static ReadOnlyContactList getSampleContactList() {
        ContactList sampleAb = new ContactList();
        for (CourseMate sampleCourseMate : getSampleCourseMates()) {
            sampleAb.addCourseMate(sampleCourseMate);
        }
        return sampleAb;
    }

    public static ReadOnlyGroupList getSampleGroupList() {
        GroupList sample = new GroupList();
        for (Group sampleGroup : getSampleGroups()) {
            sample.addGroup(sampleGroup);
        }
        return sample;
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
