package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appwarehouse.domen.Category;
import uz.pdp.appwarehouse.domen.Users;

import java.util.List;
import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category,Long> {


    @Query(value = " SELECT * " +
            " FROM category t " +
            " WHERE t.active=true LIMIT :size offset :page",nativeQuery = true)
    Optional<List<Category>> findAllByActiveTrue(Integer size, Integer page);



    Optional<List<Category>> findAllByActiveTrue();


    Optional<Category> findByActiveTrueAndId(Long id);


    Boolean existsByNameAndActiveTrue(String name);

}
