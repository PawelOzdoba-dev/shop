package pl.app.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import pl.app.shop.domain.dao.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, RevisionRepository<User, Long, Integer> { //pobieranie danych z tabelki np. user_aud

    //ctrl + o - nadpisz
//ctrl + i - zaimplementuj
    //Delete bez dodatkowego selecta (zamiast 2 zapytań)
    @Override
    @Modifying
    @Query(value = "delete from user where id = ?1", nativeQuery = true)
    void deleteById(Long id);

    Optional<User> findByEmail(String email);

}
