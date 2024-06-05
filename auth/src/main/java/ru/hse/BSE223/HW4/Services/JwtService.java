package ru.hse.BSE223.HW4.Services;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.hse.BSE223.HW4.Repositories.Data.User;

import java.util.Date;

@Service
public class JwtService {
    @Value("${token.signing.key}")
    private String jwtSigningKey;

    public String generateJwt(User user, Date expires) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSigningKey);
        JWTCreator.Builder jwtBuilder = JWT.create().withClaim("username", user.getUsername()).withClaim("email", user.getEmail()).withExpiresAt(expires);
        return jwtBuilder.sign(algorithm);
    }

    public DecodedJWT decodeAndValidateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSigningKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        } catch (JWTVerificationException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }
}
