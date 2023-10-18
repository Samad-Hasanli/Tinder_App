package org.example.Services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

public class CookieService {

    private final String USER_ID = "id";
    HttpServletRequest req;
    HttpServletResponse resp;

    public CookieService(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
    }

    public Cookie getCookie(){
        Cookie result = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for(Cookie cookie : cookies){
                if(cookie.getName().equalsIgnoreCase(USER_ID)){
                    result = cookie;
                }
            }
        }
        return result;
    }

    public void addCookie(int id){
        resp.addCookie(new Cookie(USER_ID, String.valueOf(id)));
    }

    public void removeCookie(){
        Arrays.stream(req.getCookies())
                .filter(cookie -> cookie.getName().equalsIgnoreCase(USER_ID))
                .map(cookie -> new Cookie(cookie.getName(), cookie.getValue()){{
                    cookie.setMaxAge(0);
                }}).forEach(cookie -> resp.addCookie(cookie));
    }

}
