package uz.pdp.appwarehouse.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.appwarehouse.domen.Warehouse;
import uz.pdp.appwarehouse.model.WarehouseDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;

import java.util.List;

public interface WarehouseService {
    ResponseEntity<ApiResponse<WarehouseDto>> save(WarehouseDto dto);

    ResponseEntity<ApiResponse<WarehouseDto>> update(Long id, WarehouseDto dto);

    ResponseEntity<ApiResponse<Warehouse>> get(Long id);

    ResponseEntity<ApiResponse<List<Warehouse>>> getList( int size, int page);

    ResponseEntity<ApiResponse<Boolean>> delete(Long id);
}
