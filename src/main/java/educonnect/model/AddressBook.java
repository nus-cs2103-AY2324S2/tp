package educonnect.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import educonnect.commons.util.ToStringBuilder;
import educonnect.model.student.Email;
import educonnect.model.student.Student;
import educonnect.model.student.StudentId;
import educonnect.model.student.TelegramHandle;
import educonnect.model.student.UniqueStudentList;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueStudentList students;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Students in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
    }

    //// student-level operations

    /**
     * Returns true if a student with same unique identifier as {@code student} exists in the address book.
     */
    public boolean hasSameUniqueIdentifier(Student student) {
        requireNonNull(student);
        return students.containsSameUniqueIdentifier(student);
    }

    /**
     * Returns true if a student with same student id as {@code student} exists in the address book.
     */
    public boolean hasStudentId(Student student) {
        requireNonNull(student);
        return students.containsStudentId(student);
    }

    /**
     * Returns true if a student with same student id as {@code studentId} exists in the address book.
     */
    public boolean hasStudentId(StudentId studentId) {
        requireNonNull(studentId);
        return students.containsStudentId(studentId);
    }

    /**
     * Returns Optional of Student if a student with same student id as {@code studentId} exists in the address book.
     */
    public Optional<Student> getStudentWithStudentId(StudentId studentId) {
        return students.getStudentWithStudentId(studentId);
    }

    /**
     * Returns true if a student with same email  as {@code student} exists in the address book.
     */
    public boolean hasEmail(Student student) {
        requireNonNull(student);
        return students.containsEmail(student);
    }

    /**
     * Returns true if a student with same email as {@code email} exists in the address book.
     */
    public boolean hasEmail(Email email) {
        requireNonNull(email);
        return students.containsEmail(email);
    }

    /**
     * Returns Optional of Student if a student with same email as {@code email} exists in the address book.
     */
    public Optional<Student> getStudentWithEmail(Email email) {
        return students.getStudentWithEmail(email);
    }

    /**
     * Returns true if a student with same telegram handle  as {@code student} exists in the address book.
     */
    public boolean hasTelegramHandle(Student student) {
        requireNonNull(student);
        return students.containsTelegramHandle(student);
    }

    /**
     * Returns true if a student with same telegram handle as {@code telegramHandle} exists in the address book.
     */
    public boolean hasTelegramHandle(TelegramHandle telegramHandle) {
        requireNonNull(telegramHandle);
        return students.containsTelegramHandle(telegramHandle);
    }

    /**
     * Returns Optional of Student if a student with same telegram handle
     * as {@code telegramHandle} exists in the address book.
     */
    public Optional<Student> getStudentWithTelegramHandle(TelegramHandle telegramHandle) {
        return students.getStudentWithTelegramHandle(telegramHandle);
    }

    /**
     * Adds a student to the address book.
     * The student must not already exist in the address book.
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as another existing student in
     * the address book.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code student} from this {@code AddressBook}.
     * {@code student} must exist in the address book.
     */
    public void removeStudent(Student student) {
        students.remove(student);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("students", students)
                .toString();
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return students.equals(otherAddressBook.students);
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
