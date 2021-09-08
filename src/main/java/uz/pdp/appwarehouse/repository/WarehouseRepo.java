package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appwarehouse.domen.Warehouse;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface WarehouseRepo extends JpaRepository<Warehouse,Long> {


    @Query(value = " SELECT * " +
            " FROM warehouse t " +
            " WHERE t.active=true LIMIT :size offset :page",nativeQuery = true)
    Optional<List<Warehouse>> findAllByActiveTrue(Integer size,Integer page);

    Optional<Set<Warehouse>> findAllByActiveTrueAndIdIn(Set<Long> id);

}
