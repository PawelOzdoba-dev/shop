package pl.app.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.app.shop.domain.dao.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    @Modifying
    @Query(value = "Delete from PRODUCT where id = ?1", nativeQuery = true)
    void deleteById(Long id);
}
