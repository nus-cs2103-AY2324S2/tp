package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.order.Order;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class OrderCard extends UiPart<Region> {

    private static final String FXML = "OrderListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Order order;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label orderId;
    @FXML
    private Label orderDate;
    @FXML
    private Label deadline;
    @FXML
    private Label price;
    @FXML
    private Label remark;
    @FXML
    private Label status;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public OrderCard(Order order, int displayedIndex) {
        super(FXML);
        this.order = order;
        id.setText(displayedIndex + ". ");
        orderId.setText("OrderId: " + order.getOrderId().toString());
        orderDate.setText("Date: " + order.getOrderDate().toString());
        deadline.setText("Deadline: " + order.getDeadline().toString());
        price.setText("Price: " + order.getPrice().toString());
        remark.setText("Description: " + order.getRemark().toString());
        status.setText("Status: " + order.getStatus().toString());
    }
}
