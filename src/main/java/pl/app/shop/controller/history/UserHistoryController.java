package pl.app.shop.controller.history;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import pl.app.shop.domain.dto.UserDto;
import pl.app.shop.mapper.UserHistoryMapper;
import pl.app.shop.repository.UserRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history/users")
public class UserHistoryController {

    private final UserRepository userRepository;
    private final UserHistoryMapper userHistoryMapper;

    @GetMapping("/{id}")
    public Page<UserDto> getUserHistory(@PathVariable Long id, @RequestParam int page, @RequestParam int size) {
        return userRepository.findRevisions(id, PageRequest.of(page, size)).map(userHistoryMapper::toDto);
    }
}
