package sk.stuba.fei.uim.oop.assignment3.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.Exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Component
public class ProductController {

    @Autowired
    private ProductServiceInterface service;

    @GetMapping(value = "/product")
    public List<ProductView> getAllProducts(){
        return service.getAllProducts().stream().map(ProductView::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/product/{id}")
    public ProductView getProductById(@PathVariable("id") Long id){
        return new ProductView(this.service.getById(id).orElseThrow(NotFoundException::new));
    }

    @PostMapping(value = "/product")
    public ResponseEntity<ProductView> addNewProduct(@RequestBody ProductView productView){
        return new ResponseEntity<>(new ProductView(service.addNewProduct(productView)), HttpStatus.CREATED);
    }

    @GetMapping(value = "/product/{id}/amount")
    public AmountView getProductAmount(@PathVariable("id") Long id){
        return new AmountView(this.service.getById(id).orElseThrow(NotFoundException::new));
    }

    @PostMapping(value = "/product/{id}/amount")
    public AmountView addProductAmount(@PathVariable("id") Long id,@RequestBody AmountViewRequest request){
        if(this.service.getById(id).isPresent()) {
            return new AmountView(service.addProductAmount(id, request.getAmount()));
        }
        else {
            throw new NotFoundException();
        }
    }

    @DeleteMapping(value = "/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id){
        if(this.service.getById(id).isPresent()) {
            service.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            throw new NotFoundException();
        }
    }

    @PutMapping(value = "/product/{id}")
    public ProductView updateProduct(@PathVariable("id") Long id, @RequestBody UpdateRequest request){
            return new ProductView(service.updateProduct(id,request.getName(), request.getDescription()).orElseThrow(NotFoundException::new));
        }



}
