package io.picos.sailfish.sp.security.jwt.spring;

import io.picos.sailfish.sp.security.jwt.exception.JwtTokenMissingException;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;

public class UserParser {

    public UserDetails parseUserDetails(HttpServletRequest httpServletRequest) {

        String header = httpServletRequest.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new JwtTokenMissingException("No JWT token found in request headers");
        }

        String authToken = header.substring(7);
        //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回

        JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);

    }
}
