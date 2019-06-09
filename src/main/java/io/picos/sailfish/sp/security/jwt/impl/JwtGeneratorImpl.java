package io.picos.sailfish.sp.security.jwt.impl;

import io.jsonwebtoken.Jwts;
import io.picos.sailfish.sp.security.jwt.JwtGenerator;
import io.picos.sailfish.sp.security.jwt.JwtOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtGeneratorImpl implements JwtGenerator {

    @Autowired
    private JwtOptions jwtOptions;

    @Override
    public String generate(UserDetails userDetails) {
        return Jwts.builder()
                   .setSubject(userDetails.getUsername())
                   .setIssuer()
                   .setClaims().toString();
    }

}
