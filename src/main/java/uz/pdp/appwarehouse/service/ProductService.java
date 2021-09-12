package uz.pdp.appwarehouse.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.domen.Product;
import uz.pdp.appwarehouse.model.ProductDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    ResponseEntity<ApiResponse<ProductDto>> update(Long id, ProductDto dto, MultipartFile request);

    ResponseEntity<ApiResponse<ProductDto>> save(ProductDto dto, MultipartFile multipartFile ) throws IOException;

    ResponseEntity<ApiResponse<Product>> get(Long id);

    ResponseEntity<ApiResponse<List<Product>>> getList(Integer size,Integer page);

    ResponseEntity<ApiResponse<ProductDto>> delete(Long id);
}
