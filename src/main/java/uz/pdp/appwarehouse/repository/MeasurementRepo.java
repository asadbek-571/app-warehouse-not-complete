package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appwarehouse.domen.Measurement;
import uz.pdp.appwarehouse.domen.Users;

import java.util.List;
import java.util.Optional;

public interface MeasurementRepo extends JpaRepository<Measurement,Long> {



//    @Query(value = " SELECT * " +
//            " FROM measurement t " +
//            " WHERE t.active=true LIMIT :size offset :page",nativeQuery = true)
//    Optional<List<Measurement>> findAllByActiveTrue(Integer size, Integer page);

    Optional<List<Measurement>> findAllByActiveTrue();

    Optional<Measurement> findByActiveTrueAndId(Long id);

    Boolean existsByNameAndActiveTrue(String name);

}
