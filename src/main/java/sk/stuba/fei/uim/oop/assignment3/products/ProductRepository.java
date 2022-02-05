package sk.stuba.fei.uim.oop.assignment3.products;

import org.springframework.data.repository.CrudRepository;
import sk.stuba.fei.uim.oop.assignment3.carts.Cart;
import sk.stuba.fei.uim.oop.assignment3.products.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findProductById(Long id);
//    @Override
//    void deleteById(Long id);
    @Override
    List<Product> findAll();


}
