package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appwarehouse.domen.InputProduct;
import uz.pdp.appwarehouse.model.get_interface.GetInput;

import java.util.List;

public interface InputProductRepo extends JpaRepository<InputProduct,Long> {

    List<InputProduct> findByInputId(Long input_id);


//    @Query(value = "")
//    GetInput findById_AndInputId(Long inputId);
}
