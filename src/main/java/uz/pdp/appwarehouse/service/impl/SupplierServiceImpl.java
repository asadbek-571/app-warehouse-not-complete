package uz.pdp.appwarehouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.common.MapstructMapper;
import uz.pdp.appwarehouse.domen.Currency;
import uz.pdp.appwarehouse.domen.Product;
import uz.pdp.appwarehouse.domen.Supplier;
import uz.pdp.appwarehouse.model.CurrencyDto;
import uz.pdp.appwarehouse.model.SupplierDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;
import uz.pdp.appwarehouse.repository.SupplierRepo;
import uz.pdp.appwarehouse.service.SupplierService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepo supplierRepo;
    private final MapstructMapper mapstructMapper;

    /**
     * Saves the Currency
     *
     * @return CurrencyDto
     */
    @Override
    public ResponseEntity<ApiResponse<SupplierDto>> save(SupplierDto dto) {
        Boolean byNameAndActiveTrue = supplierRepo.existsByNameAndActiveTrue(dto.getName());
        if (byNameAndActiveTrue)
            return new ResponseEntity<>(new ApiResponse<>("This supplier already exists"), HttpStatus.NOT_FOUND);

        Supplier supplier = new Supplier();
        SupplierServiceImpl service = new SupplierServiceImpl(supplierRepo,mapstructMapper);
        return service.setMeasurement(supplier, dto);
    }

    /**
     * Updates Supplier
     *
     * @param id
     * @return SupplierDto
     */
    @Override
    public ResponseEntity<ApiResponse<SupplierDto>> update(Long id, SupplierDto dto) {
        Optional<Supplier> optionalSupplier = supplierRepo.findById(id);
        if (!optionalSupplier.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Supplier not found"), HttpStatus.NOT_FOUND);


        Supplier supplier = optionalSupplier.get();
        SupplierServiceImpl service = new SupplierServiceImpl(supplierRepo,mapstructMapper);
        return service.setMeasurement(supplier, dto);
    }


    /**
     * Get a single list of the supplier
     *
     * @param id
     * @return Supplier
     */
    @Override
    public ResponseEntity<ApiResponse<Supplier>> get(Long id) {
        Optional<Supplier> optionalSupplier = supplierRepo.findByActiveTrueAndId(id);
        return optionalSupplier.map(supplier -> new ResponseEntity<>(new ApiResponse<>(supplier), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new ApiResponse<>("Supplier not found"), HttpStatus.NOT_FOUND));
    }

    /**
     * Get a supplier list
     *
     * @param size
     * @param page
     * @return Supplier
     */
    @Override
    public ResponseEntity<ApiResponse<List<Supplier>>> getList(Integer size, Integer page) {
        Optional<List<Supplier>> optionalSuppliers = supplierRepo.findAllByActiveTrue(size, page * size);
        return optionalSuppliers.map(supplier -> new ResponseEntity<>(new ApiResponse<>(supplier), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new ApiResponse<>("Supplier not found"), HttpStatus.NOT_FOUND));
    }

    /**
     * Delete a single supplier list
     *
     * @param id
     * @return SupplierDto
     */
    @Override
    public ResponseEntity<ApiResponse<SupplierDto>> delete(Long id) {
        Optional<Supplier> optionalSupplier = supplierRepo.findByActiveTrueAndId(id);
        if (!optionalSupplier.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Supplier not found"), HttpStatus.NOT_FOUND);
        Supplier supplier = optionalSupplier.get();
        supplier.setActive(false);
        SupplierDto dto = mapstructMapper.toSupplierDto(supplier);
        supplierRepo.save(supplier);
        return new ResponseEntity<>(new ApiResponse<>(dto), HttpStatus.OK);
    }

    private ResponseEntity<ApiResponse<SupplierDto>> setMeasurement(Supplier supplier, SupplierDto dto) {
        supplier.setActive(dto.isActive());
        supplier.setName(dto.getName());
        supplierRepo.save(supplier);
        return new ResponseEntity<>(new ApiResponse<>(dto), HttpStatus.OK);
    }

}
