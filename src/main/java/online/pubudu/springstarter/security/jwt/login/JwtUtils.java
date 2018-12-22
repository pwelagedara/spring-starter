package online.pubudu.springstarter.security.jwt.login;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

/**
 * Created by pubudu welagedara on 12/17/18.
 */
@Component
public class JwtUtils {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.expiration}")
    private Long expiration;

    @Value("${security.jwt.authorities-claim}")
    private String authoritiesClaim;

    public String generateToken(String subject) {

        Date createdDate = new Date();

        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(new Date(createdDate.getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .compact();
    }

    public String generateToken(String subject, List<String> scopes) {

        Date createdDate = new Date();

        Claims claims = Jwts.claims().setSubject(subject);
        claims.setId(UUID.randomUUID().toString());
        claims.setIssuedAt(createdDate);
        claims.setExpiration(new Date(createdDate.getTime() + this.expiration * 1000));
        claims.put(this.authoritiesClaim, scopes);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .compact();
    }

    // Will throw an Exception if the token is invalid
    public void validateToken(String token, String subject) {
        Jwts.parser().requireSubject(subject).setSigningKey(this.secret).parseClaimsJws(token);
    }

    public String getSubject(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public List<String> getScopes(String token) {
        return getClaim(token, claims -> claims.get(this.authoritiesClaim, List.class));
    }

    private <T> T getClaim(String token, Function<Claims, T> claimResolver) {
        Claims claims = getAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(this.secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
