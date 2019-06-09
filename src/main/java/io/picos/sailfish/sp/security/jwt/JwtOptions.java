package io.picos.sailfish.sp.security.jwt;

public interface JwtOptions {

    boolean isEnabled();

    String getAlgorithm();

    String getSecretKey();

    String getIssuer();

    String getAudience();

}
