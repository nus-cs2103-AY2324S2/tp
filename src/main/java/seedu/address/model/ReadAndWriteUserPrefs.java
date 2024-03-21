package seedu.address.model;

/**
 * Modifiable user prefs.
 */
public interface ReadAndWriteUserPrefs {

    boolean getIsSample();

    void setIsSample(boolean status);

}
