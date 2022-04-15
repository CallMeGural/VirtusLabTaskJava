package internship.internship.receipt;

import internship.internship.basket.Basket;
import internship.internship.basket.BasketService;
import internship.internship.discount.TenPercentDiscount;
import internship.internship.discount.ThreeGrainProductsDiscount;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {
    BasketService basketService = new BasketService();
    public Receipt getReceipt() {
        var receiptGenerator = new ReceiptGenerator();
        var basket = new Basket();
        var discount1 = new TenPercentDiscount();
        var discount2 = new ThreeGrainProductsDiscount();
        basket = basketService.getBasket();
        var receipt = receiptGenerator.generate(basket);
        receipt = discount1.apply(receipt);
        receipt= discount2.apply(receipt);
        return receipt;
    }
}
