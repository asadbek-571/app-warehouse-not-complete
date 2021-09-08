package uz.pdp.appwarehouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.common.MapstructMapper;
import uz.pdp.appwarehouse.domen.Warehouse;
import uz.pdp.appwarehouse.model.WarehouseDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;
import uz.pdp.appwarehouse.service.WarehouseService;
import uz.pdp.appwarehouse.repository.WarehouseRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepo warehouseRepo;

    private final MapstructMapper mapstructMapper;

    /**
     * Saves the Warehouse
     *
     * @return WarehouseDto
     */
    @Override
    public ResponseEntity<ApiResponse<WarehouseDto>> save(WarehouseDto dto) {
        Warehouse warehouse = mapstructMapper.toWarehouse(dto);
        warehouseRepo.save(warehouse);
        return new ResponseEntity<>(new ApiResponse<>(dto), HttpStatus.CREATED);
    }

    /**
     * Updates Warehouse
     *
     * @param id
     * @return WarehouseDto
     */
    @Override
    public ResponseEntity<ApiResponse<WarehouseDto>> update(Long id, WarehouseDto dto) {

        Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(id);
        if (!optionalWarehouse.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Warehouse not found"), HttpStatus.NOT_FOUND);
        Warehouse warehouse = optionalWarehouse.get();
        warehouse.setActive(dto.isActive());
        warehouse.setName(dto.getName());
        warehouseRepo.save(warehouse);
        return new ResponseEntity<>(new ApiResponse<>(dto), HttpStatus.OK);
    }

    /**
     * Get a single list of the warehouse
     *
     * @param id
     * @return Warehouse
     */
    @Override
    public ResponseEntity<ApiResponse<Warehouse>> get(Long id) {

        Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(id);
        return optionalWarehouse.map(warehouse -> new ResponseEntity<>(new ApiResponse<>(warehouse), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new ApiResponse<>("Warehouse not found"), HttpStatus.NOT_FOUND));
    }

    /**
     * Get a warehouse list
     *
     * @param page
     * @param size
     * @return Warehouse
     */
    @Override
    public ResponseEntity<ApiResponse<List<Warehouse>>> getList(int size, int page) {
        Optional<List<Warehouse>> optionalWarehouses = warehouseRepo.findAllByActiveTrue(size, size * page);
        return optionalWarehouses.map(warehouses -> new ResponseEntity<>(new ApiResponse<>(warehouses), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new ApiResponse<>("Warehouse not found"), HttpStatus.NOT_FOUND));
    }

    /**
     * Delete warehouse list
     *
     * @param id
     * @return WarehouseDto
     */
    @Override
    public ResponseEntity<ApiResponse<Boolean>> delete(Long id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(id);
        if (!optionalWarehouse.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Warehouse not found"), HttpStatus.NOT_FOUND);
        Warehouse warehouse = optionalWarehouse.get();
        warehouse.setActive(false);
        warehouseRepo.save(warehouse);
        return new ResponseEntity<>(new ApiResponse<>(true), HttpStatus.OK);
    }
}
