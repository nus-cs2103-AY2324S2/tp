package seedu.address.testutil;

import java.util.HashMap;
import java.util.Map;

import seedu.address.model.order.Order;
import seedu.address.model.order.Product;
import seedu.address.model.order.Quantity;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Order objects.
 * Example usage: <br>
 *     {@code Order order = new OrderBuilder().withIndex(1).withProductQuantity("Cupcakes", "2").build();}
 */
public class OrderBuilder {
    public static final int DEFAULT_ID = 1;
    public static final Person DEFAULT_PERSON = TypicalPersons.ALICE;

    private int id;
    private Map<Product, Quantity> productMap;
    private Person person;

    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {
        id = DEFAULT_ID;
        productMap = new HashMap<>();
        person = DEFAULT_PERSON;
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
     * Adds to the productMap of the {@code Order} that we are building.
     *
     * @param product the name of the product to add
     * @param quantity the number of the products to add
     */
    public OrderBuilder withProductQuantity(String product, String quantity) {
        Product productToAdd = new Product(product);
        Quantity quantityToAdd = new Quantity(Integer.parseInt(quantity));
        this.productMap.put(productToAdd, quantityToAdd);
        return this;
    }

    /**
     * Sets the {@code Person} of the {@code Order} that we are building.
     */
    public OrderBuilder withPerson(Person person) {
        this.person = person;
        return this;
    }



    public Order build() {
        Order order = new Order(this.id);
        order.setProductMap(this.productMap);
        return order;
    }
}
