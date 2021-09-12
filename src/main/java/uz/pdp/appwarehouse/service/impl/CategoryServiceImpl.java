package uz.pdp.appwarehouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.common.MapstructMapper;
import uz.pdp.appwarehouse.domen.Category;
import uz.pdp.appwarehouse.model.CategoryDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;
import uz.pdp.appwarehouse.repository.CategoryRepo;
import uz.pdp.appwarehouse.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    //    private final MapstructMapper mapstructMapper;
    private final CategoryRepo categoryRepo;

    /**
     * Saves the Category
     *
     * @return CategoryDto
     */
    @Override
    public ResponseEntity<ApiResponse<CategoryDto>> save(CategoryDto dto) {
        Boolean byNameAndActiveTrue = categoryRepo.existsByNameAndActiveTrue(dto.getName());
        if (byNameAndActiveTrue)
            return new ResponseEntity<>(new ApiResponse<>("This category already exists"), HttpStatus.NOT_FOUND);

        Category category = new Category();
        CategoryServiceImpl service = new CategoryServiceImpl(categoryRepo);
        return service.setCategory(category, dto);
    }


    /**
     * Updates Category
     *
     * @param id
     * @return CategoryDto
     */
    @Override
    public ResponseEntity<ApiResponse<CategoryDto>> update(Long id, CategoryDto dto) {
        Optional<Category> categoryOptional = categoryRepo.findById(id);
        if (!categoryOptional.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Category not found"), HttpStatus.NOT_FOUND);

        Boolean byNameAndActiveTrue = categoryRepo.existsByNameAndActiveTrue(dto.getName());
        if (byNameAndActiveTrue)
            return new ResponseEntity<>(new ApiResponse<>("This category already exists"), HttpStatus.NOT_FOUND);

        Category category = categoryOptional.get();
        CategoryServiceImpl service = new CategoryServiceImpl(categoryRepo);
        return service.setCategory(category, dto);
    }


    /**
     * Get a single list of the category
     *
     * @param id
     * @return Warehouse
     */
    @Override
    public ResponseEntity<ApiResponse<Category>> get(Long id) {
        Optional<Category> optionalCategory = categoryRepo.findByActiveTrueAndId(id);
        return optionalCategory.map(category -> new ResponseEntity<>(new ApiResponse<>(category), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new ApiResponse<>("Category not found"), HttpStatus.NOT_FOUND));
    }

    /**
     * Get a category list
     *
     * @param page
     * @param size
     * @return Category
     */
    @Override
    public ResponseEntity<ApiResponse<List<Category>>> getList(Integer size, Integer page) {
        Optional<List<Category>> optionalList = categoryRepo.findAllByActiveTrue(size, page * size);
        return optionalList.map(categories -> new ResponseEntity<>(new ApiResponse<>(categories), HttpStatus.NOT_FOUND)).orElseGet(() -> new ResponseEntity<>(new ApiResponse<>("Category not found"), HttpStatus.NOT_FOUND));
    }

    /**
     * Delete a single category list
     *
     * @param id
     * @return Boolean
     */
    @Override
    public ResponseEntity<ApiResponse<Boolean>> delete(Long id) {
        Optional<Category> optionalCategory = categoryRepo.findByActiveTrueAndId(id);
        if (!optionalCategory.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Category not found"), HttpStatus.NOT_FOUND);
        Category category = optionalCategory.get();
        category.setActive(false);
        categoryRepo.save(category);
        return new ResponseEntity<>(new ApiResponse<>(true), HttpStatus.OK);
    }

    private ResponseEntity<ApiResponse<CategoryDto>> setCategory(Category category, CategoryDto dto) {
        if (dto.getParentCategoryId() != 0) {
            Optional<Category> optionalCategory = categoryRepo.findByActiveTrueAndId(dto.getParentCategoryId());
            if (!optionalCategory.isPresent())
                return new ResponseEntity<>(new ApiResponse<>("Parent category not found"), HttpStatus.NOT_FOUND);
            category.setParentCategory(optionalCategory.get());
        } else
            category.setParentCategory(null);

        category.setActive(dto.isActive());
        category.setName(dto.getName());
        categoryRepo.save(category);
        return new ResponseEntity<>(new ApiResponse<>(dto), HttpStatus.OK);
    }

}
