package uz.pdp.appwarehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.AliasFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.domen.Warehouse;
import uz.pdp.appwarehouse.model.WarehouseDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;
import uz.pdp.appwarehouse.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService service;

    /**
     * Saves the Warehouse
     * @return WarehouseDto
     */
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<WarehouseDto>> save(@RequestBody WarehouseDto dto){
        return service.save(dto);
    }

    /**
     * Updates Warehouse
     * @param id
     * @return WarehouseDto
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<WarehouseDto>> update(@PathVariable(value = "id")Long id,@RequestBody WarehouseDto dto){
        return service.update(id,dto);
    }

    /**
     * Get a single list of the warehouse
     * @param id
     * @return Warehouse
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<Warehouse>> get(@PathVariable(value = "id") Long id){
        return service.get(id);
    }

    /**
     * Get a warehouse list
     * @param page
     * @param size
     * @return Warehouse
     */

    @GetMapping("/getList")
    public ResponseEntity<ApiResponse<List<Warehouse>>> getPage(@RequestParam(value = "page",defaultValue = "0",required = false) Integer page,
                                                                @RequestParam(value = "size",defaultValue = "5",required = false) Integer size){
        return service.getList(size,page);
    }

    /**
     * Delete a single warehouse list
     * @param id
     * @return Boolean
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable(value = "id")Long id){
        return service.delete(id);
    }


}
