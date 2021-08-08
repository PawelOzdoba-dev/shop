package pl.app.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.app.shop.domain.dto.UserDto;
import pl.app.shop.mapper.UserMapper;
import pl.app.shop.service.UserService;
import pl.app.shop.validator.group.CreateUser;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated() && (hasRole('ADMIN') || @securityService.hasAccessToUser(#id))")
    public UserDto getUser(@PathVariable Long id) {
        return userMapper.toDto(userService.findById(id));
    }

//    @GetMapping("/all")
//    public List<User> getAllUser() {
//        return userService.findAll();
//    }

    @PostMapping
    @Validated(CreateUser.class)
    @PreAuthorize("isAnonymous()")//tworzymy usera jeśli jesteśmy niezalogowani
    public UserDto createUser(@RequestBody @Valid UserDto user) {
        return userMapper.toDto(userService.create(userMapper.toDao(user)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() && (hasRole('ADMIN') || @securityService.hasAccessToUser(#id))")//dla zalogowanego użytkownika robi update, czy user ma role admina lub czy ma dostęp do usera
    public UserDto updateUser(@PathVariable Long id, @RequestBody @Valid UserDto user) {
        return userMapper.toDto(userService.update(id, userMapper.toDao(user)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")//sprawdza czy mamy jakąkolwiek z ról
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")//silnik spel, wywołuje metodę hasRole
    public Page<UserDto> getUserPage(@RequestParam int page, @RequestParam int size) {
        return userService.getPage(PageRequest.of(page, size)).map(userMapper::toDto);
    }

    @PatchMapping("/avatar")
    public void updateAvatar(@RequestParam MultipartFile file) {

    }
}
