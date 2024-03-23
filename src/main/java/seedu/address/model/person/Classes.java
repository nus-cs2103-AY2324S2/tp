package seedu.address.model.person;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.AddressBook;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * Represents a Class in the class book.
 */
public class Classes {

    private CourseCode courseCode; //tutorial grp
    private AddressBook addressBook;
    private AddressBookStorage addressBookStorage;

    /**
     * Constructor for Classes.
     */
    public Classes(CourseCode courseCode, AddressBook addressBook) {
        this.courseCode = courseCode;
        this.addressBook = addressBook;
        this.addressBookStorage = new JsonAddressBookStorage(getFilePath());
    }

    /**
     * Getter function to return courseCode.
     *
     * @return courseCode
     */
    public CourseCode getCourseCode() {
        return courseCode;
    }

    /**
     * Returns true if both class have the same courseCode.
     */
    public boolean isSameClass(Classes otherClass) {
        if (otherClass == this) {
            return true;
        }

        return otherClass != null && otherClass.getCourseCode().equals(getCourseCode());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Classes)) {
            return false;
        }

        Classes otherClass = (Classes) other;
        return courseCode.equals(otherClass.courseCode);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(courseCode);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("courseCode", courseCode)
                .toString();
    }

    public List<String> getStudents() {
        return new ArrayList<>();
    }

    public AddressBook getAddressBook() {
        return this.addressBook;
    }
    public Path getFilePath() {
        // Modify this method to generate the file path based on class name, etc.
        String fileName = courseCode.getCourseCode() + ".json";
        return Paths.get("data/classbook", fileName);
    }

    public void addPerson(Person person) {
        addressBook.addPerson(person);
    }

//    public Object getPersons() {
//    }

}
