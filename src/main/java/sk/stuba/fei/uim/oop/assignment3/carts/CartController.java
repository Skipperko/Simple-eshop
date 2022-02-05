package sk.stuba.fei.uim.oop.assignment3.carts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.Exceptions.NotFoundException;

@RestController
@Component
public class CartController {
    @Autowired
    private CartServiceInterface service;

    @GetMapping(value = "/cart/{id}")
    public CartView getCartById(@PathVariable("id") Long id){
        Cart cartView = this.service.getById(id).orElseThrow(NotFoundException::new);
        return new CartView(cartView);
    }

    @PostMapping(value = "/cart")
    public ResponseEntity<CartView> createCart(){
        return new ResponseEntity<>(new CartView(this.service.addNewCart()), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/cart/{id}")
    public ResponseEntity<String> deleteCartById(@PathVariable("id") Long id){
        if(this.service.getById(id).isPresent()){
            this.service.deleteCart(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            throw new NotFoundException();
        }
    }

    @PostMapping(value = "/cart/{id}/add")
    public CartView addToCart(@PathVariable("id") Long id, @RequestBody CartProductRequest request){
        if(this.service.getById(id).isPresent()) {
            return new CartView(this.service.addProductToCart(request, id));
        }
        throw new NotFoundException();
    }

    @GetMapping(value = "/cart/{id}/pay")
    public ResponseEntity<String> payForCart(@PathVariable("id") Long id){
        if(this.service.getById(id).isPresent()){
            this.service.payForCart(id);
            return new ResponseEntity<>(this.service.getCartPrice(id).toString(),HttpStatus.OK);
        }
        throw new NotFoundException();
    }
}
