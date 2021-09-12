package uz.pdp.appwarehouse.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.appwarehouse.domen.Category;
import uz.pdp.appwarehouse.model.CategoryDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;

import java.util.List;

public interface CategoryService {
    ResponseEntity<ApiResponse<CategoryDto>> save(CategoryDto dto);

    ResponseEntity<ApiResponse<CategoryDto>> update(Long id, CategoryDto dto);

    ResponseEntity<ApiResponse<Category>> get(Long id);

    ResponseEntity<ApiResponse<List<Category>>> getList(Integer size, Integer page);

    ResponseEntity<ApiResponse<Boolean>> delete(Long id);
}
