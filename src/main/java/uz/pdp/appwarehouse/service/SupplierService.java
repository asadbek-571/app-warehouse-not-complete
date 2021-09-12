package uz.pdp.appwarehouse.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.appwarehouse.domen.Supplier;
import uz.pdp.appwarehouse.model.SupplierDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;

import java.util.List;

public interface SupplierService {
    ResponseEntity<ApiResponse<SupplierDto>> delete(Long id);

    ResponseEntity<ApiResponse<List<Supplier>>> getList(Integer size, Integer page);

    ResponseEntity<ApiResponse<SupplierDto>> update(Long id, SupplierDto dto);

    ResponseEntity<ApiResponse<Supplier>> get(Long id);

    ResponseEntity<ApiResponse<SupplierDto>> save(SupplierDto dto);
}
