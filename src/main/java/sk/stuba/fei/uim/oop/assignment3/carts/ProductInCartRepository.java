package sk.stuba.fei.uim.oop.assignment3.carts;

import org.springframework.data.repository.CrudRepository;
import sk.stuba.fei.uim.oop.assignment3.products.Product;

public interface ProductInCartRepository extends CrudRepository<ProductInCart, Long> {

}
