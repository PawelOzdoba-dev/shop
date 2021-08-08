package pl.app.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.app.shop.domain.dao.Category;
import pl.app.shop.domain.dao.Product;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Override
    @Modifying
    @Query(value = "Delete from Category where id = ?1", nativeQuery = true)
    void deleteById(Long id);
}
