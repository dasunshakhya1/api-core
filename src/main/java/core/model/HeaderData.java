package core.model;

import java.util.HashMap;
import java.util.Map;

public class HeaderData {

    private final Map<String, Object> requestHeader = new HashMap<>();


    public Map<String, Object> getRequestHeaders() {
        return requestHeader;
    }

    public HeaderData addHeaderValues(String key, Object t) {
        requestHeader.put(key, t);
        return this;
    }
}
