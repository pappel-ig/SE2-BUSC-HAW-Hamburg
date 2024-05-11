package de.haw_hamburg.mensamatch.adapter.api.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class UserLoginDto {

    private final String username;
    private final String password;

}
