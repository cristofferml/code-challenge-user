package com.cml.challenge.infraestructure.security.config;

import com.cml.challenge.application.port.primary.ManageUserToken;
import com.cml.challenge.domain.model.UserToken;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

  private String jwtSecret;

  private ManageUserToken manageUserToken;

  public JWTAuthorizationFilter(AuthenticationManager authManager, String jwtSecret, ManageUserToken manageUserToken) {
    super(authManager);
    this.jwtSecret = jwtSecret;
    this.manageUserToken = manageUserToken;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
                                  FilterChain chain)
      throws IOException, ServletException {
    String header = req.getHeader("Authorization");
    if (header == null || !header.startsWith("Bearer ")) {
      chain.doFilter(req, res);
      return;
    }
    UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(req, res);
  }

  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    if (token != null) {
      String user = Jwts.parser()
          .setSigningKey(jwtSecret)
          .parseClaimsJws(token.replace("Bearer ", ""))
          .getBody()
          .getSubject();

      if (user != null) {
        UserToken userToken =
            manageUserToken.getUserTokenByUserName(UserToken.builder().username(user).build());
        if (userToken != null) {
          return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        }
      }
      return null;
    }
    return null;
  }

  public void setJwtSecret(String jwtSecret) {
    this.jwtSecret = jwtSecret;
  }

  public void setManageUserToken(
      ManageUserToken manageUserToken) {
    this.manageUserToken = manageUserToken;
  }
}
