package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Loan;

import java.util.Comparator;

/**
 * An UI component that displays information of a {@code Loan}.
 */
public class LoanCard extends UiPart<Region> {
    private static final String FXML = "LoanListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Loan loan;
    private static final String DEFAULT_LOAN_PREFIX = "Loan No. ";

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label amount;
    @FXML
    private Label startDate;
    @FXML
    private Label endDate;

    /**
     * Creates a {@code LoanCard} with the given {@code Loan} and index to display.
     */
    public LoanCard(Loan loan, int displayedIndex) {
        super(FXML);
        this.loan = loan;
        name.setText(DEFAULT_LOAN_PREFIX + displayedIndex);
        amount.setText(String.valueOf(loan.getAmount()));
        startDate.setText(loan.getStartDate().toString());
        endDate.setText(loan.getEndDate().toString());
    }
}
