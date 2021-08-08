package pl.app.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.app.shop.domain.dao.User;
import pl.app.shop.repository.RoleRepository;
import pl.app.shop.repository.UserRepository;
import pl.app.shop.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {
        roleRepository.findByName("ROLE_USER").ifPresent(role -> user.setRoles(List.of(role)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional/*(propagation = Propagation.REQUIRES_NEW)*/
// dzięki adnotacji zapisują się zmiany do bazy danych  PAMIETAJ!!!
    public User update(Long id, User user) {
        User userDb = findById(id);
        userDb.setFirstname(user.getFirstname());
        userDb.setLastname(user.getLastname());
        userDb.setEmail(user.getEmail());
        return userDb;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id: " + id + " doesn't exist"));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> getPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
