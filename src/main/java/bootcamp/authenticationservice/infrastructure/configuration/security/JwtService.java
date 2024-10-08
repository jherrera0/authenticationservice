package bootcamp.authenticationservice.infrastructure.configuration.security;

import bootcamp.authenticationservice.domain.exception.MalformJwtException;
import bootcamp.authenticationservice.domain.model.User;
import bootcamp.authenticationservice.until.ExceptionConst;
import bootcamp.authenticationservice.until.JwtConst;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service

public class JwtService {
    @Value("${app-security-key}")
    private String secretKey = "mySecretKeymysecretkeymySecretKeymysecretkeymySecretKey";
    public String generateToken(User user, Map<String, Object> extraClaims) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + JwtConst.EXPIRATION_TIME);
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getEmail())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key generateKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }


    public String extractUsername(String jwt) {
        try {
            return extractAllClaims(jwt).getSubject();
        } catch (MalformedJwtException e) {
            throw new MalformJwtException(ExceptionConst.INVALID_JWT);
        }
    }

    public Claims extractAllClaims(String jwt) {
        try {
            return Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(jwt).getBody();
        } catch (MalformedJwtException e) {
            throw new MalformJwtException(ExceptionConst.INVALID_JWT);
        }
    }
}
