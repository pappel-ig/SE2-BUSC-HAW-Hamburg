package de.haw_hamburg.mensamatch.adapter.api.user;

import de.haw_hamburg.mensamatch.adapter.api.user.model.UserLoginDto;
import de.haw_hamburg.mensamatch.adapter.api.user.model.UserStatusDto;
import de.haw_hamburg.mensamatch.domain.user.model.User;
import de.haw_hamburg.mensamatch.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public UserStatusDto userStatus(Authentication auth) {
        return UserStatusDto.builder()
                .loggedIn(Optional.ofNullable(auth).map(Authentication::isAuthenticated).orElse(false))
                .username(Optional.ofNullable(auth).map(Authentication::getName).orElse(null))
                .roles(Optional.ofNullable(auth).map(Authentication::getAuthorities).map(grantedAuthorities -> grantedAuthorities.stream().map(GrantedAuthority::getAuthority).toList()).orElse(null))
                .build();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Void> register(@RequestBody UserLoginDto login) {
        if (userService.createNewUser(User.builder().username(login.getUsername()).password(passwordEncoder.encode(login.getPassword())).build())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> login(@RequestBody UserLoginDto login, HttpServletRequest request, HttpServletResponse response) {
        Authentication authRequest = UsernamePasswordAuthenticationToken.unauthenticated(login.getUsername(), login.getPassword());
        Authentication authResponse = authenticationManager.authenticate(authRequest);
        if (authResponse.isAuthenticated()) {
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authResponse);
            SecurityContextHolder.setContext(context);
            securityContextRepository.saveContext(context, request, response);
            return ResponseEntity.ok("authenticated!");
        } else return ResponseEntity.ok("not authenticated!");
    }
}
