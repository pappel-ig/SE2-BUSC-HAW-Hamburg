package de.haw_hamburg.mensamatch.domain.user.service;

import de.haw_hamburg.mensamatch.domain.user.UserRepository;
import de.haw_hamburg.mensamatch.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findUserByName(String username) {
        return userRepository.findUser(username);
    }

    public boolean createNewUser(User user) {
        final User newUser = user.toBuilder()
                .id(UUID.randomUUID())
                .roles(List.of("USER"))
                .build();
        return userRepository.saveNewUser(newUser);
    }
}
