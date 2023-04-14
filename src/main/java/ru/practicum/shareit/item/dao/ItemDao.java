package ru.practicum.shareit.item.dao;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

public interface ItemDao {
    ItemDto addItem(ItemDto itemDto);

    ItemDto getItem(long id);

    ItemDto updateItem(long id, ItemDto itemDto);

    void deleteItem(long id);

    List<ItemDto> getUserItems(long userId);

    List<ItemDto> getAllItems();
}
