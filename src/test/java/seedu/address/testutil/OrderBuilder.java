package seedu.address.testutil;

import java.util.Map;

import seedu.address.model.order.Product;
import seedu.address.model.order.Quantity;
import seedu.address.model.order.Order;

public class OrderBuilder {
    public static final int DEFAULT_ID = 1;

    private int id;

    private Map<Product,Quantity> productMap;

    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {
        id = DEFAULT_ID;
    }

    /**
     * Initializes the OrderBuilder with the data of {@code orderToCopy}.
     */
    public OrderBuilder(Order orderToCopy) {
        productMap = orderToCopy.getProductMap();
    }

    /**
     * Sets the index of the {@code Order} that we are building.
     */
    public OrderBuilder withIndex(int id) {
        this.id = id;
        return this;
    }

    /**
     * Sets the productMap of the {@code Order} that we are building.
     */
    public OrderBuilder withProductMap(Map<Product, Quantity> productMap) {
        this.productMap = productMap;
        return this;
    }

    public Order build() {
        Order order = new Order(this.id);
        order.setProductMap(this.productMap);
        return order;
    }
}
