package pl.app.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.app.shop.domain.dao.Product;

public interface ProductService {

    Product create(Product product, Long categoryId);

    Product update(Long id, Product ProductDto);

    Product findById(Long id);

    void delete(Long id);

    Page<Product> getPage(Pageable pageable);
}
