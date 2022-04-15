package internship.virtuslab.internship.discount;

import internship.internship.discount.TenPercentDiscount;
import internship.internship.discount.ThreeGrainProductsDiscount;
import internship.internship.product.ProductDb;
import internship.internship.receipt.Receipt;
import internship.internship.receipt.ReceiptEntry;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BothDiscountsTest {
    @Test
    void shouldApplyBothDiscounts() {
        // Given
        var productDb = new ProductDb();
        var cheese = productDb.getProduct("Cheese");
        var bread = productDb.getProduct("Bread");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cheese, 15));
        receiptEntries.add(new ReceiptEntry(bread, 3));

        var receipt = new Receipt(receiptEntries);
        var discount1 = new ThreeGrainProductsDiscount();
        var discount2 = new TenPercentDiscount();
        var expectedTotalPrice =
                cheese.price().multiply(BigDecimal.valueOf(15)).
                        add(bread.price().multiply(BigDecimal.valueOf(3))).
                        multiply(BigDecimal.valueOf(0.85));
        expectedTotalPrice=expectedTotalPrice.multiply(BigDecimal.valueOf(0.90));
        // When
        var receiptAfterDiscount = discount1.apply(receipt);
        receiptAfterDiscount = discount2.apply(receiptAfterDiscount);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(2, receiptAfterDiscount.discounts().size());
    }
    @Test
    void shouldNotApply10PercentDiscountEvenIfTheBasicPriceIsMoreThan50() {
        // Given
        var productDb = new ProductDb();
        var cheese = productDb.getProduct("Cheese");
        var bread = productDb.getProduct("Bread");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(cheese, 2));
        receiptEntries.add(new ReceiptEntry(bread, 3));

        var receipt = new Receipt(receiptEntries);
        var discount1 = new ThreeGrainProductsDiscount();
        var discount2 = new TenPercentDiscount();
        var expectedTotalPrice =
                cheese.price().multiply(BigDecimal.valueOf(2)).
                add(bread.price().multiply(BigDecimal.valueOf(3))).
                multiply(BigDecimal.valueOf(0.85));
        // When
        var receiptAfterDiscount = discount1.apply(receipt);
        receiptAfterDiscount = discount2.apply(receiptAfterDiscount);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(1, receiptAfterDiscount.discounts().size());
    }
}
