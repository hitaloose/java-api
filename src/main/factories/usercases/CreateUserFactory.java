package main.factories.usercases;

import data.usecases.CreateUser;
import domain.usecases.createuser.ICreateUser;
import infra.criptography.Hasher;
import infra.repositories.UserRepository;

public class CreateUserFactory {
    public static ICreateUser create() {
        return new CreateUser(new Hasher(), new UserRepository());
    }
}
