package io.picos.helloworld.jwt;

public interface JwtOptions {

    boolean isEnabled();

    String getAlgorithm();

    String getSecretKey();

    String getIssuer();

    String getAudience();

}
