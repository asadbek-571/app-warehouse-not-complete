package uz.pdp.appwarehouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.appwarehouse.common.MapstructMapper;
import uz.pdp.appwarehouse.domen.Attachment;
import uz.pdp.appwarehouse.domen.Category;
import uz.pdp.appwarehouse.domen.Measurement;
import uz.pdp.appwarehouse.domen.Product;
import uz.pdp.appwarehouse.model.AttachmentDto;
import uz.pdp.appwarehouse.model.ProductDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;
import uz.pdp.appwarehouse.repository.AttachmentRepo;
import uz.pdp.appwarehouse.repository.CategoryRepo;
import uz.pdp.appwarehouse.repository.MeasurementRepo;
import uz.pdp.appwarehouse.repository.ProductRepo;
import uz.pdp.appwarehouse.service.ProductService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final MeasurementRepo measurementRepo;
    private final AttachmentRepo attachmentRepo;
    private final MapstructMapper mapstructMapper;


    @Override
    public ResponseEntity<ApiResponse<ProductDto>> save(ProductDto dto, MultipartFile multipartFile) throws IOException {
        Optional<Category> optionalCategory = categoryRepo.findByActiveTrueAndId(dto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Category not found"), HttpStatus.NOT_FOUND);

        Optional<Measurement> optionalMeasurement = measurementRepo.findByActiveTrueAndId(dto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Measurement not found"), HttpStatus.NOT_FOUND);

        Product product = new Product();
        product.setCode(System.currentTimeMillis());

        ProductServiceImpl productService = new ProductServiceImpl(productRepo, categoryRepo, measurementRepo, attachmentRepo, mapstructMapper);
        return productService.toProduct(dto, product, optionalCategory.get(), optionalMeasurement.get(), multipartFile);
    }


    @Override
    public ResponseEntity<ApiResponse<ProductDto>> update(Long id, ProductDto dto, MultipartFile multipartFile) {

        Optional<Product> optionalProduct = productRepo.findByActiveTrueAndId(id);
        if (!optionalProduct.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Product not found"), HttpStatus.NOT_FOUND);

        Optional<Category> optionalCategory = categoryRepo.findByActiveTrueAndId(dto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Category not found"), HttpStatus.NOT_FOUND);

        Optional<Measurement> optionalMeasurement = measurementRepo.findByActiveTrueAndId(dto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Measurement not found"), HttpStatus.NOT_FOUND);


        ProductServiceImpl productService = new ProductServiceImpl(productRepo, categoryRepo, measurementRepo, attachmentRepo, mapstructMapper);
        return productService.toProduct(dto, optionalProduct.get(), optionalCategory.get(), optionalMeasurement.get(), multipartFile);
    }


    private ResponseEntity<ApiResponse<ProductDto>> toProduct(ProductDto dto,
                                                              Product product,
                                                              Category category,
                                                              Measurement measurement,
                                                              MultipartFile multipartFile) {

        product.setCategory(category);
        product.setMeasurement(measurement);
        product.setName(dto.getName());
        product.setCode(System.currentTimeMillis());
        product.setActive(dto.isActive());
        Product save = productRepo.save(product);

        ProductServiceImpl productService = new ProductServiceImpl(productRepo, categoryRepo, measurementRepo, attachmentRepo, mapstructMapper);

        return productService.uploadFile(multipartFile, dto, save);
    }

    @Override
    public ResponseEntity<ApiResponse<Product>> get(Long id) {
        Optional<Product> optionalProduct = productRepo.findByActiveTrueAndId(id);
        return optionalProduct.map(product -> new ResponseEntity<>(new ApiResponse<>(product), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new ApiResponse<>("Product not found"), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ApiResponse<List<Product>>> getList(Integer size, Integer page) {
        Optional<List<Product>> optionalProductList = productRepo.findAllByActiveTrue(size, page * size);
        return optionalProductList.map(products -> new ResponseEntity<>(new ApiResponse<>(products), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new ApiResponse<>("Products not found"), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ApiResponse<ProductDto>> delete(Long id) {
        Optional<Product> optionalProduct = productRepo.findByActiveTrueAndId(id);
        if (!optionalProduct.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Product not found"), HttpStatus.NOT_FOUND);
        Product product = optionalProduct.get();
        product.setActive(false);
        productRepo.save(product);
        ProductDto productDto = mapstructMapper.toProductDto(product);
        return new ResponseEntity<>(new ApiResponse<>(productDto), HttpStatus.OK);
    }


    private static final String uploadDirectory = "files";

    public ResponseEntity<ApiResponse<ProductDto>> uploadFile(MultipartFile file, ProductDto productDto, Product save) {
//        for (MultipartFile file : multipartFile) {
        try {
            if (file != null) {
                String originalName = file.getOriginalFilename();

                Attachment attachment = new Attachment();
                attachment.setSize(file.getSize());
                attachment.setContentType(file.getContentType());
                attachment.setOriginalName(originalName);
                attachment.setProduct(save);
                String[] split = new String[0];
                if (originalName != null) {
                    split = originalName.split("\\.");
                }

                String name = UUID.randomUUID() + "." + split[split.length - 1];
                attachment.setName(name);
                attachmentRepo.save(attachment);
                Path path = Paths.get(uploadDirectory + "/" + name);
                Files.copy(file.getInputStream(), path);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>("Could not store the file."), HttpStatus.BAD_REQUEST);
        }
//        }
        return new ResponseEntity<>(new ApiResponse<>(productDto), HttpStatus.OK);

    }


    public ResponseEntity<List<AttachmentDto>> getAttachmentList() {

        List<AttachmentDto> attachments = new ArrayList<>();
        for (Attachment item : attachmentRepo.findAll()) {
            AttachmentDto attachment = new AttachmentDto();
            attachment.setId(item.getId());
            attachments.add(attachment);
        }
        return new ResponseEntity<>(attachments, HttpStatus.OK);
    }


}
