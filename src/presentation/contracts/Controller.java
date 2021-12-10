package presentation.contracts;

public interface Controller {
    public Response handle(Request request) throws Exception;
}
