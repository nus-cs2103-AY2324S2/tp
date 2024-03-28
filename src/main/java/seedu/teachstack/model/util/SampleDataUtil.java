package seedu.teachstack.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.teachstack.model.AddressBook;
import seedu.teachstack.model.ReadOnlyAddressBook;
import seedu.teachstack.model.group.Group;
import seedu.teachstack.model.person.Email;
import seedu.teachstack.model.person.Grade;
import seedu.teachstack.model.person.Name;
import seedu.teachstack.model.person.Person;
import seedu.teachstack.model.person.StudentId;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {

            new Person(new Name("Alex Yeoh"), new StudentId("A0123456X"),
                    new Email("e0987654@u.nus.edu"), new Grade("A+"), getGroupSet("Group 3")),
            new Person(new Name("Bernice Yu"), new StudentId("A0123456H"),
                    new Email("e0876543@u.nus.edu"), new Grade("A"), getGroupSet("Group 2", "Group 2B")),
            new Person(new Name("Charlotte Oliveiro"), new StudentId("A0123456U"),
                    new Email("e0765432@u.nus.edu"), new Grade("A-"), getGroupSet("Group30")),
            new Person(new Name("David Li"), new StudentId("A0123456A"),
                    new Email("e0654321@u.nus.edu"), new Grade("B+"), getGroupSet("Group 4")),
            new Person(new Name("Irfan Ibrahim"), new StudentId("A0123457X"),
                    new Email("e1098765@u.nus.edu"), new Grade("B"), getGroupSet("Group 20")),
            new Person(new Name("Roy Balakrishnan"), new StudentId("A0123458X"),
                    new Email("e1087654@u.nus.edu"), new Grade("C"), getGroupSet("Group 30"))

        };
    }


    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a group set containing the list of strings given.
     */
    public static Set<Group> getGroupSet(String... strings) {
        return Arrays.stream(strings)
                .map(Group::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a StudentId set containing the list of studentIds given.
     */
    public static Set<StudentId> getStudentIdSetFromStudentIds(StudentId... studentIds) {
        return Arrays.stream(studentIds)
                .collect(Collectors.toSet());
    }
}
