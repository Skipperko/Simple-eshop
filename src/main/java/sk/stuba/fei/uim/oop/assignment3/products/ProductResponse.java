package sk.stuba.fei.uim.oop.assignment3.products;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private Long id;
    private Integer amount;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.amount = product.getAmount();
    }
}
