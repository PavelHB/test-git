package com.example.testing.services;

import com.example.testing.entities.UserEntity;
import com.example.testing.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private List<UserEntity> users = List.of();

    public Optional<UserEntity> getUser(UUID id) throws Exception {
        var user = users
                .stream()
                .filter(userEntity -> userEntity.getId().equals(id))
                .findFirst();

        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        return user;
    }

    @PostConstruct
    private void init() {
        users = List.of(
                new UserEntity(
                        UUID.fromString("79bf947e-a797-4ef4-923d-15cb8cd85cd7"),
                        "Tomas",
                        "Svojanovsky"),
                new UserEntity(UUID.fromString("e00cc4d0-c079-4357-9fbe-3d06259b9aae"), "Jozka", "Kukuricudus")
        );
    }
}
