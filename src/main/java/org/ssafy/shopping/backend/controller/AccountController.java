package org.ssafy.shopping.backend.controller;

import io.jsonwebtoken.Claims;
import lombok.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.ssafy.shopping.backend.dto.MemberDto;
import org.ssafy.shopping.backend.entity.*;
import org.ssafy.shopping.backend.repository.*;
import org.ssafy.shopping.backend.security.PasswordEncoder;
import org.ssafy.shopping.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class AccountController {
    @Autowired
    private PasswordEncoder bPasswordEncoder;

    @Autowired

    MemberRepository memberRepository;

    @Autowired
    JwtService jwtService;

    @PostMapping("/api/account/login")
    public ResponseEntity login(@RequestBody Map<String, String> params,
            HttpServletResponse res) {

        //아이디 기준으로 값 불러오고 여기에 들어있는 PW와 넘겨 받은 PW가 같은지 확인할거임
        Member originMember = memberRepository.findByMemberMail(params.get("email"));
        BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder();
        if(encoder.matches(params.get("password"), originMember.getPassword())){



//        Member member = memberRepository.findByMemberMailAndPassword(params.get("email"), params.get("password"));

//        if (member != null) {
            String email = originMember.getMemberMail();
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
        System.out.println(params.get("email"));
        Member member = new Member();
        member.setMemberMail(params.get("email"));
        member.setMemberName(params.get("name"));
        member.setMemberPhone(params.get("phonenum"));
        member.setRegDate(LocalDateTime.now());
        // 주소값 미구현 하드 코딩
        member.setAddress("경기도");
        String hashingPW = this.bPasswordEncoder.encode(params.get("password"));
        member.setPassword(hashingPW);

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