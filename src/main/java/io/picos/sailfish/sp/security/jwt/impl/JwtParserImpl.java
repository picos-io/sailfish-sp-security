package io.picos.sailfish.sp.security.jwt.impl;

import com.netflix.discovery.converters.Auto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.picos.sailfish.sp.security.jwt.JwtOptions;
import io.picos.sailfish.sp.security.jwt.JwtParser;

public class JwtParserImpl implements JwtParser {

    @Auto
    private JwtOptions jwtOptions;

    @Override
    public Jws<Claims> parseJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtOptions.getSecretKey()).parseClaimsJws(token);
    }
}
