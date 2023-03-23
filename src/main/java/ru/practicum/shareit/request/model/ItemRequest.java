package ru.practicum.shareit.request.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.user.model.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequest {
    long id;
    @Size(max = 200)
    String description;
    @NotNull
    User requestor;
    @PastOrPresent
    LocalDateTime created;
}
