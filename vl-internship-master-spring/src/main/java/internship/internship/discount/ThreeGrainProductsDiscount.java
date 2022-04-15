package internship.internship.discount;

import internship.internship.product.Product;
import internship.internship.receipt.Receipt;
import internship.internship.receipt.ReceiptEntry;

import java.math.BigDecimal;

public class ThreeGrainProductsDiscount {
    public static String NAME = "ThreeGrainDiscount";

    public Receipt apply(Receipt receipt) {
        if (shouldApply(receipt)) {
            var totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.85));
            var discounts = receipt.discounts();
            discounts.add(0, NAME);
            return new Receipt(receipt.entries(), discounts, totalPrice);
        }
        return receipt;
    }

    private boolean shouldApply(Receipt receipt) {
        int counter=0;
        for(ReceiptEntry product : receipt.entries()) {
            if (product.product().type() == Product.Type.GRAINS) {
                counter += product.quantity();
            }
        }
        if(counter>2) return true;
        return false;
    }
}
