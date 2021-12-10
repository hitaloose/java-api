package domain.usecases.createuser;

import domain.entities.User;

public interface ICreateUser {
    public User run(CreateUserParams params);
}
