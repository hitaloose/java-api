package data.contracts.repositories.userepository;

import domain.entities.User;

public interface CreateUserRepository {
    public User create(CreateUserRepositoryParams params);
}
