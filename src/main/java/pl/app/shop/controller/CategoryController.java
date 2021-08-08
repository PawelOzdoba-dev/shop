package pl.app.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import pl.app.shop.domain.dto.CategoryDto;
import pl.app.shop.domain.dto.ProductDto;
import pl.app.shop.mapper.CategoryMapper;
import pl.app.shop.mapper.ProductMapper;
import pl.app.shop.service.impl.CategoryServiceImpl;
import pl.app.shop.service.impl.ProductServiceImpl;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServiceImpl categoryServiceImpl;
    private final CategoryMapper categoryMapper;

    @GetMapping("/{id}")
    public CategoryDto getById(@PathVariable Long id) {
        return categoryMapper.toDto(categoryServiceImpl.findById(id));
    }

    @PutMapping("/{id}")
    public CategoryDto updateProduct(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        return categoryMapper.toDto(categoryServiceImpl.update(id, categoryMapper.toDao(categoryDto)));
    }

    @PostMapping
    public CategoryDto createProduct(@RequestBody CategoryDto categoryDto) {
        return categoryMapper.toDto(categoryServiceImpl.create(categoryMapper.toDao(categoryDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        categoryServiceImpl.delete(id);
    }

    @GetMapping
    public Page<CategoryDto> getPages(@RequestParam int page, @RequestParam int size) {
//        Page<Product> page1 = productServiceImpl.getPage(PageRequest.of(page, size));
        return categoryServiceImpl.getPage(PageRequest.of(page, size)).map(categoryMapper::toDto);
    }

}
