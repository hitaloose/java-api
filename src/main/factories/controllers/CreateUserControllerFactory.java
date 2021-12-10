package main.factories.controllers;

import main.factories.usercases.CreateUserFactory;
import presentation.controllers.CreateUserController;

public class CreateUserControllerFactory {
    public static CreateUserController create() {
        return new CreateUserController(CreateUserFactory.create());
    }
}
