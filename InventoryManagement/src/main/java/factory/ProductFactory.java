package factory;

import entities.Product;

public class ProductFactory {
    public static Product createProduct(String productId, String name, String description) {
        return new Product.ProductBuilder(productId)
                .withName(name)
                .withDescription(description)
                .build();
    }
}