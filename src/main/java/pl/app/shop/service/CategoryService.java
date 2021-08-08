package pl.app.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.app.shop.domain.dao.Category;
import pl.app.shop.domain.dao.Product;
import pl.app.shop.domain.dto.CategoryDto;

public interface CategoryService {

    Category create(Category category);

    Category update(Long id, Category category);

    Category findById(Long id);

    void delete(Long id);

    Page<Category> getPage(Pageable pageable);
}
