package de.haw_hamburg.mensamatch.adapter.persistence.user.model;

import de.haw_hamburg.mensamatch.domain.user.model.User;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDao {

    private String username;
    private String password;
    private List<String> roles;

    public User toUser() {
        return User.builder()
                .username(username)
                .password(password)
                .roles(roles)
                .build();
    }

    public static UserDao from(User user) {
        return UserDao.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }
}
