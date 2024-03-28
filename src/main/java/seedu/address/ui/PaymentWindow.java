package seedu.address.ui;

import java.io.IOException;
import java.util.logging.Logger;

import com.google.zxing.WriterException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.paynow.PayNowCode;
import seedu.address.model.person.Person;

/**
 * Controller for the Payment window
 */
public class PaymentWindow extends UiPart<Stage> {
    private static final String FXML = "PaymentWindow.fxml";
    private static final Logger logger = LogsCenter.getLogger(PaymentWindow.class);
    private static final String HELP_TOOLTIP_TEXT = "Open your banking application and scan this QR code to pay %s!\n"
            + "Note that this QR code only works if the mobile number is registered to a bank account.";
    private final Runnable onResetDebt;

    @FXML
    private ImageView qrCode;
    @FXML
    private Button cancelButton;

    @FXML
    private Button resetButton;
    @FXML
    private Tooltip helpTooltip;

    /**
     * Creates a new QrWindow.
     *
     * @param person The person whom the user is attempting to pay.
     */
    public PaymentWindow(Person person, Runnable onResetDebt) throws IOException, WriterException {
        super(FXML, new Stage());
        this.onResetDebt = onResetDebt;
        Image image = new Image(PayNowCode.generatePayNowQrCode(
                person.getPhone().toString(),
                Math.max(0, -person.getMoneyOwed().moneyOwed)));
        qrCode.setImage(image);
        if (person.getMoneyOwed().moneyOwed == 0) {
            resetButton.setManaged(false);
        }
        helpTooltip.setText(String.format(HELP_TOOLTIP_TEXT, person.getName()));
    }


    /**
     * Shows the payment window.
     *
     * @throws IllegalStateException <ul>
     *                               <li>
     *                               if this method is called on a thread other than the JavaFX Application Thread.
     *                               </li>
     *                               <li>
     *                               if this method is called during animation or layout processing.
     *                               </li>
     *                               <li>
     *                               if this method is called on the primary stage.
     *                               </li>
     *                               <li>
     *                               if {@code dialogStage} is already showing.
     *                               </li>
     *                               </ul>
     */
    public void show() {
        logger.fine("Showing payment page.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    public void onResetDebt() {
        onResetDebt.run();
    }
}
