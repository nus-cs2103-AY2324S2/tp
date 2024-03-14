package seedu.address.model.order;

import java.util.*;

public class Order {
    public static final String MESSAGE_CONSTRAINTS = "Orders need to contain alphanumeric product names and numeric quantities";
    private int id;
    private Map<Product, Quantity> productMap;

    public Order() {
        this.productMap = new HashMap<>();
    }

    public Order(int id) {
        this.id = id;
        productMap = new HashMap<>();
    }

    public Order(Map<Product, Quantity> map) {
        productMap = map;
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
