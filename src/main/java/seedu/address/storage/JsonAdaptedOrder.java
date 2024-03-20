package seedu.address.storage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Order;
import seedu.address.model.order.Product;
import seedu.address.model.order.Quantity;

/**
 * Jackson friendly version of {@link Order}.
 */
public class JsonAdaptedOrder {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Order's %s field is missing!";
    private int id;
    private Map<String, Integer> productMap;
    private Integer customer;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given {@code order}.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("id") Integer id,
                            @JsonProperty("productMap") Map<String, Integer> productMap
                            ) {
        this.id = id;
        this.productMap = productMap;
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order order) {
        this.id = order.getId();
        Map<Product, Quantity> productQuantityMap = order.getProductMap();
        List<Product> productKeySet = productQuantityMap.keySet().stream().collect(Collectors.toList());
        Map<String, Integer> map = new HashMap<>();
        for (int k = 0; k < productKeySet.size(); k++) {
            Product currProd = productKeySet.get(k);
            Quantity currQuant = productQuantityMap.get(currProd);
            map.put(currProd.getName(), currQuant.getValue());
        }
        this.productMap = map;

    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Order toModelType() {
        Order modelOrder = new Order(this.id);
        Map<Product, Quantity> map = new HashMap<>();
        Set<String> jsonProduct = this.productMap.keySet();
        List<String> productList = jsonProduct.stream().collect(Collectors.toList());
        for (int k = 0; k < jsonProduct.size(); k++) {
            String currProdString = productList.get(k);
            Integer currQuantInt = this.productMap.get(productList.get(k));
            Product currProd = new Product(currProdString);
            Quantity currQuant = new Quantity(currQuantInt);
            map.put(currProd, currQuant);
        }
        modelOrder.setProductMap(map);
        return modelOrder;
    }
}
