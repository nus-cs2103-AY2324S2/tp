package seedu.address.testutil;

import seedu.address.model.person.Products;
import seedu.address.model.person.Supplier;
import seedu.address.model.person.TermsOfService;
import seedu.address.model.util.SampleDataUtil;

/**
 * A builder class for creating instances of the {@link Supplier} class for testing purposes.
 * Allows customization of the supplier's products and terms of service.
 */
public class SupplierBuilder extends PersonBuilder<SupplierBuilder> {

    public static final String DEFAULT_TERMS_OF_SERVICE = "PDPA compliant";
    public static final String DEFAULT_PRODUCTS = "Apple Banana";

    private Products products;
    private TermsOfService termsOfService;

    /**
     * Constructs a new SupplierBuilder with default values.
     * The default products are "Apple Banana" and the default terms of service is an empty string.
     */
    public SupplierBuilder() {
        super();
        products = new Products(DEFAULT_PRODUCTS);
        termsOfService = new TermsOfService("");
    }

    /**
     * Constructs a new SupplierBuilder with the same values as the given supplier.
     *
     * @param supplier The supplier to copy values from.
     */
    public SupplierBuilder(Supplier supplier) {
        super(supplier);
        products = supplier.getProducts();
        termsOfService = supplier.getTermsOfService();
    }

    /**
     * Sets the products of the supplier to the given list of product names.
     *
     * @param products The product names.
     * @return This SupplierBuilder object.
     */
    public SupplierBuilder withProducts(String... products) {
        this.products = SampleDataUtil.getProducts(products);
        return this;
    }

    /**
     * Sets the terms of service of the supplier to the given string.
     *
     * @param termsOfService The terms of service.
     * @return This SupplierBuilder object.
     */
    public SupplierBuilder withTermsOfService(String termsOfService) {
        this.termsOfService = new TermsOfService(termsOfService);
        return this;
    }

    /**
     * Builds and returns a new instance of the Supplier class with the configured values.
     *
     * @return A new Supplier object.
     */
    @Override
    public Supplier build() {
        return new Supplier(name, phone, email, address, remark, tags, products, termsOfService);
    }
}
