package de.haw_hamburg.mensamatch.domain.user.service;

import de.haw_hamburg.mensamatch.domain.criteria.CriteriaRepository;
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
    private final CriteriaRepository criteriaRepository;

    public Optional<User> findUserByName(String username) {
        return userRepository.findUser(username)
                .map(user -> user.toBuilder()
                        .criteriaSelection(criteriaRepository.getCriteriaForUser(user.getId()))
                        .build());
    }

    public boolean createNewUser(User user) {
        final User newUser = user.toBuilder()
                .id(UUID.randomUUID())
                .roles(List.of("USER"))
                .build();
        return userRepository.saveNewUser(newUser);
    }
}
