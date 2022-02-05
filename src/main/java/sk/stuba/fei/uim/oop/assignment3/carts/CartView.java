package sk.stuba.fei.uim.oop.assignment3.carts;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.products.Product;
import sk.stuba.fei.uim.oop.assignment3.products.ProductResponse;
import sk.stuba.fei.uim.oop.assignment3.products.ProductView;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CartView {
    @Id
    @GeneratedValue
    private Long id;
    private List<ProductInCartResponse> shoppingList;
    private boolean payed;

    public CartView(Cart cart) {
        this.id = cart.getId();
        this.shoppingList = new ArrayList<>();
        if(!cart.getShoppingList().isEmpty()) {
            for (ProductInCart product : cart.getShoppingList()) {
                this.shoppingList.add(new ProductInCartResponse(product.getProductId(), product.getAmount()));
            }
        }
        this.payed = cart.isPayed();
    }
}
