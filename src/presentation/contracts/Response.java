package presentation.contracts;

import java.util.Map;

public class Response {
    private ResponseStatus status;
    private Map<String, String> body;

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public void setBody(Map<String, String> body) {
        this.body = body;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public Map<String, String> getBody() {
        return body;
    }
}
