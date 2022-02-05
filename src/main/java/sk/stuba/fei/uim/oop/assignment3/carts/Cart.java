package sk.stuba.fei.uim.oop.assignment3.carts;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.products.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany
    private List<ProductInCart> shoppingList;
    private boolean payed;

    public Cart() {
        this.payed = false;
        this.shoppingList = new ArrayList<>();
    }
}
