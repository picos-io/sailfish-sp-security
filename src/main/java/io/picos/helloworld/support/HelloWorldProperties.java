package io.picos.helloworld.support;

import io.picos.helloworld.jwt.JwtOptions;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.Assert;

@ConfigurationProperties(prefix = "helloworld.jwt")
public class HelloWorldProperties implements JwtOptions, InitializingBean {

    private boolean enabled = false;

    private String algorithm;

    private String secret;

    private String issuer;

    private String audience;

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public String getSecretKey() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @Override
    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (enabled) {
            Assert.notNull(this.algorithm, "The algorithm must be supplied");
            Assert.notNull(this.audience, "The audience must be supplied");
            Assert.notNull(this.issuer, "The issuer must be supplied");
            Assert.notNull(this.secret, "The secret must be supplied");
        }

    }
}
