package ru.practicum.shareit.booking.model;

import lombok.Getter;

@Getter
public enum BookingStatus {
    WAITING(1, "WAITING"),
    APPROVED(2, "APPROVED"),
    REJECTED(3, "REJECTED"),
    CANCELED(4, "CANCELED");
    private final int id;
    private final String name;

    BookingStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
