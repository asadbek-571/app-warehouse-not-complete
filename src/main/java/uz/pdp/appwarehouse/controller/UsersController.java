package uz.pdp.appwarehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.domen.Users;
import uz.pdp.appwarehouse.model.UserDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;
import uz.pdp.appwarehouse.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UsersController {

    private final UserService service;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<UserDto>> save(@RequestBody UserDto dto) {
        return service.save(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<UserDto>> update(@PathVariable(value = "id") Long id, @RequestBody UserDto dto) {
        return service.update(id, dto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<Users>> getUser(@PathVariable(value = "id") Long id) {
        return service.getUser(id);
    }

    @GetMapping("/getList")
    public ResponseEntity<ApiResponse<List<Users>>> getUsersList(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                                 @RequestParam(value = "size", defaultValue = "5", required = false) int size) {
        return service.getUsersList(size, page);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<UserDto>> delete(@PathVariable(value = "id") Long id) {
        return service.delete(id);
    }

}
