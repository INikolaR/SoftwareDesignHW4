package ru.hse.BSE223.Services;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.hse.BSE223.API.UserDataResponse;
import ru.hse.BSE223.Exceptions.UnauthorizedException;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${auth.url.get-user-data}")
    private String url;
    private static final String EMAIL = "email";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String HEADER_NAME = "Authorization";
    public DecodedJWT checkAndDecodeToken(HttpServletRequest request) {
        var authHeader = request.getHeader(HEADER_NAME);
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, BEARER_PREFIX)) {
            return null;
        }
        var jwt = authHeader.substring(BEARER_PREFIX.length());
        try {
            RestTemplate restTemplate = new RestTemplate();
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add(HEADER_NAME, authHeader);
            HttpEntity<?> entity = new HttpEntity<>(headers);
            if (restTemplate.exchange(url, HttpMethod.GET, entity, UserDataResponse.class).getStatusCode() != HttpStatus.OK) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        try {
            return JWT.decode(jwt);
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    public DecodedJWT decodeToken(HttpServletRequest request) {
        var authHeader = request.getHeader(HEADER_NAME);
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, BEARER_PREFIX)) {
            return null;
        }
        var jwt = authHeader.substring(BEARER_PREFIX.length());
        try {
            return JWT.decode(jwt);
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    public String extractEmail(HttpServletRequest request) {
        try {
            DecodedJWT decodedJwt = decodeToken(request);
            String email = decodedJwt.getClaim(EMAIL).asString();
            if (StringUtils.isNotEmpty(email)) {
                return email;
            } else {
                throw new UnauthorizedException("Bad token!");
            }
        } catch (Exception e) {
            throw new UnauthorizedException("Bad token!");
        }
    }
    public String checkAndExtractEmail(HttpServletRequest request) {
        try {
            DecodedJWT decodedJwt = checkAndDecodeToken(request);
            String email = decodedJwt.getClaim(EMAIL).asString();
            if (StringUtils.isNotEmpty(email)) {
                return email;
            } else {
                throw new UnauthorizedException("Bad token!");
            }
        } catch (Exception e) {
            throw new UnauthorizedException("Bad token!");
        }
    }
}
