package io.picos.helloworld.jwt;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class JwtUtil {

    private static final Log logger = LogFactory.getLog(JwtUtil.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private JwtOptions jwtOptions;

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    public User parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                              .setSigningKey(jwtOptions.getSecretKey())
                              .parseClaimsJws(token)
                              .getBody();

            String username = body.get("username", String.class);
            String password = "";
            boolean enabled = body.get("enabled", Boolean.class);
            boolean accountNonExpired = body.get("accountNonExpired", Boolean.class);
            boolean credentialsNonExpired = body.get("credentialsNonExpired", Boolean.class);
            boolean accountNonLocked = body.get("accountNonLocked", Boolean.class);
            String authoritiesString = body.get("authorities", String.class);

            JavaType javaType = objectMapper.getTypeFactory()
                                            .constructParametricType(List.class, SimpleGrantedAuthority.class);
            Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>) objectMapper.readValue(
                    authoritiesString,
                    javaType);

            return new User(username,
                            password,
                            enabled,
                            accountNonExpired,
                            credentialsNonExpired,
                            accountNonLocked,
                            authorities);

        } catch (JwtException | ClassCastException | IOException e) {
            logger.error(e);
            return null;
        }
    }

    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     *
     * @param u the user for which the token will be generated
     * @return the JWT token
     */
    public String generateToken(User u) {
        try {
            Claims claims = Jwts.claims().setSubject(u.getUsername());
            claims.put("username", u.getUsername() + "");
            claims.put("authorities", objectMapper.writeValueAsString(u.getAuthorities()));
            claims.put("accountNonExpired", u.isAccountNonExpired());
            claims.put("accountNonLocked", u.isAccountNonLocked());
            claims.put("credentialsNonExpired", u.isCredentialsNonExpired());
            claims.put("enabled", u.isEnabled());
            return Jwts.builder()
                       .setClaims(claims)
                       .signWith(SignatureAlgorithm.valueOf(jwtOptions.getAlgorithm()), jwtOptions.getSecretKey())
                       .compact();
        } catch (IOException e) {
            logger.error(e);
            return null;
        }
    }

}
