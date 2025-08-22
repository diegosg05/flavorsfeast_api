package com.flavors.pe.flavorsfeast.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {
    public static void create(HttpServletResponse response, String name, String value, Boolean secure, Integer maxAge, String domain) {
        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(false);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        cookie.setDomain(domain);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void clear(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(1);
        cookie.setDomain("localhost");
        response.addCookie(cookie);
    }
}
