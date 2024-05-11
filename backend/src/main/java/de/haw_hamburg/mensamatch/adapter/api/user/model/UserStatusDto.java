package de.haw_hamburg.mensamatch.adapter.api.user.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserStatusDto {

    private final boolean loggedIn;
    private final String username;
    private final List<String> roles;

}
