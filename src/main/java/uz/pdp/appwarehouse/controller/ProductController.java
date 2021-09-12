package uz.pdp.appwarehouse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.domen.Currency;
import uz.pdp.appwarehouse.domen.Product;
import uz.pdp.appwarehouse.model.CurrencyDto;
import uz.pdp.appwarehouse.model.ProductDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;
import uz.pdp.appwarehouse.service.CurrencyService;
import uz.pdp.appwarehouse.service.ProductService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService service;


    @PostMapping(value = "/save", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ApiResponse<ProductDto>> save(@RequestParam("dto") String dto,
                                                        @RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {
        ProductController controller = new ProductController(service);
        ProductDto productDto = controller.getJson(dto);
        return service.save(productDto, multipartFile);
    }

    private ProductDto getJson(String json) {
        ProductDto dto = new ProductDto();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            dto = objectMapper.readValue(json, ProductDto.class);
            return dto;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> update(@PathVariable(value = "id") Long id, @RequestParam("dto") String dto,
                                                          @RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {
        ProductController controller = new ProductController(service);
        ProductDto productDto = controller.getJson(dto);
        return service.update(id, productDto, multipartFile);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<Product>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @GetMapping("/getList")
    public ResponseEntity<ApiResponse<List<Product>>> getList(@RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
                                                              @RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
        return service.getList(size,page);
    }

    @DeleteMapping("/get/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> delete(@PathVariable(value = "id") Long id) {
        return service.delete(id);
    }
}
