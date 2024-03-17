package seedu.address.model.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;

/**
 * Represents a Customer's Order in the Addressbook.
 */
public class Order implements Comparable<Order> {
    public static final String MESSAGE_CONSTRAINTS = "Orders need to contain alphanumeric "
            + "product names and numeric quantities";
    private int id;
    private Map<Product, Quantity> productMap;

    private Person customer;

    /**
     * Constructs an {@code Order} Object.
     */
    public Order() {
        this.productMap = new HashMap<>();
    }

    /**
     * Constructs an {@code Order} Object with {@code id}.
     *
     * @param id ID of the Order.
     */
    public Order(int id) {
        this.id = id;
        productMap = new HashMap<>();
    }

    /**
     * Contructs an {@code Order} object with {@code map}
     * @param map Mappings of Products and Quantity
     */
    public Order(Map<Product, Quantity> map) {
        productMap = map;
    }

    /**
     * Sets the Order ID.
     * @param id ID of the Order.
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * Gets the Order ID.
     * @return the Order ID.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets the Product Quantity Map.
     * @return the Product Quantity Map.
     */
    public Map<Product, Quantity> getProductMap() {
        return this.productMap;
    }

    /**
     * Adds a specified quantity of Product into the order
     * @param newProduct Product to be added.
     * @param newQuantity Quantity of Product to be added.
     */
    public void addProduct(Product newProduct, Quantity newQuantity) {
        productMap.put(newProduct, newQuantity);
    }

    /**
     * Get quantity of this product in the order.
     * @param product Product of which quantity is returned.
     * @return quantity of the product.
     */
    public Optional<Quantity> getQuantity(Product product) {
        return Optional.ofNullable(productMap.get(product));
    }

    /**
     * Gets the quantity values of the product in the order.
     * @param product Product of which int quantity is returned.
     * @return int quantity of the product.
     */
    public int getQuantityValue(Product product) {
        int value = productMap.get(product).getValue();
        return value;
    }

    /**
     * Sets the quantity values of the product in the order.
     *
     * @param currProduct Product of which quantity to be editted.
     * @param newQuantity New Quantity of the specified product.
     * @return Updated order.
     */
    public Order changeQuantity(Product currProduct, Quantity newQuantity) {
        productMap.put(currProduct, newQuantity);
        return new Order(productMap);
    }

    /**
     * Deletes the specified product from the order.
     * @param product Product to be deleted.
     * @return Updated order.
     */
    public Order deleteProduct(Product product) {
        productMap.remove(product);
        return new Order(productMap);
    }

    public Order updateOrder(Product currProduct, Quantity newQuantity) {
        return newQuantity.getValue() == 0
                ? deleteProduct(currProduct)
                : changeQuantity(currProduct, newQuantity);
    }

    /**
     * Clears the product map.
     */
    public void clearProductMap() {
        this.productMap.clear();
    }

    /**
     * Sets the ProductMap of the order.
     *
     * @param productMap ProductMap to set to
     */
    public void setProductMap(Map<Product, Quantity> productMap) {
        this.productMap = productMap;
    }

    /**
     * Checks if the product map is empty.
     * @return boolean value of whether the product map is empty.
     */
    public boolean isEmpty() {
        return productMap.isEmpty();
    }

    /**
     * Gets the {@code Person} ordering the order
     *
     * @return the customer ordering the order
     */
    public Person getCustomer() {
        return this.customer;
    }

    /**
     * Compares the other Order Object with this Object based on the OrderID
     * @param otherOrder the object to be compared.
     * @return negative integer, zero, or a positive integer as this object is less than,
     *         equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Order otherOrder) {
        if (this.id < otherOrder.id) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Checks if two orders are the same.
     * @param otherOrder The other order to be checked against.
     * @return A boolean value of whether the two orders are the same.
     */
    public boolean isSameOrder(Order otherOrder) {
        if (otherOrder == this) {
            return true;
        }

        return otherOrder != null
                && otherOrder.id == this.id;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Order)) {
            return false;
        }

        Order otherOrder = (Order) other;
        return (this.id == otherOrder.id)
                && this.productMap.equals(otherOrder.productMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productMap);
    }

    @Override
    public String toString() {
        Set<Product> set = productMap.keySet();
        ArrayList<Product> productList = new ArrayList<>();
        productList.addAll(set);
        String str = "";
        for (int k = 0; k < productList.size(); k++) {
            str += productList.get(k).getName();
            str += ",";
            str += productMap.get(productList.get(k)).getValue();
        }
        return str;
    }
}
