package sk.stuba.fei.uim.oop.assignment3.products;

import java.util.List;
import java.util.Optional;

public interface ProductServiceInterface {
    Product getProductById(Long id);
    Optional<Product> getById(Long id);
    List<Product> getAllProducts();
    Product addNewProduct(ProductView productView);
    Product addProductAmount(Long id, Integer value);
    int getProductAmount(Long id);
    void deleteProduct(Long id);
    Optional<Product> updateProduct(Long id, String name, String description);
    void saveProduct(Product product);
}
