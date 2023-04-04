package ru.practicum.shareit.user.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    long id;
    String name;
    @Email
    String email;
}
