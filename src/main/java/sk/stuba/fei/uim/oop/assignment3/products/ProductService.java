package sk.stuba.fei.uim.oop.assignment3.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@Component
public class ProductService implements ProductServiceInterface{

    //private Database database;
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(Long id){
        return this.productRepository.findProductById(id);
    }

    @Override
    public Optional<Product> getById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts(){

        return this.productRepository.findAll();
    }

    @Override
    public Product addNewProduct(ProductView productView){
        Product product = new Product();
        product.setPrice(productView.getPrice());
        product.setAmount(productView.getAmount());
        product.setDescription(productView.getDescription());
        product.setName(productView.getName());
        product.setUnit(productView.getUnit());
        return productRepository.save(product);
    }

    @Override
    public Product addProductAmount(Long id, Integer value){
        if(value != null) {
            Product product = productRepository.findProductById(id);
            product.setAmount(product.getAmount()+value);
            return this.productRepository.save(product);
        }
        return  productRepository.findProductById(id);
    }

    @Override
    public int getProductAmount(Long id){
        return productRepository.findProductById(id).getAmount();
    }

    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public Optional<Product> updateProduct(Long id, String name, String description){
        if(name != null){
            productRepository.findById(id).get().setName(name);
        }
        if(description != null){
            productRepository.findById(id).get().setDescription(description);
        }
        return productRepository.findById(id);
    }

    @Override
    public void saveProduct(Product product) {
        this.productRepository.save(product);
    }
}
