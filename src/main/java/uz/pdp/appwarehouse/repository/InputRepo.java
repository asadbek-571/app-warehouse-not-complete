package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appwarehouse.domen.Input;
import uz.pdp.appwarehouse.model.get_interface.GetInput;

import java.util.List;
import java.util.Optional;

public interface InputRepo extends JpaRepository<Input, Long> {

    @Query(value = " SELECT * " +
            " FROM input t " +
            " LIMIT :size offset :page", nativeQuery = true)
    Optional<List<Input>> findAllByActiveTrue(Integer size, Integer page);


//    @Query(value = " SELECT t.id, code, date, facture_number, currency_id, supplier_id, warehouse_id,ip.id, amount, expire_date, price, input_id, product_id FROM input t " +
//            "        LEFT JOIN input_product ip on t.id = ip.input_id " +
//            "        WHERE t.id=:inputId AND  ip.input_id=:inputId ", nativeQuery = true)
//    List<GetInput> findById_AndInputId(Long inputId);


}
