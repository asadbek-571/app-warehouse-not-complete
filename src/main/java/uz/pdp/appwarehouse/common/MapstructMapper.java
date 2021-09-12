package uz.pdp.appwarehouse.common;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import uz.pdp.appwarehouse.domen.Category;
import uz.pdp.appwarehouse.domen.Product;
import uz.pdp.appwarehouse.domen.Supplier;
import uz.pdp.appwarehouse.domen.Warehouse;
import uz.pdp.appwarehouse.model.CategoryDto;
import uz.pdp.appwarehouse.model.ProductDto;
import uz.pdp.appwarehouse.model.SupplierDto;
import uz.pdp.appwarehouse.model.WarehouseDto;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface MapstructMapper {

    Warehouse toWarehouse(WarehouseDto dto);





    @Mapping(target ="parentCategory.id" ,source = "parentCategoryId")
    Category toCategory(CategoryDto dto);

    @Mapping(target ="parentCategoryId" ,source = "parentCategory.id")
    CategoryDto toCategoryDto(Category category);

    @Mapping(target ="categoryId" ,source = "category.id")
    @Mapping(target ="measurementId" ,source = "measurement.id")
    ProductDto toProductDto(Product product);


    SupplierDto toSupplierDto(Supplier supplier);
}
