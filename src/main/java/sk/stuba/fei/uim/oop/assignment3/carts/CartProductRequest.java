package sk.stuba.fei.uim.oop.assignment3.carts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartProductRequest {
    private Long productId;
    private Integer amount;
}
