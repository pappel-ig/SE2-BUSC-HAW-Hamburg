package de.haw_hamburg.mensamatch.domain.user.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(toBuilder = true)
public class User {

    private final String username;
    private final String password;
    private final List<String> roles;

}
