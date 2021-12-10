package data.usecases;

import data.contracts.criptography.IHasher;
import data.contracts.repositories.userepository.CreateUserRepository;
import data.contracts.repositories.userepository.CreateUserRepositoryParams;
import domain.entities.User;
import domain.usecases.createuser.CreateUserParams;
import domain.usecases.createuser.ICreateUser;

public class CreateUser implements ICreateUser {
    private IHasher IHasher;

    private CreateUserRepository createUserRepository;

    public CreateUser(IHasher IHasher, CreateUserRepository createUserRepository) {
        this.IHasher = IHasher;
        this.createUserRepository = createUserRepository;
    }

    @Override
    public User run(CreateUserParams params) {
        String hashedPassword = this.IHasher.hash(params.getPassword());

        CreateUserRepositoryParams createUserRepositoryParams = new CreateUserRepositoryParams();
        createUserRepositoryParams.setEmail(params.getEmail());
        createUserRepositoryParams.setUsername(params.getUsername());
        createUserRepositoryParams.setName(params.getName());
        createUserRepositoryParams.setHashedPassword(hashedPassword);

        User createdUser = this.createUserRepository.create(createUserRepositoryParams);

        return createdUser;
    }
}
