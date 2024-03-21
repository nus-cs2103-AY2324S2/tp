package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.contact.Contact;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final CodeConnect codeConnect;
    private final UserPrefs userPrefs;
    private final FilteredList<Contact> filteredContacts;

    /**
     * Initializes a ModelManager with the given codeConnect and userPrefs.
     */
    public ModelManager(ReadOnlyCodeConnect codeConnect, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(codeConnect, userPrefs);

        logger.fine("Initializing with address book: " + codeConnect + " and user prefs " + userPrefs);

        this.codeConnect = new CodeConnect(codeConnect);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredContacts = new FilteredList<>(this.codeConnect.getContactList());
    }

    public ModelManager() {
        this(new CodeConnect(), new UserPrefs());
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
    public Path getCodeConnectFilePath() {
        return userPrefs.getCodeConnectFilePath();
    }

    @Override
    public void setCodeConnectFilePath(Path codeConnectFilePath) {
        requireNonNull(codeConnectFilePath);
        userPrefs.setCodeConnectFilePath(codeConnectFilePath);
    }

    //=========== CodeConnect ================================================================================

    @Override
    public void setCodeConnect(ReadOnlyCodeConnect codeConnect) {
        this.codeConnect.resetData(codeConnect);
    }

    @Override
    public ReadOnlyCodeConnect getCodeConnect() {
        return codeConnect;
    }

    @Override
    public boolean hasContact(Contact contact) {
        requireNonNull(contact);
        return codeConnect.hasContact(contact);
    }

    @Override
    public void deleteContact(Contact target) {
        codeConnect.removeContact(target);
    }

    @Override
    public void addContact(Contact contact) {
        codeConnect.addContact(contact);
        updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
    }

    @Override
    public void setContact(Contact target, Contact editedContact) {
        requireAllNonNull(target, editedContact);

        codeConnect.setContact(target, editedContact);
    }

    //=========== Filtered Contact List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Contact} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Contact> getFilteredContactList() {
        return filteredContacts;
    }

    @Override
    public void updateFilteredContactList(Predicate<Contact> predicate) {
        requireNonNull(predicate);
        filteredContacts.setPredicate(predicate);
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
        return codeConnect.equals(otherModelManager.codeConnect)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredContacts.equals(otherModelManager.filteredContacts);
    }

}
