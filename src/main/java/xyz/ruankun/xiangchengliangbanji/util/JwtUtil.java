package xyz.ruankun.xiangchengliangbanji.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import xyz.ruankun.xiangchengliangbanji.entity.User;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration-seconds}")
    private long expirationSeconds;

    private SecretKey key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 生成token需要用到user的id，username
     * @param user
     * @return jwt字符串
     */
    public String generateToken(User user) {
        Instant now = Instant.now();
        Instant expirationTime = now.plus(expirationSeconds, ChronoUnit.SECONDS);
        return Jwts.builder()
                .setId(String.valueOf(user.getId()))
                .setSubject(user.getUsername())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expirationTime))
                .signWith(key)
                .compact();
    }

    /**
     * 解析token,返回user对象
     * @param token
     * @return
     */
    public User getUserFromToken(String token) {
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        String idStr = claims.getBody().getId();
        int id = Integer.parseInt(idStr);
        String username = claims.getBody().getSubject();
        return new User().setId(id).setUsername(username);
    }
}
