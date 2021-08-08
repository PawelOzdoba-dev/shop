package pl.app.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.app.shop.domain.dao.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
