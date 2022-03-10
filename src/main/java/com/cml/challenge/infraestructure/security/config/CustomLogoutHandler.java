package com.cml.challenge.infraestructure.security.config;

import com.cml.challenge.application.port.primary.ManageUserToken;
import com.cml.challenge.domain.model.UserToken;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class CustomLogoutHandler implements LogoutHandler {

  private final ManageUserToken manageUserToken;

  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response,
                     Authentication authentication) {

    String token = request.getHeader("Authorization");
    Authentication auth = SecurityContextHolder.getContext()
        .getAuthentication();
    String userName = auth != null ?
        ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername() :
        null;

    if (userName == null && token != null) {
      try {
        userName = Jwts.parser()
            .setSigningKey("4321")
            .parseClaimsJws(token.replace("Bearer ", ""))
            .getBody()
            .getSubject();
      } catch (JwtException je) {
        log.warn("Problem retrieve the username from jwt", je);
      }
    }

    if (userName != null) {
      manageUserToken.removeUserToken(UserToken.builder()
          .username(userName).token(token).build());
    } else {
      log.info("the user is not authenticated");
    }

  }
}
