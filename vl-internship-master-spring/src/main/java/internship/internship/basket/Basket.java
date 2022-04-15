package internship.internship.basket;

import internship.internship.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private final List<Product> products;

    public Basket() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct(int index) {
        return products.get(index);
    }

    public int getBasketSize() {
        return products.size();
    }
}
