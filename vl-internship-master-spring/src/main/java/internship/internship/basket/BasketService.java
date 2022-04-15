package internship.internship.basket;

import internship.internship.product.ProductDb;
import org.springframework.stereotype.Service;

@Service
public class BasketService {

    public Basket getBasket() {
        Basket basket = new Basket();
        var productDb = new ProductDb();
        var cheese = productDb.getProduct("Cheese");
        var bread = productDb.getProduct("Bread");
        var apple = productDb.getProduct("Apple");
        var milk = productDb.getProduct("Milk");
        var steak = productDb.getProduct("Steak");
        basket.addProduct(cheese);
        basket.addProduct(bread);
        basket.addProduct(bread);
        basket.addProduct(bread);
        basket.addProduct(apple);
        basket.addProduct(apple);
        basket.addProduct(milk);
        basket.addProduct(steak);
        basket.addProduct(steak);
        basket.addProduct(steak);
        basket.addProduct(steak);
        return basket;
    }
}
