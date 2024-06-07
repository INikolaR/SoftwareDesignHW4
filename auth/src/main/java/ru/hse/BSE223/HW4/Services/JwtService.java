package ru.hse.BSE223.HW4.Services;

import com.auth0.jwt.interfaces.DecodedJWT;
import ru.hse.BSE223.HW4.Repositories.Data.User;

import java.util.Date;

public interface JwtService {
    String generateJwt(User user, Date expires);
    DecodedJWT decodeAndValidateToken(String token);

}
