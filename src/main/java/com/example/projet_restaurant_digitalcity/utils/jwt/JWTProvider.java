package com.example.projet_restaurant_digitalcity.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.projet_restaurant_digitalcity.domain.entity.Role;
import com.example.projet_restaurant_digitalcity.domain.entity.Worker;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.time.Instant;



@Component
public class JWTProvider {

    private final UserDetailsService userDetailsService;

    public JWTProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    public String generateToken(Worker worker){
        return JWT.create()
                .withSubject(worker.getUsername())
                .withExpiresAt(Instant.now().plusMillis(86_400_000)) // 1 jour
                .withClaim(
                        "roles",
                        worker.getRoles().stream()
                                .map(Role::getName)
                                .toList()
                )
                .sign(Algorithm.HMAC512("#RqzSTh77B29z@vj})Pe6T;Vaoz6jc;~wtG(Bzavk,L#IyokJT"));
    }

    public String extractToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if( authHeader == null || !authHeader.startsWith("Bearer ") )
            return null;

        return authHeader.replace("Bearer ", "");
    }

    public UserDetails isTokenValid(String token){

        JWTVerifier verifier = JWT.require(Algorithm.HMAC512("#RqzSTh77B29z@vj})Pe6T;Vaoz6jc;~wtG(Bzavk,L#IyokJT"))
                .acceptExpiresAt(86_400_000)
                .build();

        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            String username = decodedJWT.getSubject();
            return userDetailsService.loadUserByUsername(username);
        }
        catch (JWTVerificationException ex){
            return null;
        }

    }

    public Authentication generateAuth(UserDetails userDetails){
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
    }

}
