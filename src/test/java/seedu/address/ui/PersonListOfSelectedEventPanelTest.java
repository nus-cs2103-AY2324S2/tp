package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import seedu.address.model.person.UniquePersonList;

public class PersonListOfSelectedEventPanelTest {

    private static final int TIMEOUT = 5;

    private PersonListOfSelectedEventPanel panel;
    private UniquePersonList personList;

    @BeforeAll
    public static void setUpClass() throws InterruptedException {
        // Initialize JavaFX toolkit
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(() -> latch.countDown());
        latch.await(TIMEOUT, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        // Create your test objects within the JavaFX application thread
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            personList = new UniquePersonList();
            panel = new PersonListOfSelectedEventPanel(personList.asUnmodifiableObservableList());
            latch.countDown();
        });
        latch.await(TIMEOUT, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void tearDownClass() {
        Platform.exit();
    }

    @Test
    public void constructor_emptyPersonList_correctVisibility() {
        assertFalse(panel.personListView.isVisible());
        assertFalse(panel.personListView.isManaged());
        assertTrue(panel.emptyListLabel.isVisible());
        assertTrue(panel.emptyListLabel.isManaged());
    }

    @Test
    public void updateListView_emptyPersonList_correctVisibility() {
        panel.updateListView();

        assertFalse(panel.personListView.isVisible());
        assertFalse(panel.personListView.isManaged());
        assertTrue(panel.emptyListLabel.isVisible());
        assertTrue(panel.emptyListLabel.isManaged());
    }

    @Test
    public void updateListView_nonEmptyPersonList_correctVisibility() {
        personList.add(BENSON);
        panel.updateListView();

        assertTrue(panel.personListView.isVisible());
        assertTrue(panel.personListView.isManaged());
        assertFalse(panel.emptyListLabel.isVisible());
        assertFalse(panel.emptyListLabel.isManaged());
    }
}
