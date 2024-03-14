package seedu.address.model.order;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Order {
    private int id;
    private Map<Product, Quantity> productMap;

    public Order() {
        productMap = new HashMap<>();
    }

    public Order(int id) {
        this.id = id;
        productMap = new HashMap<>();
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public Map<Product, Quantity> getProductMap() {
        return this.productMap;
    }

    public void addProduct(Product newProduct, Quantity newQuantity) {
        productMap.put(newProduct, newQuantity);
    }

    public Quantity getQuantity(Product product) {
        Quantity currQuantity = productMap.get(product);
        return currQuantity;
    }

    public int getQuantityValue(Product product) {
        int value = productMap.get(product).getValue();
        return value;
    }

    public void changeQuantity(Product currProduct, int newQuantity) {
        Quantity currQuantity = productMap.get(currProduct);
        currQuantity.setQuantity(newQuantity);
    }

    public void deleteProduct(Product product) {
        productMap.remove(product);
    }

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
        return productMap.toString();
    }
}
