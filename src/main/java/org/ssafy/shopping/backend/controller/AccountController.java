package org.ssafy.shopping.backend.controller;

import io.jsonwebtoken.Claims;
import org.ssafy.shopping.backend.dto.MemberDto;
import org.ssafy.shopping.backend.entity.*;
import org.ssafy.shopping.backend.repository.*;
import org.ssafy.shopping.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
public class AccountController {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    JwtService jwtService;

    @PostMapping("/api/account/login")
    public ResponseEntity login(@RequestBody Map<String, String> params,
            HttpServletResponse res) {
        Member member = memberRepository.findByMemberMailAndPassword(params.get("email"), params.get("password"));

        if (member != null) {
            String email = member.getMemberMail();
            String token = jwtService.getToken("email", email);

            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            res.addCookie(cookie);

            return new ResponseEntity<>(email, HttpStatus.OK);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/account/logout")
    public ResponseEntity logout(HttpServletResponse res) {
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        res.addCookie(cookie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/account/register")
    public  ResponseEntity register(@RequestBody Map<String, String> params,
                                    HttpServletResponse res){
        Member member = new Member();
        member.setMemberMail(params.get("email"));
        member.setMemberName("염동엽");
        member.setMemberPhone("010-9999-0000");
        member.setPassword(params.get("password"));

        return new ResponseEntity<>(memberRepository.save(member), HttpStatus.OK);
    }


    @GetMapping("/api/account/check")
    public ResponseEntity check(@CookieValue(value = "token", required = false) String token) {
        Claims claims = jwtService.getClaims(token);

        if (claims != null) {
            String email  = claims.get("email").toString();
            return new ResponseEntity<>(email, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}