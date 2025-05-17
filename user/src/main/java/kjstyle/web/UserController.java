package kjstyle.web;

import kjstyle.domain.repository.User;
import kjstyle.domain.service.UserService;
import kjstyle.web.dto.UserCreateDto;
import kjstyle.web.dto.UserResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResDto>> getAll() {
        List<UserResDto> userResDtoList = userService.findAll().stream()
                .map(UserResDto::of)
                .toList();
        return ResponseEntity.ok(userResDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResDto> getById(@PathVariable Long id) {
        return userService.findById(id)
                .map(UserResDto::of)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserResDto> create(@RequestBody UserCreateDto userCreateDto) {
        User creatingUser = userCreateDto.toUser();
        User createdUser = userService.save(creatingUser);
        UserResDto createdUserResDto = UserResDto.of(createdUser);
        return ResponseEntity.ok(createdUserResDto);
    }
}