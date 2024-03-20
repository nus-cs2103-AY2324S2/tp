package educonnect.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import educonnect.commons.core.GuiSettings;
import educonnect.commons.core.LogsCenter;
import educonnect.commons.util.CollectionUtil;
import educonnect.model.student.Email;
import educonnect.model.student.Student;
import educonnect.model.student.StudentId;
import educonnect.model.student.TelegramHandle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        CollectionUtil.requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.addressBook.getStudentList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasSameUniqueIdentifier(Student student) {
        requireNonNull(student);
        return addressBook.hasSameUniqueIdentifier(student);
    }

    @Override
    public boolean hasStudentId(Student student) {
        requireNonNull(student);
        return addressBook.hasStudentId(student);
    }

    @Override
    public boolean hasStudentId(StudentId studentId) {
        requireNonNull(studentId);
        return addressBook.hasStudentId(studentId);
    }

    @Override
    public Optional<Student> getStudentWithStudentId(StudentId studentId) {
        return addressBook.getStudentWithStudentId(studentId);
    }

    @Override
    public boolean hasEmail(Student student) {
        requireNonNull(student);
        return addressBook.hasEmail(student);
    }

    @Override
    public boolean hasEmail(Email email) {
        requireNonNull(email);
        return addressBook.hasEmail(email);
    }

    @Override
    public Optional<Student> getStudentWithEmail(Email email) {
        return addressBook.getStudentWithEmail(email);
    }

    @Override
    public boolean hasTelegramHandle(Student student) {
        requireNonNull(student);
        return addressBook.hasTelegramHandle(student);
    }

    @Override
    public boolean hasTelegramHandle(TelegramHandle telegramHandle) {
        requireNonNull(telegramHandle);
        return addressBook.hasTelegramHandle(telegramHandle);
    }

    @Override
    public Optional<Student> getStudentWithTelegramHandle(TelegramHandle telegramHandle) {
        return addressBook.getStudentWithTelegramHandle(telegramHandle);
    }

    @Override
    public void deleteStudent(Student target) {
        addressBook.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        addressBook.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        CollectionUtil.requireAllNonNull(target, editedStudent);

        addressBook.setStudent(target, editedStudent);
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Collection<Predicate<Student>> predicates) {
        requireNonNull(predicates);
        // Combine all predicates using logical AND
        Predicate<Student> combinedPredicate = predicates.stream()
                .reduce(Predicate::and)
                .orElse(student -> true);

        filteredStudents.setPredicate(combinedPredicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredStudents.equals(otherModelManager.filteredStudents);
    }

}
