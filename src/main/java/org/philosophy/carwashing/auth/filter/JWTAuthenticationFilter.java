package org.philosophy.carwashing.auth.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

import static org.philosophy.carwashing.util.CommonStringConstants.USERNAME;
import static org.philosophy.carwashing.util.CommonStringConstants.PASSWORD;
import static org.philosophy.carwashing.util.CommonStringConstants.ACCESS_TOKEN;
import static org.philosophy.carwashing.util.CommonStringConstants.REFRESH_TOKEN;
import static org.philosophy.carwashing.util.CommonStringConstants.ROLES;
import static org.philosophy.carwashing.util.CommonStringConstants.SECRET;


@Slf4j
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final int SECONDS_IN_MINUTE = 60;
    public static final int MILLIS_IN_SECOND = 1000;

/*
    @Value("${secret}")
    public static String SECRET;
*/
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        log.debug("Username: {}, password {}", username, password);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(SECRET.getBytes(StandardCharsets.UTF_8));
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * SECONDS_IN_MINUTE * MILLIS_IN_SECOND))
                .withIssuer(request.getRequestURL().toString())
                .withClaim(ROLES, user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(algorithm);
        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 50 * SECONDS_IN_MINUTE * MILLIS_IN_SECOND))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        response.setHeader(ACCESS_TOKEN, access_token);
        response.setHeader(REFRESH_TOKEN, refresh_token);
    }
}
