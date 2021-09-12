package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appwarehouse.domen.Users;


import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<Users,Long> {

    Optional<Users> findByPhoneNumber(String phoneNumber);

    @Query(value = " SELECT * " +
            " FROM users t " +
            " WHERE t.active=true LIMIT :size offset :page",nativeQuery = true)
    Optional<List<Users>> findAllByActiveTrue(Integer size, Integer page);

    Optional<List<Users>> findAllByActiveTrue();

    Optional<Users> findByActiveTrueAndId(Long id);

}
