package sk.stuba.fei.uim.oop.assignment3.carts;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, Long> {
    //void deleteCartById(Long id);
    Cart findCartById(Long id);

}
