package seedu.address.testutil;

import seedu.address.model.person.Client;
import seedu.address.model.person.Products;
import seedu.address.model.util.SampleDataUtil;

/**
 * Represents a builder class for creating instances of the Client class.
 * Inherits from the PersonBuilder class.
 */
public class ClientBuilder extends PersonBuilder<ClientBuilder> {

    public static final String DEFAULT_PREFERENCES = "";
    public static final String DEFAULT_PRODUCTS = "Apple Banana";

    private Products products;
    private String preferences;

    /**
     * Constructs a new ClientBuilder with default values.
     */
    public ClientBuilder() {
        super();
        products = new Products(DEFAULT_PRODUCTS);
        preferences = DEFAULT_PREFERENCES;
    }

    /**
     * Initializes the ClientBuilder with the data of {@code clientToCopy}.
     */
    public ClientBuilder(Client client) {
        super(client);
        products = client.getProducts();
        preferences = client.getPreferences();
    }

    /**
     * Sets the products of the client that we are building.
     *
     * @param products The products of the client.
     * @return The ClientBuilder with the products set to {@code products}.
     */
    public ClientBuilder withProducts(String... products) {
        this.products = SampleDataUtil.getProducts(products);
        return this;
    }

    /**
     * Sets the preferences of the client that we are building.
     *
     * @param preferences The preferences of the client.
     * @return The ClientBuilder with the preferences set to {@code preferences}.
     */
    public ClientBuilder withPreferences(String preferences) {
        this.preferences = preferences;
        return this;
    }

    /**
     * Builds a new Client object with the attributes of the ClientBuilder.
     *
     * @return A new Client object.
     */
    @Override
    public Client build() {
        return new Client(name, phone, email, address, remark, tags, products, preferences);
    }
}
