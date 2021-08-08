package pl.app.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.app.shop.domain.dao.Category;
import pl.app.shop.domain.dao.Product;
import pl.app.shop.repository.CategoryRepository;
import pl.app.shop.repository.ProductRepository;
import pl.app.shop.service.CategoryService;
import pl.app.shop.service.ProductService;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category update(Long id, Category category) {
        Category categoryDb = findById(id);
        categoryDb.setName(category.getName());
        categoryDb.setParent(category.getParent());
        return categoryDb;
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category with id: " + id + " doesn't exist"));
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Page<Category> getPage(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

}
