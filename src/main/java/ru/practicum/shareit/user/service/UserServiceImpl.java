package ru.practicum.shareit.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.AlreadyExistException;
import ru.practicum.shareit.user.dao.UserDao;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Qualifier("InMemUserDao")
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        if (emailExist(userDto).isPresent()) throw new AlreadyExistException("Email already in use");
        return userDao.addUser(userDto);
    }

    @Override
    public UserDto getUser(long id) {
        return userDao.getUser(id);
    }

    @Override
    public UserDto updateUser(long id, UserDto userDto) {
        if (emailExist(userDto).isPresent() && emailExist(userDto).get().getId() != id)
            throw new AlreadyExistException("Email already in use");
        return userDao.updateUser(id, userDto);
    }

    @Override
    public List<UserDto> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    private Optional<UserDto> emailExist(UserDto userDto) {
        return userDao.getUsers().stream()
                .filter(u -> u.getEmail().equals(userDto.getEmail()))
                .findFirst();
    }
}
