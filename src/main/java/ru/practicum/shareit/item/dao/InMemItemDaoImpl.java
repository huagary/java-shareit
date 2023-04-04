package ru.practicum.shareit.item.dao;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.NotExistException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("InMemItemDao")
public class InMemItemDaoImpl implements ItemDao {
    private final Map<Long, Item> items = new HashMap<>();

    private long id = 1L;

    @Override
    public ItemDto addItem(ItemDto itemDto) {
        assert itemDto != null;
        Item item = ItemMapper.fromItemDto(itemDto);
        item.setId(id++);
        items.put(item.getId(), item);
        return ItemMapper.toItemDto(item);
    }

    @Override
    public ItemDto getItem(long id) {
        if (!items.containsKey(id)) throw new NotExistException("Item not found");
        return ItemMapper.toItemDto(items.get(id));
    }

    @Override
    public ItemDto updateItem(long id, ItemDto itemDto) {
        if (!items.containsKey(id)) throw new NotExistException("Item not found");
        if (itemDto.getName() != null) items.get(id).setName(itemDto.getName());
        if (itemDto.getDescription() != null) items.get(id).setDescription(itemDto.getDescription());
        if (itemDto.getAvailable() != null) items.get(id).setAvailable(itemDto.getAvailable());
        return ItemMapper.toItemDto(items.get(id));
    }

    @Override
    public void deleteItem(long id) {
        items.remove(id);
    }

    @Override
    public List<ItemDto> getUserItems(long userId) {
        return items.values().stream()
                .filter(item -> item.getOwnerId() == userId)
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> getAllItems() {
        return items.values().stream()
                .map(ItemMapper::toItemDto)
                .collect(Collectors.toList());
    }
}
