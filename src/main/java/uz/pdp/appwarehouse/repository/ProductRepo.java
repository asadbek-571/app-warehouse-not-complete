package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appwarehouse.domen.Currency;
import uz.pdp.appwarehouse.domen.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product,Long> {


    @Query(value = " SELECT * " +
            " FROM product t " +
            " WHERE t.active=true LIMIT :size offset :page",nativeQuery = true)
    Optional<List<Product>> findAllByActiveTrue(Integer size, Integer page);



    Optional<List<Product>> findAllByActiveTrue();


    Optional<Product> findByActiveTrueAndId(Long id);


    Boolean existsByNameAndActiveTrue(String name);

}
