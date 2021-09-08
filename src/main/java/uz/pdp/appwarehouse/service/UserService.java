package uz.pdp.appwarehouse.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.appwarehouse.domen.Users;
import uz.pdp.appwarehouse.model.UserDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;

import java.util.List;

public interface UserService {
    ResponseEntity<ApiResponse<UserDto>> save(UserDto dto);

    ResponseEntity<ApiResponse<UserDto>> update(Long id, UserDto dto);

    ResponseEntity<ApiResponse<Users>> getUser(Long id);

    ResponseEntity<ApiResponse<List<Users>>> getUsersList(int size, int page);

    ResponseEntity<ApiResponse<UserDto>> delete(Long id);
}

