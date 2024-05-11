package de.haw_hamburg.mensamatch.adapter.api.security;

import de.haw_hamburg.mensamatch.domain.user.model.User;
import de.haw_hamburg.mensamatch.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MongoUserDetailsService implements UserDetailsService {

    private final UserService userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> user = userRepository.findUserByName(username);
        if (user.isEmpty()) throw new UsernameNotFoundException("user not found");

        final User fetchedUser = user.get();
        return new org.springframework.security.core.userdetails.User(
                fetchedUser.getUsername(),
                fetchedUser.getPassword(),
                Optional.ofNullable(fetchedUser.getRoles()).stream().flatMap(Collection::stream).map(SimpleGrantedAuthority::new).toList());
    }
}
