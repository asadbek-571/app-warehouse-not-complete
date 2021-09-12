package uz.pdp.appwarehouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.domen.*;
import uz.pdp.appwarehouse.helpers.Utils;
import uz.pdp.appwarehouse.model.InputDto;
import uz.pdp.appwarehouse.model.InputProductDto;
import uz.pdp.appwarehouse.model.InputResDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;
import uz.pdp.appwarehouse.repository.*;
import uz.pdp.appwarehouse.service.InputService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InputServiceImpl implements InputService {

    private final WarehouseRepo warehouseRepo;
    private final SupplierRepo supplierRepo;
    private final CurrencyRepo currencyRepo;
    private final ProductRepo productRepo;
    private final InputRepo inputRepo;
    private final InputProductRepo inputProductRepo;


    @Override
    public ResponseEntity<ApiResponse<InputResDto>> save(InputDto dto, InputProductDto inputProductDto) {

        Input input = new Input();
        input.setCode(UUID.randomUUID().toString());

        InputProduct inputProduct = new InputProduct();

        InputServiceImpl inputService = new InputServiceImpl(warehouseRepo, supplierRepo, currencyRepo, productRepo, inputRepo, inputProductRepo);
        return inputService.response(input, inputProductDto, dto, inputProduct);
    }

    @Override
    public ResponseEntity<ApiResponse<InputResDto>> update(Long id, InputDto dto, InputProductDto inputProductDto) {

        Optional<Input> optionalInput = inputRepo.findById(id);
        if (!optionalInput.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Input not found"), HttpStatus.NOT_FOUND);
        Input input = optionalInput.get();
        InputServiceImpl inputService = new InputServiceImpl(warehouseRepo, supplierRepo, currencyRepo, productRepo, inputRepo, inputProductRepo);
        List<InputProduct> productList = inputProductRepo.findByInputId(id);
        for (InputProduct inputProduct : productList) {

            return inputService.response(input, inputProductDto, dto, inputProduct);
        }
        return null;
    }

    private ResponseEntity<ApiResponse<InputResDto>> response(Input input,
                                                              InputProductDto inputProductDto,
                                                              InputDto dto, InputProduct inputProduct) {

        Warehouse warehouse = warehouseRepo.findByActiveTrueAndId(dto.getWarehouseId());
        Optional<Supplier> optionalSupplier = supplierRepo.findByActiveTrueAndId(dto.getSupplierId());
        Optional<Currency> optionalCurrency = currencyRepo.findByActiveTrueAndId(dto.getCurrencyId());
        Optional<Product> optionalProduct = productRepo.findByActiveTrueAndId(inputProductDto.getProductId());

        if (Utils.isEmpty(warehouse))
            return new ResponseEntity<>(new ApiResponse<>("Warehouse not found"), HttpStatus.NOT_FOUND);

        if (!optionalSupplier.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Supplier not found"), HttpStatus.NOT_FOUND);

        if (!optionalCurrency.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Currency not found"), HttpStatus.NOT_FOUND);

        if (!optionalProduct.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Product not found"), HttpStatus.NOT_FOUND);

        input.setWarehouse(warehouse);
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        input.setDate(Timestamp.valueOf(dto.getDate()));
        input.setFactureNumber(dto.getFactureNumber());
        Input save = inputRepo.save(input);


        inputProduct.setInput(save);
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());

        inputProduct.setExpireDate(Date.valueOf(inputProductDto.getExpireDate()));
        inputProductRepo.save(inputProduct);
        return new ResponseEntity<>(new ApiResponse<>(new InputResDto(dto, inputProductDto)), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ApiResponse<List<InputProduct>>> get(Long id) {
        List<InputProduct> productList = inputProductRepo.findByInputId(id);
        if (productList.isEmpty())
            return new ResponseEntity<>(new ApiResponse<>("Input not found"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new ApiResponse<>(productList), HttpStatus.NOT_FOUND);

    }

    @Override
    public ResponseEntity<ApiResponse<List<Input>>> getList(int size, int page) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<InputDto>> delete(Long id) {
        return null;
    }
}
