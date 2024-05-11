package de.haw_hamburg.mensamatch.adapter.persistence.user;

import com.mongodb.client.MongoCollection;
import de.haw_hamburg.mensamatch.adapter.persistence.user.model.UserDao;
import de.haw_hamburg.mensamatch.domain.user.UserRepository;
import de.haw_hamburg.mensamatch.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

@Repository
@RequiredArgsConstructor
public class MongoUserRepository implements UserRepository {

    private final MongoCollection<UserDao> collection;

    @Override
    public Optional<User> findUser(String username) {
        return Optional.ofNullable(collection.find(eq("username", username)).first()).map(UserDao::toUser);
    }

    @Override
    public boolean saveNewUser(User user) {
        if (findUser(user.getUsername()).isPresent()) return false;
        return collection.insertOne(UserDao.from(user)).wasAcknowledged();
    }
}
