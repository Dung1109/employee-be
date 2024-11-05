package tayduong.com.employeebe.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

import static org.yaml.snakeyaml.nodes.Tag.PREFIX;

@Component
public class JwtService {

    static final long EXPIRATIONTIME = 60 * 60 * 1000;

    static final String PREFIX = "Bearer";

    static final SecretKey key = Jwts.SIG.HS256.key().build();


    // Generate signed JWT token
    public String getToken(String username) {
        String token = Jwts.builder().subject(username).expiration(new Date(System.currentTimeMillis() +
                        EXPIRATIONTIME)).signWith(key)
                .compact();
        return token;
    }

    // Get a token from request Authorization header,
    // verify the token, and get username
    public String getAuthUser(HttpServletRequest request) {
        String token = request.getHeader
                (HttpHeaders.AUTHORIZATION);
        if (token != null) {
            String user = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token.replace(PREFIX, "")).getPayload()
                    .getSubject();
            return user;
        }
        return null;
    }

}
