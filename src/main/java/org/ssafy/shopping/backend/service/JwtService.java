package org.ssafy.shopping.backend.service;

import io.jsonwebtoken.Claims;

public interface JwtService {
    String getToken(String key, Object value);

    Claims getClaims(String token);

    boolean isValid(String token);

    int getId(String token);

    String getMemberMail(String token);
}