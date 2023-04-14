package ru.practicum.shareit.user.dao;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.NotExistException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("InMemUserDao")
public class InMemUserDaoImpl implements UserDao {
    private final Map<Long, User> users = new HashMap<>();
    private long id = 1L;

    @Override
    public UserDto addUser(UserDto userDto) {
        assert userDto != null;
        User user = UserMapper.fromUserDto(userDto);
        user.setId(id++);
        users.put(user.getId(), user);
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto getUser(long id) {
        if (!users.containsKey(id)) throw new NotExistException("User not found");
        return UserMapper.toUserDto(users.get(id));
    }

    @Override
    public UserDto updateUser(long id, UserDto userDto) {
        if (!users.containsKey(id)) throw new NotExistException("User not found");
        if (userDto.getName() != null) users.get(id).setName(userDto.getName());
        if (userDto.getEmail() != null) users.get(id).setEmail(userDto.getEmail());
        return UserMapper.toUserDto(users.get(id));
    }

    @Override
    public List<UserDto> getUsers() {
        return users.values().stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(long id) {
        users.remove(id);
    }
}
