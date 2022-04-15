package internship.internship.receipt;

import internship.internship.basket.Basket;
import internship.internship.product.Product;

import java.math.BigDecimal;
import java.util.*;

public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        List<ReceiptEntry> finalReceiptEntries = new ArrayList<>();
        int q;
        ReceiptEntry re;
        for(Product product : basket.getProducts()) {
            receiptEntries.add(new ReceiptEntry(product,1,product.price()));
        }
        HashMap<ReceiptEntry,Integer> hs = new HashMap<>();
        int i=0;
        while(i<receiptEntries.size()) {
            re = receiptEntries.get(i);
            if(hs.containsKey(re)) {
                hs.put(re,hs.get(re)+1);
                receiptEntries.remove(i);
            }
            else {
                hs.put(re,1);
                i++;
            }
        }
        Iterator it = hs.entrySet().iterator();
        while(it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            re = (ReceiptEntry) pair.getKey();
            q = (int) pair.getValue();
            finalReceiptEntries.add(new ReceiptEntry(re.product(),q,re.product().
                    price().multiply(BigDecimal.valueOf(q))));
            it.remove();
        }

        return new Receipt(finalReceiptEntries);
    }
}
