package hello.world.http.server;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class ShoppingCartEntity {
    private String sessionId;
    private Integer total;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
