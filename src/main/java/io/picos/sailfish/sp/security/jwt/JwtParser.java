package io.picos.sailfish.sp.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public interface JwtParser {

    Jws<Claims> parseJwtToken(String token);

}
