package uz.pdp.appwarehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.domen.Category;
import uz.pdp.appwarehouse.domen.Supplier;
import uz.pdp.appwarehouse.model.CategoryDto;
import uz.pdp.appwarehouse.model.SupplierDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;
import uz.pdp.appwarehouse.service.CategoryService;
import uz.pdp.appwarehouse.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService service;

    /**
     * Saves the Supplier
     * @return SupplierDto
     */
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<SupplierDto>> save(@RequestBody SupplierDto dto){
        return service.save(dto);
    }

    /**
     * Updates Supplier
     * @param id
     * @return SupplierDto
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<SupplierDto>> update(@PathVariable(value = "id")Long id,@RequestBody SupplierDto dto){
        return service.update(id,dto);
    }

    /**
     * Get a single list of the Supplier
     * @param id
     * @return Supplier
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<Supplier>> get(@PathVariable(value = "id") Long id){
        return service.get(id);
    }

    /**
     * Get a Supplier list
     * @param page
     * @param size
     * @return Supplier
     */
    @GetMapping("/getList")
    public ResponseEntity<ApiResponse<List<Supplier>>> getList(@RequestParam(value = "page",defaultValue = "0",required = false) Integer page,
                                                               @RequestParam(value = "size",defaultValue = "5",required = false) Integer size){
        return service.getList(size,page);
    }

    /**
     * Delete a single Supplier list
     * @param id
     * @return SupplierDto
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<SupplierDto>> delete(@PathVariable(value = "id")Long id){
        return service.delete(id);
    }


}
