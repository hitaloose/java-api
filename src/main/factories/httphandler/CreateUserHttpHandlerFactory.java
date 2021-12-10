package main.factories.httphandler;

import com.sun.net.httpserver.HttpHandler;
import main.adapters.HttpHandlerAdapter;
import main.decorators.HttpHandlerErrorDecorator;
import main.factories.controllers.CreateUserControllerFactory;

public class CreateUserHttpHandlerFactory {
    public static HttpHandler create() {
        return new HttpHandlerErrorDecorator(new HttpHandlerAdapter(CreateUserControllerFactory.create()));
    }
}
