package infra.repositories;

import data.contracts.repositories.userepository.CreateUserRepository;
import data.contracts.repositories.userepository.CreateUserRepositoryParams;
import domain.entities.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserRepository implements CreateUserRepository {
    private static List<User> USERS = new ArrayList<>();

    @Override
    public User create(CreateUserRepositoryParams params) {
        User newUser = new User();
        newUser.setId(new Date().getTime());
        newUser.setUsername(params.getUsername());
        newUser.setName(params.getName());
        newUser.setEmail(params.getEmail());
        newUser.setHashedPassword(params.getHashedPassword());

        UserRepository.USERS.add(newUser);

        return newUser;
    }
}
