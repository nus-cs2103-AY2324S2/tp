package seedu.address.ui;

/**
 * A fake implementation of a confirmation box for testing purposes. This class simulates
 * user responses to confirmation dialogs without displaying an actual user interface.
 * It is designed to be used in unit tests to provide controlled responses to actions
 * that would normally require user interaction.
 */
public class FakeConfirmationBox implements Prompt {
    private boolean userResponse;

    /**
     * Constructs a {@code FakeConfirmationBox} with a predetermined user response.
     *
     * @param userResponse the simulated response to the confirmation dialog. {@code true} to simulate
     *                     the user confirming the action, {@code false} to simulate the user canceling the action.
     */
    public FakeConfirmationBox(boolean userResponse) {
        this.userResponse = userResponse;
    }

    /**
     * Simulates the display of a confirmation dialog and returns the predetermined user response.
     *
     * @param title   the title of the dialog (not used).
     * @param message the message of the dialog (not used).
     * @return the predetermined user response to the simulated confirmation dialog.
     */
    @Override
    public boolean display(String title, String message) {
        return userResponse;
    }
}
