package seedu.address.ui;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.order.Order;

/**
 * A UI component that displays information of an {@code Order}.
 */
public class OrderCard extends UiPart<Region> {
    private static final String FXML = "OrderListCard.fxml";

    public final Order order;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label orderId;
    @FXML
    private Label customerName;
    @FXML
    private FlowPane products;


    /**
     * Creates an {@code OrderCard} with the given {@code Order} to display.
     */
    public OrderCard(Order order) {
        super(FXML);
        this.order = order;
        orderId.setText("Order " + order.getId());
        customerName.setText(order.getCustomer().getName().fullName);
        ArrayList<String> productList = new ArrayList<>();
        order.getProductMap().forEach((product, quantity) -> productList.add(product + " x " + quantity));
        productList.stream().forEach(product -> products.getChildren().add(new Label(product)));
    }
}
