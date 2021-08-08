package pl.app.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import pl.app.shop.domain.dao.Product;
import pl.app.shop.domain.dto.ProductDto;
import pl.app.shop.mapper.ProductMapper;
import pl.app.shop.service.impl.ProductServiceImpl;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductServiceImpl productServiceImpl;
    private final ProductMapper productMapper;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")//dla wszystkich
    public ProductDto getById(@PathVariable Long id) {
        return productMapper.toDto(productServiceImpl.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")//dla admina, mozna dodać walidację do ProductDto
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productMapper.toDto(productServiceImpl.update(id, productMapper.toDao(productDto)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping//dla admina,  mozna dgiodać walidację do ProductDto
    public ProductDto createProduct(@RequestBody @Valid ProductDto productDto, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
        return productMapper.toDto(productServiceImpl.create(productMapper.toDao(productDto), productDto.getCategoryId()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void handlerMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("handlerMethodArgumentTypeMismatchException: ", e);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")//dla admina
    public void deleteProduct(@PathVariable Long id) {
        productServiceImpl.delete(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping//dla wszystkich
    public Page<ProductDto> getPages(@RequestParam int page, @RequestParam int size) {
//        Page<Product> page1 = productServiceImpl.getPage(PageRequest.of(page, size));
        return productServiceImpl.getPage(PageRequest.of(page, size)).map(productMapper::toDto);
    }

}
