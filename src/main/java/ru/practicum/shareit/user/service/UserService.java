package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto addUser(UserDto userDto);

    UserDto getUser(long id);

    UserDto updateUser(long id, UserDto userDto);

    List<UserDto> getUsers();

    void deleteUser(long id);
}
