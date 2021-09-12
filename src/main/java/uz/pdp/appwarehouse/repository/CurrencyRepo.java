package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appwarehouse.domen.Category;
import uz.pdp.appwarehouse.domen.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyRepo extends JpaRepository<Currency,Long> {


    @Query(value = " SELECT * " +
            " FROM currency t " +
            " WHERE t.active=true LIMIT :size offset :page",nativeQuery = true)
    Optional<List<Currency>> findAllByActiveTrue(Integer size, Integer page);



    Optional<List<Currency>> findAllByActiveTrue();


    Optional<Currency> findByActiveTrueAndId(Long id);


    Boolean existsByNameAndActiveTrue(String name);

}
