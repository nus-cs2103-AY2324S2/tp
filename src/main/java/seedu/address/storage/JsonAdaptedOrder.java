package seedu.address.storage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

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
    private Map<JsonAdaptedProduct, JsonAdaptedQuantity> productMap;
    private JsonAdaptedPerson customer;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given {@code order}.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("id") Integer id,
                            @JsonProperty("productMap") Map<JsonAdaptedProduct, JsonAdaptedQuantity> productMap,
                            @JsonProperty("orderCustomer") JsonAdaptedPerson orderCustomer) {
        this.id = id;
        this.productMap = productMap;
        this.customer = orderCustomer;
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order order) {
        this.id = order.getId();

    }

    @JsonValue
    public Map<JsonAdaptedProduct, JsonAdaptedQuantity> getOrder() {
        return productMap;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Order toModelType() throws IllegalValueException {
        Order modelOrder = new Order(this.id);
        modelOrder.setCustomer(customer.toModelType());
        Map<Product, Quantity> map = new HashMap<>();
        Set<JsonAdaptedProduct> jsonProduct = this.productMap.keySet();
        List<JsonAdaptedProduct> productList = jsonProduct.stream().collect(Collectors.toList());
        for (int k = 0; k < jsonProduct.size(); k++) {
            JsonAdaptedProduct currProd = productList.get(k);
            JsonAdaptedQuantity currQuant = this.productMap.get(productList.get(k));
            map.put(currProd.toModelType(), currQuant.toModelType());
        }
        modelOrder.setProductMap(map);
        return modelOrder;
    }
}
