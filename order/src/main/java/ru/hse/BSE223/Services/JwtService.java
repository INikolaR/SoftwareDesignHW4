package ru.hse.BSE223.Services;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;

public interface JwtService {
    DecodedJWT checkAndDecodeToken(HttpServletRequest request);
    DecodedJWT decodeToken(HttpServletRequest request);
    String extractEmail(HttpServletRequest request);
    String checkAndExtractEmail(HttpServletRequest request);
}
