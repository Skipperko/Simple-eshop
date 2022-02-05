package sk.stuba.fei.uim.oop.assignment3.products;

import lombok.Getter;
import lombok.Setter;

@Getter
public class AmountView {
    private Integer amount;

    public AmountView(Product product) {
        this.amount = product.getAmount();
    }
}
