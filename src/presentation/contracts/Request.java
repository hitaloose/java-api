package presentation.contracts;

import java.util.Map;

public class Request {
    private Map<String, String> headers;
    private Map<String, String> body;
    private Map<String, String> queryParams;
    private Map<String, String> routeParams;

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String> getBody() {
        return body;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public Map<String, String> getRouteParams() {
        return routeParams;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void setBody(Map<String, String> body) {
        this.body = body;
    }

    public void setQueryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    public void setRouteParams(Map<String, String> routeParams) {
        this.routeParams = routeParams;
    }
}
