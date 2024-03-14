package educonnect.model.student;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import educonnect.commons.util.CollectionUtil;
import educonnect.model.student.exceptions.DuplicateStudentException;
import educonnect.model.student.exceptions.StudentNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of students that enforces uniqueness between its elements and does not allow nulls.
 * A student is considered unique by comparing using {@code Student#isSameStudent(Student)}.
 * As such, adding and updating of students uses {@code Student#isSameStudent(Student)} for equality
 * so as to ensure that the student being added or updated is unique in terms of identity in the UniqueStudentList.
 * However, the removal of a student uses {@code Student#equals(Object)} so as to ensure that the student
 * with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Student#isSameStudent(Student)
 */
public class UniqueStudentList implements Iterable<Student> {

    private final ObservableList<Student> internalList = FXCollections.observableArrayList();
    private final ObservableList<Student> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent student unique identifier as the given argument.
     * The unique identifiers are student id, email and telegram handle
     */
    public boolean containsSameUniqueIdentifier(Student toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameStudentId)
                || internalList.stream().anyMatch(toCheck::isSameEmail)
                || internalList.stream().anyMatch(toCheck::isSameTelegramHandle);
    }

    /**
     * Returns true if the list contains an equivalent student id as the given argument.
     */
    public boolean containsStudentId(Student toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameStudentId);
    }

    public boolean containsStudentId(StudentId studentIdToCheck) {
        requireNonNull(studentIdToCheck);
        return internalList.stream().anyMatch(student -> student.isSameStudentId(studentIdToCheck));
    }

    public Optional<Student> getStudentWithStudentId(StudentId studentId) {
        return internalList.stream().filter(student -> student.isSameStudentId(studentId)).findFirst();
    }

    /**
     * Returns true if the list contains an equivalent email as the given argument.
     */
    public boolean containsEmail(Student toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEmail);
    }

    public boolean containsEmail(Email email) {
        requireNonNull(email);
        return internalList.stream().anyMatch(student -> student.isSameEmail(email));
    }

    public Optional<Student> getStudentWithEmail(Email email) {
        return internalList.stream().filter(student -> student.isSameEmail(email)).findFirst();
    }

    /**
     * Returns true if the list contains an equivalent telegram handle as the given argument.
     */
    public boolean containsTelegramHandle(Student toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTelegramHandle);
    }

    public boolean containsTelegramHandle(TelegramHandle telegramHandle) {
        requireNonNull(telegramHandle);
        return internalList.stream().anyMatch(student -> student.isSameTelegramHandle(telegramHandle));
    }

    public Optional<Student> getStudentWithTelegramHandle(TelegramHandle telegramHandle) {
        return internalList.stream().filter(student -> student.isSameTelegramHandle(telegramHandle)).findFirst();
    }

    /**
     * Adds a student to the list.
     * The student must not share the same unique identifier in the list.
     */
    public void add(Student toAdd) {
        requireNonNull(toAdd);
        if (containsSameUniqueIdentifier(toAdd)) {
            throw new DuplicateStudentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the list.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the list.
     */
    public void setStudent(Student target, Student editedStudent) {
        CollectionUtil.requireAllNonNull(target, editedStudent);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new StudentNotFoundException();
        }
        if (!target.isSameStudentId(editedStudent) && containsStudentId(editedStudent)) {
            throw new DuplicateStudentException();
        }
        if (!target.isSameEmail(editedStudent) && containsEmail(editedStudent)) {
            throw new DuplicateStudentException();
        }
        if (!target.isSameTelegramHandle(editedStudent) && containsTelegramHandle(editedStudent)) {
            throw new DuplicateStudentException();
        }

        internalList.set(index, editedStudent);
    }

    /**
     * Removes the equivalent student from the list.
     * The student must exist in the list.
     */
    public void remove(Student toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new StudentNotFoundException();
        }
    }

    public void setStudents(UniqueStudentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        CollectionUtil.requireAllNonNull(students);
        if (!studentsAreUnique(students)) {
            throw new DuplicateStudentException();
        }

        internalList.setAll(students);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Student> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Student> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueStudentList)) {
            return false;
        }

        UniqueStudentList otherUniqueStudentList = (UniqueStudentList) other;
        return internalList.equals(otherUniqueStudentList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code students} contains unique students that do not share the same unique identifier.
     */
    private boolean studentsAreUnique(List<Student> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).isSameUniqueIdentifier(students.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
