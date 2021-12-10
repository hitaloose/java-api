package presentation.controllers;

import domain.entities.User;
import domain.usecases.createuser.CreateUserParams;
import domain.usecases.createuser.ICreateUser;
import presentation.contracts.Controller;
import presentation.contracts.Request;
import presentation.contracts.Response;
import presentation.contracts.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

public class CreateUserController implements Controller {
    private ICreateUser createUser;

    public CreateUserController(ICreateUser createUser) {
        this.createUser = createUser;
    }

    @Override
    public Response handle(Request request) throws Exception {
        Map<String, String> body = request.getBody();

        String name = body.get("name");
        String email = body.get("email");
        String username = body.get("username");
        String password = body.get("password");
        String passwordConfirmation = body.get("password_confirmation");

        if(!password.equals(passwordConfirmation)) {
            throw new Exception("Passwords not match");
        }

        CreateUserParams createUserParams = new CreateUserParams();
        createUserParams.setName(name);
        createUserParams.setEmail(email);
        createUserParams.setUsername(username);
        createUserParams.setPassword(password);

        User createdUser = this.createUser.run(createUserParams);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("id", createdUser.getId().toString());
        responseBody.put("name", createdUser.getName());
        responseBody.put("email", createdUser.getEmail());
        responseBody.put("username", createdUser.getUsername());

        Response response = new Response();
        response.setStatus(ResponseStatus.CREATED);
        response.setBody(responseBody);

        return response;
    }
}
