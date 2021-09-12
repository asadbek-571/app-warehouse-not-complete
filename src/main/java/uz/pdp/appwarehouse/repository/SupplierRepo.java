package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appwarehouse.domen.Currency;
import uz.pdp.appwarehouse.domen.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierRepo extends JpaRepository<Supplier,Long> {


    @Query(value = " SELECT * " +
            " FROM supplier t " +
            " WHERE t.active=true LIMIT :size offset :page",nativeQuery = true)
    Optional<List<Supplier>> findAllByActiveTrue(Integer size, Integer page);



    Optional<List<Supplier>> findAllByActiveTrue();


    Optional<Supplier> findByActiveTrueAndId(Long id);


    Boolean existsByNameAndActiveTrue(String name);

}
