package data.usecases;

import data.contracts.IHasher;
import domain.entities.User;
import domain.usecases.createuser.CreateUserParams;
import domain.usecases.createuser.ICreateUser;

public class CreateUser implements ICreateUser {
    private IHasher IHasher;

    public CreateUser(IHasher IHasher) {
        this.IHasher = IHasher;
    }

    @Override
    public User run(CreateUserParams params) {
        String name = params.getName();
        String username = params.getUsername();
        String password = params.getPassword();
        String email = params.getEmail();

        String hashedPassword = this.IHasher.hash(password);

        User createdUser = new User();
        createdUser.setId(123L);
        createdUser.setEmail(email);
        createdUser.setUsername(username);
        createdUser.setName(name);
        createdUser.setHashedPassword(hashedPassword);

        return createdUser;
    }
}
