package io.picos.helloworld.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public interface JwtParser {

    Jws<Claims> parseJwtToken(String token);

}
