package ru.practicum.shareit.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/items")
public class ItemController {
    private final String userIdHeader = "X-Sharer-User-Id";
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ItemDto addItem(@RequestHeader(userIdHeader) long ownerId,
                           @Valid @RequestBody ItemDto itemDto) {
        logRequestMethod(RequestMethod.POST, String.format(" + header '%s': %s", userIdHeader, ownerId));
        return itemService.addItem(ownerId, itemDto);
    }

    @PatchMapping("/{id}")
    public ItemDto updateItem(@RequestHeader(userIdHeader) long ownerId,
                              @PathVariable long id,
                              @RequestBody ItemDto itemDto) {
        logRequestMethod(RequestMethod.PATCH, String.format("/%s + header '%s': %s", id, userIdHeader, ownerId));
        return itemService.updateItem(ownerId, id, itemDto);
    }

    @GetMapping("/{id}")
    public ItemDto getItem(@PathVariable long id) {
        logRequestMethod(RequestMethod.GET, String.format("/%s", id));
        return itemService.getItem(id);
    }

    @GetMapping
    public List<ItemDto> getUserItems(@RequestHeader(userIdHeader) long userId) {
        logRequestMethod(RequestMethod.GET, String.format(" + header '%s': %s", userIdHeader, userId));
        return itemService.getUserItems(userId);
    }

    @GetMapping("/search")
    public List<ItemDto> search(@RequestParam String text) {
        logRequestMethod(RequestMethod.GET, String.format("/search?text=%s", text));
        System.out.println(text);
        return itemService.search(text);
    }

    private void logRequestMethod(RequestMethod requestMethod, String path) {
        log.info(String.format("Получен запрос %s по адресу: /users%s", requestMethod, path));
    }
}
