package com.test.cookie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@ResponseBody
public class CookieController {
    @GetMapping("/")
    public String index() {
        return "hey there!";
    }

    @GetMapping("/cookie/make")
    public String makeCookie(HttpServletResponse response) {
        Cookie myCookie = new Cookie("myCookie", "ChocoCookie");
        myCookie.setPath("/");
        response.addCookie(myCookie);
        return myCookie.getValue();
    }

    @GetMapping("/cookie/check")
    public String checkCookie(HttpServletRequest request, @CookieValue(value="myCookie", required=false) Cookie myCookie) {
//        Cookie[] cookies = request.getCookies();
//
//        for(int i=0; i<cookies.length; i++) {
//            if(cookies[i].getName() == "myCookie") {
//                return "myCookie exists: " + cookies[i].getValue();
//            }
//        }

        if(myCookie != null) {
            return "myCookie exists: " + myCookie.getValue();
        }
        return "myCookie does not exists";
    }

    @GetMapping("/cookie/eat")
    public String eatCookie(HttpServletResponse response) {
        Cookie myCookie = new Cookie("myCookie", null);
        myCookie.setMaxAge(0);
        myCookie.setPath("/");
        response.addCookie(myCookie);
        return "nom nom it's delicious!";
    }
}
