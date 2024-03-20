package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.testfx.framework.junit5.ApplicationExtension;

import javafx.application.Platform;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


@ExtendWith(ApplicationExtension.class)
public class PersonCardTest {

    @Mock
    private Person mockedPerson = new PersonBuilder(ALICE).withEmail("e1234567@u.nus.edu").build();

    private PersonCard personCard;

    @Mock
    private DesktopInterface desktopWrapper;

    @BeforeEach
    public void setUp() {
        // Initialize the PersonCard with a mocked Person
        personCard = new PersonCard(mockedPerson, 1);
        personCard.setTestEnvironment(true);
        desktopWrapper = mock(DesktopInterface.class);
        // Inject the mocked DesktopWrapper into the PersonCard
        personCard.setDesktopWrapper(desktopWrapper);
    }

    @Test
    public void successfulEmailClick() throws IOException, URISyntaxException, InterruptedException {

        doNothing().when(desktopWrapper).mail(any(URI.class));
        // Create a CountDownLatch with initial count 1
        CountDownLatch latch = new CountDownLatch(1);

        // Call the method under test on JavaFX Application Thread
        Platform.runLater(() -> {
            personCard.handleEmailClicked();
            // Signal that the action is completed
            latch.countDown();
        });

        // Wait for the action to complete
        latch.await();

        Thread.sleep(100);
        // Verify that DesktopWrapper.mail() is called with the correct URI
        verify(desktopWrapper).mail(new URI("mailto:" + "e1234567@u.nus.edu"));
    }

    @Test
    public void unsuccessfulEmailClick() throws IOException {

        // Throw an exception when desktopWrapper.mail() is called
        doThrow(new IOException("Email client not supported")).when(desktopWrapper).mail(any(URI.class));

        // Call the method under test
        personCard.handleEmailClicked();

        // Verify that an error alert is shown
        assertTrue(personCard.isEmailErrorAlertShown());


    }


}
