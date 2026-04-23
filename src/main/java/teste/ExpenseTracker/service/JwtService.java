package teste.ExpenseTracker.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.print.DocFlavor;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123123123123123123123123123123";

    public String generateToken(UserDetails user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role",user.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256,SECRET)
                .compact();
    }
    public String extractUsername(String token){
        System.out.println(getBody(token).getSubject());
        return getBody(token).getSubject();
    }

    private Claims getBody(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET).build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token){
        Date exp = getBody(token).getExpiration();
        return exp.before(new Date());
    }

    public boolean isValid(String token, UserDetails user){
        String username = extractUsername(token);
        System.out.println(username);

        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }
}
