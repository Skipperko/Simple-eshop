package sk.stuba.fei.uim.oop.assignment3.carts;

import sk.stuba.fei.uim.oop.assignment3.products.Product;

import java.util.Optional;

public interface CartServiceInterface {

    Optional<Cart> getById(Long id);
    Cart addNewCart();
    void deleteCart(Long id);
    Cart addProductToCart(CartProductRequest product, Long cartId);
    Cart payForCart(Long id);
    Double getCartPrice(Long id);
}
