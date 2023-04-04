package ru.practicum.shareit.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto addUser(@Valid @RequestBody UserDto userDto) {
        logRequestMethod(RequestMethod.POST, "");
        return userService.addUser(userDto);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        logRequestMethod(RequestMethod.GET, "");
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable long id) {
        logRequestMethod(RequestMethod.GET, "/" + id);
        return userService.getUser(id);
    }

    @PatchMapping("/{id}")
    public UserDto updateUser(@RequestBody UserDto userDto,
                              @PathVariable long id) {
        logRequestMethod(RequestMethod.PATCH, "/" + id);
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        logRequestMethod(RequestMethod.DELETE, "/" + id);
        userService.deleteUser(id);
    }

    private void logRequestMethod(RequestMethod requestMethod, String path) {
        log.info("Получен запрос " + requestMethod + " по адресу: /users" + path);
    }
}
