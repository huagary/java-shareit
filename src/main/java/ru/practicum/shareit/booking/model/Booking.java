package ru.practicum.shareit.booking.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking {
    long id;
    LocalDateTime start;
    LocalDateTime end;
    Item item;
    User booker;
    Status status;

    @Getter
    public enum Status {
        WAITING(1, "WAITING"),
        APPROVED(2, "APPROVED"),
        REJECTED(3, "REJECTED"),
        CANCELED(4, "CANCELED");
        private final int id;
        private final String name;

        Status(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
