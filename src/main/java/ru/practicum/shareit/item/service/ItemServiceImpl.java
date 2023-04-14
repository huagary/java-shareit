package ru.practicum.shareit.item.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ForbiddenException;
import ru.practicum.shareit.item.dao.ItemDao;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("ItemServiceImpl")
public class ItemServiceImpl implements ItemService {
    @Qualifier("InMemItemDao")
    private final ItemDao itemDao;
    private final UserService userService;

    @Autowired
    public ItemServiceImpl(ItemDao itemDao, UserService userService) {
        this.itemDao = itemDao;
        this.userService = userService;
    }

    @Override
    public ItemDto addItem(long ownerId, ItemDto itemDto) {
        return itemDao.addItem(setItemDtoOwnerId(ownerId, itemDto));
    }

    @Override
    public ItemDto getItem(long id) {
        return itemDao.getItem(id);
    }

    @Override
    public ItemDto updateItem(long ownerId, long id, ItemDto itemDto) {
        if (itemDao.getItem(id).getOwnerId() != ownerId) throw new ForbiddenException("Only owner can modify item");
        return itemDao.updateItem(id, itemDto);
    }

    @Override
    public void deleteItem(long id) {
        itemDao.deleteItem(id);
    }

    @Override
    public List<ItemDto> getUserItems(long userId) {
        return itemDao.getUserItems(userId);
    }

    @Override
    public List<ItemDto> search(String text) {
        List<ItemDto> result = new ArrayList<>();
        if (!text.isBlank()) {
            result = itemDao.getAllItems().stream()
                    .filter(i -> i.getName().toLowerCase().contains(text.toLowerCase())
                            || i.getDescription().toLowerCase().contains(text.toLowerCase()))
                    .filter(i -> i.getAvailable().equals(true))
                    .collect(Collectors.toList());

        }
        return result;
    }

    private ItemDto setItemDtoOwnerId(long ownerId, ItemDto itemDto) {
        userService.getUser(ownerId);
        itemDto.setOwnerId(ownerId);
        return itemDto;
    }
}
