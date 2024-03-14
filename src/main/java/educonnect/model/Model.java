package educonnect.model;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.Optional;

import educonnect.commons.core.GuiSettings;
import educonnect.model.student.Email;
import educonnect.model.student.Student;
import educonnect.model.student.StudentId;
import educonnect.model.student.TelegramHandle;
import javafx.collections.ObservableList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a student with the same unique identifer as {@code student} exists in the address book.
     * The unique identifiers are student id, email, telegram handle
     */
    boolean hasSameUniqueIdentifier(Student student);

    /**
     * Returns true if a student with the student id as {@code student} exists in the address book.
     */
    boolean hasStudentId(Student student);
    boolean hasStudentId(StudentId studentId);
    Optional<Student> getStudentWithStudentId(StudentId studentId);

    /**
     * Returns true if a student with the email as {@code student} exists in the address book.
     */
    boolean hasEmail(Student student);
    boolean hasEmail(Email email);
    Optional<Student> getStudentWithEmail(Email email);

    /**
     * Returns true if a student with the telegram handle as {@code student} exists in the address book.
     */
    boolean hasTelegramHandle(Student student);
    boolean hasTelegramHandle(TelegramHandle telegramHandle);
    Optional<Student> getStudentWithTelegramHandle(TelegramHandle telegramHandle);

    /**
     * Deletes the given student.
     * The student must exist in the address book.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the address book.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as another existing student in
     * the address book.
     */
    void setStudent(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);
}
