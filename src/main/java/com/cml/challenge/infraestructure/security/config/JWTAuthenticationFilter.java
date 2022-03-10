package com.cml.challenge.infraestructure.security.config;

import com.cml.challenge.application.port.primary.ManageUserToken;
import com.cml.challenge.domain.model.UserToken;
import com.cml.challenge.infraestructure.security.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private String jwtSecret;

    private AuthenticationManager authenticationManager;

    private ManageUserToken manageUserToken;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, String jwtSecret,
                                   ManageUserToken manageUserToken) {
        this.authenticationManager = authenticationManager;
        this.jwtSecret = jwtSecret;
        this.manageUserToken = manageUserToken;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            User credenciales = new ObjectMapper().readValue(request.getInputStream(), User.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    credenciales.getUser(), credenciales.getPass(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String username = ((org.springframework.security.core.userdetails.User)auth.getPrincipal()).getUsername();
        String token = Jwts.builder().setIssuedAt(new Date()).setIssuer("www.cml.cl")
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + 600_000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
        response.addHeader("Authorization", "Bearer " + token);

        manageUserToken.associateUserToken(
            UserToken.builder().username(username).token(token).build());
    }
}
