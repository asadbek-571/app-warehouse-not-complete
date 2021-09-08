package uz.pdp.appwarehouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.common.MapstructMapper;
import uz.pdp.appwarehouse.domen.Users;
import uz.pdp.appwarehouse.domen.Warehouse;
import uz.pdp.appwarehouse.model.UserDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;
import uz.pdp.appwarehouse.repository.UserRepo;
import uz.pdp.appwarehouse.repository.WarehouseRepo;
import uz.pdp.appwarehouse.service.UserService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final WarehouseRepo warehouseRepo;
    private final MapstructMapper mapstructMapper;

    @Override
    public ResponseEntity<ApiResponse<UserDto>> save(UserDto dto) {
        Optional<Set<Warehouse>> warehouseList = warehouseRepo.findAllByActiveTrueAndIdIn(dto.getWarehousesId());
        if (!warehouseList.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Warehouse not found"), HttpStatus.NOT_FOUND);

        Optional<Users> optionalUsers = userRepo.findByPhoneNumber(dto.getPhoneNumber());
        if (optionalUsers.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("There is such a phone number"), HttpStatus.BAD_REQUEST);

        Users users = new Users();

        Long userCode = System.currentTimeMillis();

        return toUser(dto, warehouseList, users, userCode);

    }

    @Override
    public ResponseEntity<ApiResponse<UserDto>> update(Long id, UserDto dto) {
        Optional<Users> optionalUsers = userRepo.findById(id);
        if (!optionalUsers.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("User not found"), HttpStatus.NOT_FOUND);
        Optional<Set<Warehouse>> warehouseList = warehouseRepo.findAllByActiveTrueAndIdIn(dto.getWarehousesId());
        if (!warehouseList.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Warehouse not found"), HttpStatus.NOT_FOUND);

        Optional<Users> findByPhoneNumber = userRepo.findByPhoneNumber(dto.getPhoneNumber());
        if (findByPhoneNumber.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("There is such a phone number"), HttpStatus.BAD_REQUEST);
        Users users = optionalUsers.get();

        Long userCode = System.currentTimeMillis();
        return toUser(dto, warehouseList, users, userCode);

    }

    private ResponseEntity<ApiResponse<UserDto>> toUser(UserDto dto, Optional<Set<Warehouse>> warehouseList, Users users, Long userCode) {
        users.setFirstName(dto.getFirstName());
        users.setLastName(dto.getLastName());
        users.setPhoneNumber(dto.getPhoneNumber());
        users.setCode(userCode);
        users.setPassword(dto.getPassword());
        users.setActive(dto.isActive());
        users.setWarehouses(warehouseList.get());
        userRepo.save(users);
        return new ResponseEntity<>(new ApiResponse<>(dto), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ApiResponse<Users>> getUser(Long id) {
        Optional<Users> optionalUsers = userRepo.findById(id);
        return optionalUsers.map(users -> new ResponseEntity<>(new ApiResponse<>(users), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new ApiResponse<>("User not found"), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ApiResponse<List<Users>>> getUsersList(int size, int page) {
        Optional<List<Users>> optionalUsers = userRepo.findAllByActiveTrue(size, size * page);
        return optionalUsers.map(users -> new ResponseEntity<>(new ApiResponse<>(users), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new ApiResponse<>("Users not found"), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ApiResponse<UserDto>> delete(Long id) {
        Optional<Users> optionalUsers = userRepo.findById(id);
        if (!optionalUsers.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("User not found"), HttpStatus.NOT_FOUND);
        Users users = optionalUsers.get();
        users.setActive(false);
        userRepo.save(users);
        UserDto userDto = mapstructMapper.toUserDto(users);
        return new ResponseEntity<>(new ApiResponse<>(userDto), HttpStatus.OK);
    }

}
