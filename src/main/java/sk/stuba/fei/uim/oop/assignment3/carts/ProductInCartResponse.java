package sk.stuba.fei.uim.oop.assignment3.carts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductInCartResponse {
    private Long productId;
    private Integer amount;

}
