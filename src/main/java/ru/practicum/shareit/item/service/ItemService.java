package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto addItem(long userId, ItemDto itemDto);

    ItemDto getItem(long id);

    ItemDto updateItem(long ownerId, long id, ItemDto itemDto);

    void deleteItem(long id);

    List<ItemDto> getUserItems(long userId);

    List<ItemDto> search(String text);
}
