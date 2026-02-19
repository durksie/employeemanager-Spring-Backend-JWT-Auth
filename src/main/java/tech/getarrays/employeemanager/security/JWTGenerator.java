package tech.getarrays.employeemanager.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTGenerator {

    private final SecretKey key =
            Keys.hmacShaKeyFor(SecurityConstants.JWT_SECRET.getBytes(StandardCharsets.UTF_8));


    // Generates a JWT Token when user logs in successfully.
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(username)        // sets username as token subject
                .setIssuedAt(currentDate)    // token creation time
                .setExpiration(expireDate)   // expiry time
                .signWith(key, SignatureAlgorithm.HS512) // signing algorithm
                .compact();
    }



     // Extract UserName from the generated TOKEN then  Reads the token payload and returns username.
    public String getUsernameFromJWT(String token){

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }


     // Checks if token is valid and not expired/tampered.

    public boolean validateToken(String token){
        try {

            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;

        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException(
                    "JWT was expired or incorrect"
            );
        }
    }
}
