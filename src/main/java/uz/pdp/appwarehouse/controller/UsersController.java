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

    /**
     * Saves the User
     * @return UserDto
     */
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<UserDto>> save(@RequestBody UserDto dto) {
        return service.save(dto);
    }

    /**
     * Updates User
     * @param id
     * @return UserDto
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<UserDto>> update(@PathVariable(value = "id") Long id, @RequestBody UserDto dto) {
        return service.update(id, dto);
    }

    /**
     * Get a single list of the user
     * @param id
     * @return User
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<Users>> getUser(@PathVariable(value = "id") Long id) {
        return service.getUser(id);
    }

    /**
     * Get a user list
     * @param page
     * @param size
     * @return User
     */
    @GetMapping("/getList")
    public ResponseEntity<ApiResponse<List<Users>>> getUsersList(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                                 @RequestParam(value = "size", defaultValue = "5", required = false) int size) {
        return service.getUsersList(size, page);
    }

    /**
     * Delete a single user list
     * @param id
     * @return UserDto
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<UserDto>> delete(@PathVariable(value = "id") Long id) {
        return service.delete(id);
    }

}
