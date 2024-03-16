package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.UniquePersonList;

public class PersonListOfSelectedEventPanelTest {

    private PersonListOfSelectedEventPanel panel;
    private UniquePersonList personList;

    @BeforeEach
    public void setUp() {
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return;
        }
        com.sun.javafx.application.PlatformImpl.startup(()->{});
        personList = new UniquePersonList();
        panel = new PersonListOfSelectedEventPanel(personList.asUnmodifiableObservableList());
    }

    @Test
    public void constructor_emptyPersonList_emptyListView() {
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return;
        }
        assertTrue(panel.personListView.getItems().isEmpty());
        assertTrue(panel.emptyListLabel.isVisible());
        assertFalse(panel.personListView.isVisible());
    }

    @Test
    public void constructor_nonEmptyPersonList_nonEmptyListView() {
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return;
        }
        personList.add(ALICE);
        assertFalse(panel.personListView.getItems().isEmpty());
        assertTrue(panel.personListView.isVisible());
        assertFalse(panel.emptyListLabel.isVisible());
    }

    @Test
    public void updateListView_emptyPersonList_showEmptyLabel() {
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return;
        }
        panel.updateListView();
        assertTrue(panel.emptyListLabel.isVisible());
        assertFalse(panel.personListView.isVisible());
    }

    @Test
    public void updateListView_nonEmptyPersonList_showListView() {
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            return;
        }
        personList.add(ALICE);
        panel.updateListView();

        assertFalse(panel.emptyListLabel.isVisible());
        assertTrue(panel.personListView.isVisible());
    }
}
