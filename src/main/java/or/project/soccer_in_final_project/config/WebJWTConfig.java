package or.project.soccer_in_final_project.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "or.project.soccerin")
public class WebJWTConfig {

    private String secret = "secret";

    private  Long expires = 86400000L;

    public WebJWTConfig() {
    }

    public WebJWTConfig(String secret, Long expired) {
        this.secret = secret;
        this.expires = expired;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpired() {
        return expires;
    }

    public void setExpired(Long expired) {
        this.expires = expired;
    }
}
