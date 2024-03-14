package seedu.address.model.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a Customer's Order in the Addressbook.
 */
public class Order {
    public static final String MESSAGE_CONSTRAINTS = "Orders need to contain alphanumeric "
            + "product names and numeric quantities";
    private int id;
    private Map<Product, Quantity> productMap;

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
    public Quantity getQuantity(Product product) {
        Quantity currQuantity = productMap.get(product);
        return currQuantity;
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
     * @param currProduct Product of which quantity to be editted.
     * @param newQuantity new Quantity of the specified product.
     */
    public void changeQuantity(Product currProduct, int newQuantity) {
        Quantity currQuantity = productMap.get(currProduct);
        currQuantity.setQuantity(newQuantity);
    }

    /**
     * Deletes the specified product from the order.
     * @param product Product to be deleted.
     */
    public void deleteProduct(Product product) {
        productMap.remove(product);
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
