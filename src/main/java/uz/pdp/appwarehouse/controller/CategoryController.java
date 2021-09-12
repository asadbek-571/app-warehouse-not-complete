package uz.pdp.appwarehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.domen.Category;
import uz.pdp.appwarehouse.domen.Warehouse;
import uz.pdp.appwarehouse.model.CategoryDto;
import uz.pdp.appwarehouse.model.WarehouseDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;
import uz.pdp.appwarehouse.service.CategoryService;
import uz.pdp.appwarehouse.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    /**
     * Saves the Category
     * @return CategoryDto
     */
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<CategoryDto>> save(@RequestBody CategoryDto dto){
        return service.save(dto);
    }

    /**
     * Updates Category
     * @param id
     * @return CategoryDto
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> update(@PathVariable(value = "id")Long id,@RequestBody CategoryDto dto){
        return service.update(id,dto);
    }

    /**
     * Get a single list of the category
     * @param id
     * @return Category
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<Category>> get(@PathVariable(value = "id") Long id){
        return service.get(id);
    }

    /**
     * Get a category list
     * @param page
     * @param size
     * @return Category
     */
    @GetMapping("/getList")
    public ResponseEntity<ApiResponse<List<Category>>> getPage(@RequestParam(value = "page",defaultValue = "0",required = false) Integer page,
                                                               @RequestParam(value = "size",defaultValue = "5",required = false) Integer size){
        return service.getList(size,page);
    }

    /**
     * Delete a single category list
     * @param id
     * @return Boolean
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable(value = "id")Long id){
        return service.delete(id);
    }


}
