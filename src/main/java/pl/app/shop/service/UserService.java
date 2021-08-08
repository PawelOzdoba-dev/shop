package pl.app.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.app.shop.domain.dao.User;

import java.util.List;

public interface UserService {

    User create(User user);
    List<User> findAll();
    User update(Long id, User user);
    User findById(Long id);
    void deleteById(Long id);
    Page<User> getPage(Pageable pageable);
}
