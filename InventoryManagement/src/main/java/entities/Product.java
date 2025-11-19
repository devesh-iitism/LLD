package entities;

public class Product {
    private final String productId;
    private final String name;
    private final String description;

    // Private constructor to be used by the builder
    private Product(ProductBuilder builder) {
        this.productId = builder.productId;
        this.name = builder.name;
        this.description = builder.description;
    }

    // Getters
    public String getProductId() { return productId; }
    public String getName() { return name; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return "Product{id='" + productId + "', name='" + name + "'}";
    }

    // --- Static Builder Class ---
    public static class ProductBuilder {
        private final String productId;
        private String name;
        private String description;

        public ProductBuilder(String productId) {
            this.productId = productId;
        }

        public ProductBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Product build() {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalStateException("Product name cannot be null or empty.");
            }
            return new Product(this);
        }
    }
}