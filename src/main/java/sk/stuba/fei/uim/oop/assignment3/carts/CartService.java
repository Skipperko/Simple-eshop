package sk.stuba.fei.uim.oop.assignment3.carts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import sk.stuba.fei.uim.oop.assignment3.Exceptions.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.Exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.products.Product;
import sk.stuba.fei.uim.oop.assignment3.products.ProductService;
import sk.stuba.fei.uim.oop.assignment3.products.ProductServiceInterface;

import java.util.Optional;

@Service
public class CartService implements CartServiceInterface{
    private CartRepository repository;
    private ProductServiceInterface productService;
    private ProductInCartRepository productInCartRepository;

    @Autowired
    public CartService(CartRepository repository, ProductServiceInterface productService, ProductInCartRepository productInCartRepository) {
        this.repository = repository;
        this.productService = productService;
        this.productInCartRepository = productInCartRepository;
    }

    @Override
    public Optional<Cart> getById(Long id) {
        return repository.findById(id);
    }

    public Cart addNewCart(){
        Cart cart = new Cart();
        return repository.save(cart);
    }

    public void deleteCart(Long id){
        repository.deleteById(id);
    }

    @Override
    public Cart addProductToCart(CartProductRequest request, Long cartId) {
        Product product = this.productService.getById(request.getProductId()).orElseThrow(NotFoundException::new);
        Cart cart = this.repository.findCartById(cartId);
        if(cart.isPayed()){
            throw new BadRequestException();
        }
        if(request.getAmount() > product.getAmount()){
            throw new BadRequestException();
        }
        ProductInCart productInCart = cart.getShoppingList()
                .stream()
                .filter((p) -> p.getProductId().equals(request.getProductId()))
                .findFirst()
                .orElseGet(() -> {
                    ProductInCart newProductInCart = new ProductInCart();
                    cart.getShoppingList().add(newProductInCart);
                    return newProductInCart;
                });


        product.setAmount(product.getAmount() - request.getAmount());
        this.productService.saveProduct(product);
        productInCart.setAmount(request.getAmount() + productInCart.getAmount());
        productInCart.setProductId(request.getProductId());
        productInCartRepository.save(productInCart);
        return this.repository.save(cart);
    }

    @Override
    public Cart payForCart(Long id) {
        Optional<Cart> maybeCart = this.repository.findById(id);
        if(maybeCart.isEmpty()){
            throw new NotFoundException();
        }
        Cart cart = maybeCart.get();
        if(cart.isPayed()){
           throw new BadRequestException();
        }
        cart.setPayed(true);
        return cart;
    }

    public Double getCartPrice(Long id){
        Double result = 0.0;
        Optional<Cart> maybeCart = this.repository.findById(id);
        if(maybeCart.isEmpty()){
            throw new NotFoundException();
        }
        Cart cart = maybeCart.get();
        for (ProductInCart productInCart: cart.getShoppingList()) {
            Long currentProductId = productInCart.getProductId();
            Product product = this.productService.getById(currentProductId).orElseThrow(NotFoundException::new);
            result += product.getPrice() * productInCart.getAmount();
        }
        return result;
    }
}
