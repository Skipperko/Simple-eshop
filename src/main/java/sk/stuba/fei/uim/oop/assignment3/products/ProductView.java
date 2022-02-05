package sk.stuba.fei.uim.oop.assignment3.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductView {

    private Long id;
    private String name;
    private String description;
    private Integer amount;
    private String unit;
    private Integer price;

    public ProductView(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.amount = product.getAmount();
        this.unit = product.getUnit();
        this.price = product.getPrice();
    }
}
