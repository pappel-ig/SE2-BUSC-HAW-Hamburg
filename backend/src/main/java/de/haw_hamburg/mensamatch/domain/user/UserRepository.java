package de.haw_hamburg.mensamatch.domain.user;

import de.haw_hamburg.mensamatch.domain.user.model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findUser(String username);
    boolean saveNewUser(User user);

}
