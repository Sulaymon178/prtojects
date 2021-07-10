package ecma.demo.springjwtlesson.controller;

import ecma.demo.springjwtlesson.entity.Users;
import ecma.demo.springjwtlesson.payload.ReqLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("sign")
    public HttpEntity<?> login(@RequestBody ReqLogin reqLogin){
        Date now = new Date();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(reqLogin.getUsername(),reqLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Users users =(Users) authentication.getPrincipal();

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String compact = Jwts.builder()
                .setSubject(users.getId().toString())
                .setIssuedAt(now)
                .claim("roles",authorities)
                .setExpiration(new Date(now.getTime()+1000*60))
                .signWith(SignatureAlgorithm.HS512, "salom")
                .compact();
        return ResponseEntity.ok(compact);
    }


}
