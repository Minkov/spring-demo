package productsstore.demo.utils.validators;

import org.springframework.stereotype.Repository;
import productsstore.demo.entities.Product;
import productsstore.demo.utils.validators.base.Validator;

public class ProductValidator implements Validator<Product> {
    private static final int MIN_NAME_LENGTH = 4;
    private static final float MIN_PRICE = 0;
    private static final int MIN_QUANTITY = 0;
    private static final int MAX_NAME_LENGTH = 20;

    @Override
    public boolean isValid(Product product) {
        return product != null &&
            isNameValid(product.getName()) &&
            isPriceValid(product.getPrice()) &&
            isQuantityValid(product.getQuantity());
    }

    private boolean isQuantityValid(int quantity) {
        return quantity > MIN_QUANTITY;
    }

    private boolean isPriceValid(float price) {
        return price > MIN_PRICE;
    }

    private boolean isNameValid(String name) {
        return name != null &&
            name.length() >= MIN_NAME_LENGTH &&
            name.length() <= MAX_NAME_LENGTH;
    }
}
