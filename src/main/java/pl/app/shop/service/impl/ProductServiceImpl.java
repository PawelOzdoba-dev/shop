package pl.app.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.app.shop.domain.dao.Category;
import pl.app.shop.domain.dao.Product;
import pl.app.shop.repository.ProductRepository;
import pl.app.shop.service.CategoryService;
import pl.app.shop.service.ProductService;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;


    @Override
    public Product create(Product product, Long categoryId) {
        Category category = categoryService.findById(categoryId);
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product update(Long id, Product product) {
        Product productById = findById(id);
        productById.setName(product.getName());
        productById.setCategory(product.getCategory());
        return productById;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id: " + id + " doesn't exist"));
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> getPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

}
