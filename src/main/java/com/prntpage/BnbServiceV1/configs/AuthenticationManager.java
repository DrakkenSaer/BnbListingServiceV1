package com.prntpage.BnbServiceV1.configs;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.prntpage.BnbServiceV1.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        DecodedJWT jwtToken = jwtTokenUtil.verify(authentication.getCredentials().toString());

        // TODO: Refactor & add safety checking
        if (jwtToken.getClaim("auth") != null) {
            List<String> usernameAndPassword = Arrays.asList(jwtToken.getClaim("auth").asString().split(":"));
            Collection<? extends GrantedAuthority> authorities = usernameAndPassword.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            User principal = new User(usernameAndPassword.get(0), usernameAndPassword.get(1), new ArrayList<>());
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal, null, authorities);

            return Mono.just(auth);
        } else {
            return Mono.empty();
        }
    }
}