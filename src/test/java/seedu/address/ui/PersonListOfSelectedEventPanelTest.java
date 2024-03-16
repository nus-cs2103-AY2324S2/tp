package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

public class PersonListOfSelectedEventPanelTest {

    private PersonListOfSelectedEventPanel panel;
    private UniquePersonList personList;

    @BeforeEach
    public void setUp() {
        com.sun.javafx.application.PlatformImpl.startup(()->{});
        personList = new UniquePersonList();
        panel = new PersonListOfSelectedEventPanel(personList.asUnmodifiableObservableList());
    }

    @Test
    public void constructor_emptyPersonList_emptyListView() {
        assertTrue(panel.personListView.getItems().isEmpty());
        assertTrue(panel.emptyListLabel.isVisible());
        assertFalse(panel.personListView.isVisible());
    }

    @Test
    public void constructor_nonEmptyPersonList_nonEmptyListView() {
        personList.add(ALICE);

        assertFalse(panel.personListView.getItems().isEmpty());
        assertTrue(panel.personListView.isVisible());
        assertFalse(panel.emptyListLabel.isVisible());
    }

    @Test
    public void updateListView_emptyPersonList_showEmptyLabel() {
        panel.updateListView();

        assertTrue(panel.emptyListLabel.isVisible());
        assertFalse(panel.personListView.isVisible());
    }

    @Test
    public void updateListView_nonEmptyPersonList_showListView() {
        Person person = ALICE;
        personList.add(person);

        panel.updateListView();

        assertFalse(panel.emptyListLabel.isVisible());
        assertTrue(panel.personListView.isVisible());
    }
}
