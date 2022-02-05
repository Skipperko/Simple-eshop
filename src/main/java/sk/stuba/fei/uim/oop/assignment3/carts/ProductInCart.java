package sk.stuba.fei.uim.oop.assignment3.carts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class ProductInCart {
    private Long productId;
    private Integer amount = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
